package com.example.notes.NotesViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.Mydatabase.DbGraph
import com.example.notes.Mydatabase.NotesDBRep
import com.example.notes.Mydatabase.NotesEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NotesViewModel(
    private val notesDBRep: NotesDBRep? = DbGraph.notesDBRep
):ViewModel() {

    lateinit var NotesList: Flow<List<NotesEntity>>


    //use repository to access Dao Functions
    init {
        viewModelScope.launch {
            val result = notesDBRep?.getAllNote()
            NotesList = result!!
        }
    }

    fun AddNote(notesEntity: NotesEntity)
    {
        viewModelScope.launch(Dispatchers.IO) {
            notesDBRep?.addNotes(notesEntity)
        }
    }


    fun UpdateNote(notesEntity: NotesEntity){
        viewModelScope.launch(Dispatchers.IO) {
            notesDBRep?.updateNote(notesEntity)
        }
    }

    fun getNoteById(id:Int):Flow<NotesEntity>{
       return notesDBRep!!.getNoteById(id)
    }

    fun deleteANote(notesEntity: NotesEntity){
        viewModelScope.launch (Dispatchers.IO){
            notesDBRep?.deleteANote(notesEntity)
        }
    }
}