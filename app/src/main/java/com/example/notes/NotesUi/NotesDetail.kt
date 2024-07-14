package com.example.notes.NotesUi

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.notes.Mydatabase.NotesEntity
import com.example.notes.NotesNavigation.Screen
import com.example.notes.NotesViewModel.NotesViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetails(
    Note: NotesEntity,
    notesViewModel: NotesViewModel,
    navController: NavController,
)
{

   var isReading by remember {
       mutableStateOf(true)
   }

    var title by remember {
        mutableStateOf(Note.title)
    }
    var desc by remember {
        mutableStateOf(Note.desc)
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(title = {
            Text(text =title, style = MaterialTheme.typography.titleMedium)},
            navigationIcon = {
                IconButton(onClick = {

                    //go to the home screen
                    navController.navigate(Screen.NotesHomeScreen.route)
                }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back Home")
                }
            },
            actions = {
                IconButton(onClick = {

                    //toggle between the reading mode to Update or Edit mode
                    isReading = !isReading

                    //update the Note
                    //here date will also got updated by modified date or new date
                    if(isReading){
                       notesViewModel.UpdateNote(NotesEntity(id = Note.id,
                           title = title.trim(),
                           desc = desc.trim(),
                           date = LocalDate.now()
                           ))

                        //after update go to the home screen
                        navController.navigate(Screen.NotesHomeScreen.route)
                    }
                }) {
                    Icon(imageVector = if(isReading)Icons.Outlined.Create else Icons.Filled.Check, contentDescription = "Edit")
                }
            }
        )

        TextField(
            value = title,
            onValueChange = {
                         title = it
                            },
            modifier = Modifier.fillMaxWidth(),
            readOnly = isReading,
        )
        Text(text = Note.date.toString(), style = MaterialTheme.typography.labelSmall)

        TextField(
            value =desc,
            onValueChange = {
                       desc = it
                            },
            modifier = Modifier.fillMaxSize(),
            readOnly = isReading
        )
    }
}

