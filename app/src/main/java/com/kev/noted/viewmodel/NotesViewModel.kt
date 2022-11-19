package com.kev.noted.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kev.noted.model.Note
import com.kev.noted.repository.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
	private val repository: NotesRepository
) : ViewModel() {


	fun insertNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
		repository.insertNote(note)
	}

	fun getAllNotes() = repository.getAllNotes()

	fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
		repository.deleteNote(note)
	}

	fun updateNote(note: Note) = viewModelScope.launch {
		repository.updateNote(note)
	}

}