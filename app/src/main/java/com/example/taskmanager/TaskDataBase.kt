package com.example.taskmanager

sealed interface TaskEvent {
    object SaveTask:TaskEvent
    data class setTITLE(val title: String):TaskEvent
    data class setDescription(val description:String):TaskEvent
    data class updateTask(val task: TaskData):TaskEvent
    data class deleteTASK(val task:TaskData):TaskEvent
    object showAlert : TaskEvent
    object hideAlert : TaskEvent
}