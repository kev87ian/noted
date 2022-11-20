package com.kev.noted.view.fragments.notesfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kev.noted.R
import com.kev.noted.databinding.FragmentUpdateNoteBinding
import com.kev.noted.model.Note
import com.kev.noted.viewmodel.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class UpdateNoteFragment : Fragment(R.layout.fragment_update_note) {
	private var _binding: FragmentUpdateNoteBinding? = null
	private val binding get() = _binding!!

	private val viewModel: NotesViewModel by viewModels()
	private val args: UpdateNoteFragmentArgs by navArgs()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentUpdateNoteBinding.inflate(inflater, container, false)
		return binding.root
	}


	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.idEdtNoteDesc.setText(args.noteDescription)
		binding.idEdtNoteName.setText(args.noteTitle)

		binding.idBtn.setOnClickListener {
			updateNote()
		}

	}


	private fun updateNote() {
		val date = Date()
		val noteId = args.noteId
		val title = binding.idEdtNoteName.text.toString()
		val description = binding.idEdtNoteDesc.text.toString()
		val note = Note(noteId, title, description, date)

		if (binding.idEdtNoteName.text.isNullOrEmpty() || binding.idEdtNoteDesc.text.isNullOrEmpty()) {
			Toast.makeText(requireContext(), "Ensure all fields are filled", Toast.LENGTH_SHORT)
				.show()
		} else {
			Toast.makeText(requireContext(), "Note updated", Toast.LENGTH_SHORT).show()
			viewModel.updateNote(note)
			findNavController().navigate(R.id.action_updateNoteFragment_to_notesListFragment)
		}
	}
}