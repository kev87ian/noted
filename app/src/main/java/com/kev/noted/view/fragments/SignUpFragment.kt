package com.kev.noted.view.fragments

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.kev.noted.R
import com.kev.noted.databinding.FragmentSignUpBinding
import com.kev.noted.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment:Fragment(R.layout.fragment_sign_up){
	private var _binding : FragmentSignUpBinding? = null
	private val binding get() = _binding!!

	private val viewModel : AuthViewModel by viewModels()
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		_binding = FragmentSignUpBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.createUser.setOnClickListener {
			createUser()
		}
	}

	private fun createUser() {
		val email = binding.userEmailEtv.text.toString()
		val name = binding.firstnameEtv.text?.trim().toString()
		val password = binding.userPasswordEtv.text.toString()
		val confirmPassword = binding.confirmPasswordEtv.text.toString()

		if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
			Toast.makeText(requireContext(), "Ensure email is correct", Toast.LENGTH_SHORT).show()
		}
		else if(name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
			Toast.makeText(requireContext(), "Ensure all fields are filled", Toast.LENGTH_SHORT).show()
		}
		else if(password ==confirmPassword && confirmPassword.length <=  5){
		Toast.makeText(requireContext(), "Password must contain 5 or more characters", Toast.LENGTH_SHORT).show()

		}
		else if(password != confirmPassword){
			binding.confirmPasswordEtv.error = "Passwords do not match"
		}

		else{
		/*viewModel.signUp(name, email, password)*/
			Toast.makeText(requireContext(), "Conditions yote sawa", Toast.LENGTH_SHORT).show()
		}


	}

	override fun onDestroy() {
		super.onDestroy()
		_binding = null
	}
}