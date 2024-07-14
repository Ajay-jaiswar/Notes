package com.example.notes.Mydatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [NotesEntity::class],
    version = 1,
    exportSchema = false)
@TypeConverters(DatetoStringConverter::class)
abstract class NoteDatabase:RoomDatabase(){
    abstract fun notesDao(): NotesDao
}