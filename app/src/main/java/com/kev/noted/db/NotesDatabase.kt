package com.kev.noted.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kev.noted.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {

	abstract fun notesDao() : NotesDao

	companion object{
		private var DB_INSTANCE : NotesDatabase? = null

		fun getDbInstance(context: Context) : NotesDatabase{
			if (DB_INSTANCE==null){
				DB_INSTANCE = Room.databaseBuilder(
					context,
					NotesDatabase::class.java,
					"notes_db"
				).allowMainThreadQueries()
					.build()
			}

			return DB_INSTANCE!!
		}

	}



}