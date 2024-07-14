package com.example.notes

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.notes.NotesNavigation.NotesNav
import com.example.notes.NotesViewModel.NotesVMProvider
import com.example.notes.NotesViewModel.NotesViewModel
import com.example.notes.ui.theme.NotesTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val notesViewModel = ViewModelProvider(
            owner = this,
            factory = NotesVMProvider()
        ).get(NotesViewModel::class.java)

        enableEdgeToEdge()
        setContent {
            NotesTheme {
                    NotesNav(notesViewModel)
            }
        }
    }
}
