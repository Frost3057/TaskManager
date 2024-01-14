package com.example.taskmanager

import androidx.compose.runtime.mutableStateOf
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "TaskData")
data class TaskData(
    @PrimaryKey(autoGenerate = true)
    var id: Int= 0,
    var Title:String,
    var Description : String)


