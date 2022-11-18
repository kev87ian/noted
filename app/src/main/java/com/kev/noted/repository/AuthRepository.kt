package com.kev.noted.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.kev.util.LoadingState
import javax.inject.Inject

interface AuthRepository {
	val currentUser:FirebaseUser?

	suspend fun login(email:String, password:String) : LoadingState<FirebaseUser>
	suspend fun signUp(name:String, email: String, password: String) : LoadingState<FirebaseUser>
	fun logout()
}