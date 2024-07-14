package com.example.notes.Mydatabase

import kotlinx.coroutines.flow.Flow

class NotesDBRep(private val notesDao: NotesDao){


    suspend fun addNotes(notesEntity: NotesEntity){
        notesDao.AddNote(notesEntity)
    }

    fun getAllNote() = notesDao.GetAllNotes()

    suspend fun updateNote(notesEntity: NotesEntity){
        notesDao.UpdateNote(notesEntity)
    }

    fun getNoteById(id:Int): Flow<NotesEntity> = notesDao.GetNotesById(id)


    suspend fun deleteANote(notesEntity: NotesEntity) = notesDao.DeleteANote(notesEntity = notesEntity)
}