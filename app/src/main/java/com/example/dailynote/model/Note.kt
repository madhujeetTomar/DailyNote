package com.example.dailynote.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "notes_table")
data class Note(@PrimaryKey @ColumnInfo(name = "id") val id:Long, val title: String, val content: String, val timeStamp: String)