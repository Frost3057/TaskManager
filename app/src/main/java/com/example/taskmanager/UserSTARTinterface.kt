package com.example.taskmanager

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.text.Layout
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.AlertDialog


import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.ViewModel
import java.nio.file.WatchEvent


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun strtINT(id:Int,state: TaskState,change : (TaskEvent)-> Unit){


    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Tasks") }, colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black
        )) },
        floatingActionButton = {
                IconButton(onClick = {state.isEditing = true}, colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White,
                    disabledContainerColor = Color.Black,
                    disabledContentColor = Color.Black
                )) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")

                }


        },
        floatingActionButtonPosition = FabPosition.End,
        containerColor = Color.White,
        contentColor = Color.Black
    ){
        if(state.isEditing){
            //Alert Box
            AlertDialog(onDismissRequest = { state.isEditing = false }) {
                Column {
                    if(id == 0){
                    Text(text = "ADD TASK")
                    Spacer(modifier = Modifier.padding(8.dp))
                    //HEADING OF TASK
                    OutlinedTextField(
                        value = state.title,
                        onValueChange = { change(TaskEvent.setTITLE(it)) },
                        label = { Text(text = "Task") })
                    Spacer(modifier = Modifier.padding(8.dp))
                    //HEADING OF DESCRIPTION
                    OutlinedTextField(
                        value = state.description,
                        onValueChange = { change(TaskEvent.setDescription(it)) },
                        label = { Text(text = "Description") })}
                    else{
                        Text(text = "Update Task")
                        Spacer(modifier = Modifier.padding(8.dp))
                        //HEADING OF TASK
                        OutlinedTextField(
                            value = state.title,
                            onValueChange = {change(TaskEvent.setTITLE(it)) },
                            label = { Text(text = "Task") })
                        Spacer(modifier = Modifier.padding(8.dp))
                        //HEADING OF DESCRIPTION
                        OutlinedTextField(
                            value = state.description,
                            onValueChange = { change(TaskEvent.setDescription(it)) },
                            label = { Text(text = "Description") })

                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                    Row(horizontalArrangement = Arrangement.SpaceBetween) {

                        if(id ==0){
                            //SAVE BUTTON
                        Button(
                            onClick = { state.isEditing = false
                                change(TaskEvent.SaveTask)
                                   },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Black,
                                contentColor = Color.White
                            )
                        ) {
                            Text(text = "Save")
                        }}
                        else{
                            //UPDATE BUTTON
                            Button(onClick = { state.isEditing = false
                                change(TaskEvent.updateTask(state.Tasks.get(id)))}) {
                                Text(text = "Update")

                            }


                        }
                        Spacer(modifier = Modifier.padding(55.dp))
                        //CANCEL BUTTON
                        Button(
                            onClick = { state.isEditing = false },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Black,
                                contentColor = Color.White
                            )
                        ) {
                            Text(text = "Cancel")


                        }
                    }
                }
        }}
        LazyColumn(modifier = Modifier.padding(it)){
            items(state.Tasks){
                item ->
                ElevatedCard(onClick = { state.isEditing = true },modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)) {
                    Column {
                        Text(text = item.Title,modifier = Modifier.padding(6.dp))
                        Text(text = item.Description,modifier = Modifier.padding(6.dp))
                    }
                }
            }
        }

    }

}


