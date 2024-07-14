package com.example.notes.Mydatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
abstract class NotesDao {


    //insert in database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun AddNote(notesEntity: NotesEntity)


    //Get all notes from Notes_Table
    @Query("select * from  `Notes_Table`")
    abstract fun GetAllNotes() : Flow<List<NotesEntity>>


    //Update the modified note from ui
    @Update
    abstract suspend fun UpdateNote(notesEntity: NotesEntity)


    //Get Note by id
    @Query("Select * From `Notes_Table` where Note_Id= :id")
    abstract  fun GetNotesById(id:Int):Flow<NotesEntity>

    //to delete a Note
    @Delete
    abstract suspend fun DeleteANote(notesEntity: NotesEntity)
}