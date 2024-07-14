package com.example.notes.Mydatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity("Notes_Table")
data class NotesEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("Note_Id")
    val id:Int=0,

    @ColumnInfo("Note_Title")
    val title:String,

    @ColumnInfo("Note_Desc")
    val desc:String,

    @ColumnInfo("Note_Date")
    val date:LocalDate
)