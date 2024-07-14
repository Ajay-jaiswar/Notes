package com.example.notes.NotesNavigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.notes.Mydatabase.NotesEntity
import com.example.notes.NotesUi.AddNote
import com.example.notes.NotesUi.NoteDetails
import com.example.notes.NotesUi.NotesHome
import com.example.notes.NotesViewModel.NotesViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NotesNav(
    notesViewModel: NotesViewModel
)
{
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.NotesHomeScreen.route)
    {

        //Starting Screen or Home Screen for Notes list
        composable(Screen.NotesHomeScreen.route){
            NotesHome(
                notesViewModel = notesViewModel,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                notes = notesViewModel.NotesList.collectAsState(initial = listOf()),
                navController
                )
        }


        //Note Detail Screen to Read the Notes
        composable(Screen.NotesDetailScreen.route+"/{id}",
            arguments = listOf(
                navArgument("id"){type=NavType.IntType}
            ))
        {backstackEntry->
            val id  = backstackEntry.arguments?.getInt("id")?:1
            val note: NotesEntity by notesViewModel.getNoteById(id).collectAsState(
                initial = NotesEntity(
                    id,
                    title = "",
                    desc = "",
                   date =  LocalDate.now()
                ))

            if(id != 0 && note.title !=""){
                NoteDetails(Note = note, notesViewModel = notesViewModel, navController =navController )
            }
        }

        composable(Screen.NoteAddScreen.route){
            AddNote(
                notesViewModel,
                navController)
        }
    }
}