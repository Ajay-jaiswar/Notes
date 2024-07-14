package com.example.notes.NotesNavigation

sealed class Screen(val route:String) {
    object NotesHomeScreen:Screen("NotesHome")
    object NotesDetailScreen:Screen("NotesDetail")
    object NoteAddScreen:Screen("NoteAdd")
    object NotesUpdateScreen:Screen("UpdateScreen")
}