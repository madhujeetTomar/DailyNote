package com.example.dailynote

import androidx.lifecycle.LiveData
import com.example.dailynote.model.Note
import com.example.dailynote.model.datasource.NoteDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class NoteRepository(val noteDAO: NoteDAO) : CoroutineScope {



    val allNotes: LiveData<List<Note>> = noteDAO.getAllNotes()

    suspend fun insertNote(note: Note) {
        noteDAO.insert(note)
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}