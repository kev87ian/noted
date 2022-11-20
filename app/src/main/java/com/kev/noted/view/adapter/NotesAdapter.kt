package com.kev.noted.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kev.noted.databinding.NoteItemBinding
import com.kev.noted.model.Note
import com.kev.noted.view.fragments.notesfragments.NotesListFragmentDirections

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

	class NotesViewHolder(val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root)


	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {

		val view = NoteItemBinding.inflate(
			LayoutInflater.from(parent.context), parent, false
		)
		return NotesViewHolder(view)
	}

	override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
		val currentNote = differ.currentList[position]

		with(holder) {
			binding.noteTitleTv.text = currentNote.noteTitle
			binding.noteDescriptionTextview.text = currentNote.noteDescription
			binding.lastModifiedTextview.text =
				"Last update : ".plus(currentNote.dateAdded.toString())
		}


		holder.itemView.setOnClickListener {
			val direction = NotesListFragmentDirections.actionNotesListFragmentToUpdateNoteFragment(currentNote.noteTitle, currentNote.noteDescription, currentNote.id)
			it.findNavController().navigate(direction)
		}
	}

	override fun getItemCount(): Int {

		return differ.currentList.size

	}


	private val diffUtil = object : DiffUtil.ItemCallback<Note>() {
		override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
			return oldItem.noteTitle == newItem.noteTitle
		}

		override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
			return newItem == oldItem
		}
	}

	val differ = AsyncListDiffer(this, diffUtil)
}