package com.example.thisnote.presentation.notesList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thisnote.data.Note
import com.example.thisnote.repositories.NotesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val notesRepo: NotesRepo
) :ViewModel() {


    val notes = notesRepo.getNotes()

    private val _searchNotes = MutableStateFlow<List<Note>>(emptyList())
    val searchNotes: StateFlow<List<Note>> = _searchNotes


    fun upsertNote(note:Note){
        viewModelScope.launch {
            notesRepo.upsertNote(note)
        }
    }

    fun searchNotes(searchQuery: String) = viewModelScope.launch {
        notesRepo.searchNotes(searchQuery).collect { notesList ->
            _searchNotes.emit(notesList)
        }
    }

    fun deleteNote(note:Note) = viewModelScope.launch {
        notesRepo.deleteNote(note)
    }

}