package com.kev.noted.view.fragments.notesfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.kev.noted.R
import com.kev.noted.databinding.FragmentNotesListBinding
import com.kev.noted.view.adapter.NotesAdapter
import com.kev.noted.viewmodel.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesListFragment : Fragment(R.layout.fragment_notes_list) {
	private var _binding: FragmentNotesListBinding? = null
	private val binding get() = _binding!!

	private val viewModel: NotesViewModel by viewModels()

	lateinit var notesAdapter: NotesAdapter

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentNotesListBinding.inflate(inflater, container, false)
		return binding.root
	}


	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		createNewNote()
		fetchNotes()
	}

	private fun fetchNotes() {
	notesAdapter = NotesAdapter()
		binding.notesRecyclerview.apply {
			adapter = notesAdapter
			layoutManager  = GridLayoutManager(requireContext(), 2)
		}


		viewModel.getAllNotes().observe(viewLifecycleOwner){
			notesAdapter.differ.submitList(it)
		}
	}

	private fun createNewNote() {
		binding.btnNewNote.setOnClickListener {
			findNavController().navigate(R.id.action_notesListFragment_to_newNoteFragment)
		}
	}

	override fun onDestroy() {
		super.onDestroy()
		_binding = null
	}
}