package com.example.notes.NotesUi

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notes.Mydatabase.NotesEntity
import com.example.notes.NotesNavigation.Screen
import com.example.notes.NotesViewModel.NotesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesHome(
    notesViewModel: NotesViewModel,
    modifier: Modifier,
    notes:State<List<NotesEntity>>,
    navController: NavController,
){
    Scaffold(

        //Add Top App Bar
        topBar = { TopAppBar(title = { Text(text = "Notes", style = MaterialTheme.typography.headlineMedium) }) },
        modifier = Modifier.fillMaxSize(),

        //Add Floating Action Button
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Screen.NoteAddScreen.route)
            }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription ="Add Notes")
            }
        }
    ) {paddingValues ->
        LazyColumn(
            modifier = modifier.padding(paddingValues)
        ) {
            items(
                items = notes.value,
                key = {
                    it.id
                }
            ){note->
                val dismissState = rememberDismissState(
                    confirmValueChange ={

                        //if the item(Note) is swipe in direction to right to left then Delete the Note from Room
                        if(it == DismissValue.DismissedToStart){
                            //Delete the Note
                            notesViewModel.deleteANote(note)
                        }
                        //if it Swipe in opposite Direction the do nothing
                        false
                    }
                )

                //Swipe to Dismiss The Cards or Items(Note) and Delete the Note From the Room Database
                SwipeToDismiss(
                    state =dismissState ,
                    background ={
                        val color= animateColorAsState(targetValue =
                        if(dismissState.dismissDirection == DismissDirection.EndToStart) Color.Red else Color.Transparent,
                            label = " ")
                        Box(
                            contentAlignment = Alignment.CenterEnd,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .padding(horizontal = 20.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(color.value)
                            ,
                        ){
                            Icon(Icons.Default.Delete, contentDescription =null, tint = Color.White )
                        }
                    } , dismissContent = {
                        NotesItems(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .padding(16.dp),
                            note = note,
                            navController = navController
                            )
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesItems(
    modifier: Modifier,
    note:NotesEntity,
    navController: NavController
)
{
            Card(
                modifier = modifier,
                elevation = CardDefaults.outlinedCardElevation(),
                onClick = {
                    navController.navigate(route = Screen.NotesDetailScreen.route+"/${note.id}")
                }
            ) {
                Column(modifier = modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                ) {
                    Text(text = note.title, style = MaterialTheme.typography.titleMedium)
                    Text(text = note.date.toString(),
                        modifier = Modifier.align(Alignment.End),
                        style = MaterialTheme.typography.labelSmall)
                }
            }
}

