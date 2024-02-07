package com.example.thisnote.data

import android.app.Application
import androidx.room.Room
import com.example.thisnote.data.db.NoteDao
import com.example.thisnote.data.db.NoteDatabase
import com.example.thisnote.repositories.NotesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotesModule {


    @Provides
    @Singleton
    fun provideNoteDatabase(context: Application): NoteDatabase {
        return Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            "note_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNoteDao(notesDatabase: NoteDatabase): NoteDao = notesDatabase.noteDao

    @Provides
    @Singleton
    fun provideRepository(noteDao: NoteDao): NotesRepo = NotesRepo(noteDao)

}