package com.kev.noted.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.kev.noted.repository.AuthRepository
import com.kev.util.LoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
	private val repository: AuthRepository
) : ViewModel() {

	private val _loginLiveData : MutableLiveData<LoadingState<FirebaseUser>> = MutableLiveData()
	val loginLiveData : LiveData<LoadingState<FirebaseUser>> = _loginLiveData

	private val _signUpLiveData : MutableLiveData<LoadingState<FirebaseUser>> = MutableLiveData()
	val signUpLiveData : LiveData<LoadingState<FirebaseUser>> = _signUpLiveData


	fun login(email:String, password:String) = viewModelScope.launch {
		_loginLiveData.postValue(LoadingState.Loading())
		val result = repository.login(email, password)
		_loginLiveData.postValue(result)
	}

	fun signUp(name:String, email: String, password: String) = viewModelScope.launch {
		_signUpLiveData.postValue(LoadingState.Loading())
		val result = repository.signUp(name, email, password)
		_signUpLiveData.postValue(result)
	}

	val currentUser : FirebaseUser?
		get() = repository.currentUser

	fun logout(){
		repository.logout()
	}
	init {

		if (repository.currentUser != null){
			_loginLiveData.postValue(LoadingState.Success(repository.currentUser!!))
		}
	}
}