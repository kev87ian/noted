package com.kev.noted.view.fragments.notesfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kev.noted.R
import com.kev.noted.databinding.FragmentNewNoteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewNoteFragment : Fragment(R.layout.fragment_new_note){

	private var _binding:FragmentNewNoteBinding? = null
	private val binding get() = _binding!!


	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		_binding = FragmentNewNoteBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onDestroy() {
		super.onDestroy()
		_binding = null
	}

}