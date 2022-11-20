package com.kev.noted.view.fragments.notesfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kev.noted.R
import com.kev.noted.databinding.FragmentNewNoteBinding
import com.kev.noted.model.Note
import com.kev.noted.viewmodel.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class NewNoteFragment : Fragment(R.layout.fragment_new_note) {

	private var _binding: FragmentNewNoteBinding? = null
	private val binding get() = _binding!!

	private val viewModel: NotesViewModel by viewModels()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)


		binding.idBtn.setOnClickListener {
			createNewNote()
		}
	}

	private fun createNewNote() {

		val title = binding.idEdtNoteName.text.toString().trim()
		val description = binding.idEdtNoteDesc.text.toString().trim()
		val date = Date()
		val note = Note(1, title, description, date)
		if (title.isEmpty() || description.isEmpty()) {
			Toast.makeText(requireContext(), "Ensure all fields are field", Toast.LENGTH_SHORT)
				.show()
		} else {
			viewModel.insertNote(note)
			Toast.makeText(requireContext(), "Note successfully saved", Toast.LENGTH_SHORT).show()
			findNavController().navigate(R.id.action_newNoteFragment_to_notesListFragment)
		}


	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentNewNoteBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onDestroy() {
		super.onDestroy()
		_binding = null
	}

}