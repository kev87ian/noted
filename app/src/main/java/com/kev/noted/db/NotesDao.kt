package com.kev.noted.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kev.noted.model.Note

@Dao
interface NotesDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun saveNotes(note: Note): Long

	@Query("SELECT * FROM notes ORDER BY date_added DESC")
	fun getAllNotes(): LiveData<List<Note>>

	@Delete
	suspend fun deleteNote(note: Note)

	@Update
	suspend fun updateNote(note: Note)
}