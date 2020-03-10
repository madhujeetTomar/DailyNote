package com.example.dailynote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.dailynote.model.Note
import com.example.dailynote.model.datasource.NoteDatabase
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NoteRepository

    val allNotes:LiveData<List<Note>>

    init {

        val noteDAO=NoteDatabase.getDatabase(application).noteDao()
        repository= NoteRepository(noteDAO)
        allNotes=repository.allNotes
    }

     fun insert(note: Note) = viewModelScope.launch { repository.insertNote(note) }

}