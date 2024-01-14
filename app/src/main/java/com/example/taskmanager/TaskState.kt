package com.example.taskmanager

import kotlinx.coroutines.flow.Flow

data class TaskState (
    var Tasks : List<TaskData> = emptyList(),
    var isEditing : Boolean = false,
    var title : String = "",
    val description  : String = ""
    )