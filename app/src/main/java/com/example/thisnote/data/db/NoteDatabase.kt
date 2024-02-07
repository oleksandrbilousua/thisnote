package com.example.thisnote.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.thisnote.data.Note

@Database(entities = [Note::class], version = 2, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao
}