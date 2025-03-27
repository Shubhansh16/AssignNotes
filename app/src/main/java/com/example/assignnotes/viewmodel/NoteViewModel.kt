package com.example.assignnotes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.assignnotes.database.NoteDatabase
import com.example.assignnotes.model.Note
import com.example.assignnotes.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(application: Application):AndroidViewModel(application) {

    private val repository:NoteRepository
    val allNotes:LiveData<List<Note>>

    init {

        val noteDao = NoteDatabase.getDatabase(application).noteDao()
        repository = NoteRepository(noteDao)
        allNotes = repository.allNotes
    }

    fun insert(note: Note)= viewModelScope.launch{
        repository.insert(note)
    }

    fun delete(note: Note)= viewModelScope.launch {
        repository.delete(note)
    }

    fun update(note: Note)= viewModelScope.launch {
        repository.update(note)
    }
}