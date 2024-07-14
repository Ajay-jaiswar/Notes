package com.example.notes.Mydatabase

import android.content.Context
import androidx.room.Room

object DbGraph {

    @Volatile
    var database: NoteDatabase? = null

    val notesDBRep by lazy {
        database?.notesDao()?.let { NotesDBRep(it) }
    }

    fun getDatabase(context: Context):NoteDatabase
    {
        //return database instance and give new database instance if there is no database is Created At
        //or return  Existing Room DB
        return database ?: synchronized(this){
            val instance = Room.databaseBuilder(
                context = context,
                klass = NoteDatabase::class.java,
                "Note.db"
            ).build()
            database = instance
            instance
        }
    }
}