package com.kev.noted.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "notes")
data class Note(


	@ColumnInfo(name = "title")
	val noteTitle:String,
	@ColumnInfo(name = "description")
	val noteDescription:String,
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "date_added")
	val dateAdded:Date
)
