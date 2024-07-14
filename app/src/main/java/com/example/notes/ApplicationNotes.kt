package com.example.notes

import android.app.Application
import com.example.notes.Mydatabase.DbGraph

class ApplicationNotes:Application() {

    override fun onCreate() {
        super.onCreate()

        //Return the Room DB Instance
        DbGraph.getDatabase(this)
    }
}