package com.example.notes.NotesUi

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.notes.Mydatabase.NotesEntity
import com.example.notes.NotesNavigation.Screen
import com.example.notes.NotesViewModel.NotesViewModel
import java.time.LocalDate


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNote(
    notesViewModel: NotesViewModel,
    navController: NavHostController)
{
    val context = LocalContext.current


    var title by remember { mutableStateOf("") }
    var  desc by remember { mutableStateOf("") }


    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Add Note") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()

                        //go back to the Home Screen and Cancel the Adding Notes
                        navController.navigate(Screen.NotesHomeScreen.route)

                        //Show the Toast to Canceled to Add Note
                        Toast.makeText(context,"Cancel",Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back cancel"
                        )
                    }
                },
            actions = {
                IconButton(onClick = {
                    if(title.isNotEmpty()){

                        //Add Note to the Room DB
                        notesViewModel.AddNote(NotesEntity(title = title.trim() , desc =desc.trim() , date = LocalDate.now() ))
                        navController.popBackStack()

                        // GO Back to the Home and update the ui with new Note
                        navController.navigate(Screen.NotesHomeScreen.route)

                        //Show Success
                        Toast.makeText(context,"Added",Toast.LENGTH_SHORT).show()
                    }else{

                        //if Text field is Empty and show this to User
                        Toast.makeText(context,"Please Enter title At least",Toast.LENGTH_SHORT).show()
                    }
                }) {
                    Icon(imageVector = Icons.Filled.Check, contentDescription = "Save")
                }
            }
            )}
    ) {innerpadding->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                value = title,
                onValueChange ={title = it},
                placeholder = { Text(text = "Add Title") },
                label = { Text(text = "Title") },
                maxLines = 1
                )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxSize(),
                value = desc,
                onValueChange ={desc = it},
                placeholder = { Text(text = "Add Description") },
                label = { Text(text = "Note")}
            )
        }
    }
}
