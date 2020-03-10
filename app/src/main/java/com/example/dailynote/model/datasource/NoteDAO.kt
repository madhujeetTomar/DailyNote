package com.example.dailynote.model.datasource

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dailynote.model.Note


@Dao
interface NoteDAO {

    @Query("SELECT * from notes_table ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Note)

    @Query("DELETE FROM notes_table")
    suspend fun deleteAll()
}