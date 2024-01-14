package com.example.taskmanager

import android.icu.text.CaseMap.Title
import android.util.EventLogTags.Description
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.copy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class taskViewModel(private val dao:TaskDao):ViewModel() {

    val _state = MutableStateFlow(TaskState())


    fun onChange(change:TaskEvent){
        when(change){
            TaskEvent.SaveTask -> {
                viewModelScope.launch {
                    val title = _state.value.title
                    val description = _state.value.title
                    val task = TaskData(Title = title, Description = description)

                    viewModelScope.launch{
                        dao.addTask(task)
                    }

                    _state.update {
                        it.copy(
                            isEditing = false,
                            title = "",
                            description = ""

                        )
                    }



                }
            }
            is TaskEvent.updateTask ->{
                viewModelScope.launch {
                    dao.updateTask(change.task)
                }
            }
            is TaskEvent.deleteTASK -> {
                viewModelScope.launch {
                    dao.delTask(change.task)
                }
            }
            TaskEvent.hideAlert -> {
                _state.update {
                    it.copy(
                        isEditing = false

                    )
                }


            }
            is TaskEvent.setDescription -> {
                _state.update {
                    it.copy(
                        description = change.description
                    )
                }

            }
            is TaskEvent.setTITLE -> {
                _state.update {
                    it.copy(
                        title = change.title
                    )
                }

            }
            TaskEvent.showAlert -> {
                _state.update {
                    it.copy(
                        isEditing = true
                    )
                }
            }
        }



    }







}