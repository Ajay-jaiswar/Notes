package com.example.notes.NotesViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NotesVMProvider:ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NotesViewModel() as T
    }
}