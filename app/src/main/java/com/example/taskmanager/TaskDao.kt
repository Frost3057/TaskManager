package com.example.taskmanager

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
@Dao
interface TaskDao {
    @Insert
    fun addTask(task:TaskData)

    @Update
    fun updateTask(task:TaskData)

    @Delete
    fun delTask(task: TaskData)

    @Update
    fun update(task:TaskData)

    @Query("SELECT * FROM TaskData")
    fun showtasks():Flow<List<TaskData>>
}