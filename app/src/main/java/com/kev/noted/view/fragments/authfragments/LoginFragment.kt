package com.kev.noted.view.fragments.authfragments

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kev.noted.R
import com.kev.noted.databinding.FragmentLoginBinding
import com.kev.noted.viewmodel.AuthViewModel
import com.kev.util.LoadingState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
	private var _binding: FragmentLoginBinding? = null
	private val binding get() = _binding!!

	private val viewModel: AuthViewModel by viewModels()
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		_binding = FragmentLoginBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.btnLogin.setOnClickListener {
			loginUser()
		}


		if (findNavController().currentDestination?.id == R.id.loginFragment){
			binding.resetPasswordTextview.setOnClickListener {
				findNavController().navigate(R.id.action_loginFragment_to_resetPasswordFragment)
			}

		}

		if (findNavController().currentDestination?.id == R.id.loginFragment){

			binding.signupTextview.setOnClickListener {
				findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
			}

		}

	}

	private fun loginUser() {

		val email = binding.userEmailEtv.text?.trim().toString()
		val password = binding.userPasswordEtv.text.toString()
		if (email.isEmpty() || password.isEmpty()) {
			Toast.makeText(requireContext(), "Ensure all fields are filled", Toast.LENGTH_SHORT)
				.show()
		} else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
			Toast.makeText(
				requireContext(),
				"Ensure the email is correctly typed.",
				Toast.LENGTH_SHORT
			).show()
		} else {
			viewModel.login(email, password)
		}


		viewModel.loginLiveData.observe(viewLifecycleOwner) { state ->
			when (state) {
				is LoadingState.Loading -> {
					binding.progressBar.visibility = View.VISIBLE
					binding.errorTextview.visibility = View.GONE
				}

				is LoadingState.Error -> {
					binding.progressBar.visibility = View.GONE
					binding.errorTextview.visibility = View.VISIBLE
					binding.errorTextview.text = state.message
				}
				is LoadingState.Success -> {
					binding.progressBar.visibility = View.GONE
					Toast.makeText(requireContext(), "Login was successful!", Toast.LENGTH_SHORT).show()

					if (findNavController().currentDestination?.id == R.id.loginFragment){
						findNavController().navigate(R.id.action_loginFragment_to_notesListFragment)
					}
				}
			}
		}
	}

	override fun onDestroy() {
		super.onDestroy()
		_binding = null
	}
}