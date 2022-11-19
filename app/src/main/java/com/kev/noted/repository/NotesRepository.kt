package com.kev.noted.repository

import com.kev.noted.db.NotesDao
import com.kev.noted.model.Note
import javax.inject.Inject

class NotesRepository @Inject constructor(
	private val dao: NotesDao
) {

	suspend fun insertNote(note: Note) = dao.saveNotes(note)

	fun getAllNotes() = dao.getAllNotes()

	suspend fun deleteNote(note: Note) = dao.deleteNote(note)

	suspend fun updateNote(note: Note) = dao.updateNote(note)

}