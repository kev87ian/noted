package com.kev.noted.repository

import await
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.*
import com.kev.util.LoadingState
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
	private val firebaseAuth: FirebaseAuth
) : AuthRepository {

	override val currentUser: FirebaseUser?
		get() = firebaseAuth.currentUser

	override suspend fun login(email: String, password: String): LoadingState<FirebaseUser> {
		return try {
			val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
			LoadingState.Success(result.user!!)
		} catch (e: Exception) {
			e.printStackTrace()
			when (e) {
				is FirebaseAuthInvalidCredentialsException -> LoadingState.Error("Invalid Credentials. Please try again.")
				is FirebaseAuthInvalidUserException -> LoadingState.Error("No such user exists. Please check your email again.")
				is FirebaseNetworkException -> LoadingState.Error("Ensure you have an active internet connection.")
				else -> LoadingState.Error(e.localizedMessage!!)
			}

		}
	}

	override suspend fun signUp(
		name: String,
		email: String,
		password: String
	): LoadingState<FirebaseUser> {
		return try {
			val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
			result.user?.updateProfile(
				UserProfileChangeRequest.Builder().setDisplayName(name).build()
			)?.await()
			LoadingState.Success(result.user!!)
		} catch (e: Exception) {
			e.printStackTrace()
			when (e) {
				is FirebaseNetworkException -> LoadingState.Error("Ensure you have an active internet connection.")
				is FirebaseAuthUserCollisionException -> LoadingState.Error("An account with this email already exists!")
				else -> LoadingState.Error(e.localizedMessage!!)
			}
		}
	}

	override fun logout() {
		firebaseAuth.signOut()
	}
}