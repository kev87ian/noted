package com.kev.noted.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "notes")
data class Note(

	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "id")
	val id: Int,

	@ColumnInfo(name = "title")
	val noteTitle: String,

	@ColumnInfo(name = "description")
	val noteDescription: String,

	@ColumnInfo(name = "date_added")
	val dateAdded: Date
)
