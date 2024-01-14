package com.example.taskmanager

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(
    entities = [TaskData::class],
    version = 1
)
abstract class TaskDB:RoomDatabase() {
    abstract val dao :TaskDao
}