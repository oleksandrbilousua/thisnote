package com.example.thisnote.repositories

import com.example.thisnote.data.Note
import com.example.thisnote.data.db.NoteDao


class NotesRepo(
    private val notesDao: NoteDao
) {

    suspend fun upsertNote(note: Note) = notesDao.upsertNote(note)

    suspend fun deleteNote(note: Note) = notesDao.deleteNote(note)

    fun getNotes() = notesDao.selectNotes()

    fun searchNotes(searchQuery: String) = notesDao.searchInNotesTitle(searchQuery)

    suspend fun deleteAllNotes() = notesDao.deleteAllNotes()

}