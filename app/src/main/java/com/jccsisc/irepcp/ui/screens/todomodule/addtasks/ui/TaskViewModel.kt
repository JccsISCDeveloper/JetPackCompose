package com.jccsisc.irepcp.ui.screens.todomodule.addtasks.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jccsisc.irepcp.core.constants.Constants.NO_VALUE
import com.jccsisc.irepcp.ui.screens.todomodule.addtasks.domain.model.TaskModel
import com.jccsisc.irepcp.ui.screens.todomodule.addtasks.domain.usecase.UseCaseTasks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.todomodule.addtasks.ui
 * Created by Julio Cesar Camacho Silva on 02/12/22
 */
@HiltViewModel
class TaskViewModel  @Inject constructor(private val repo: UseCaseTasks): ViewModel() {
    var openDialog by mutableStateOf(false)
    val tasks = repo.getTasksFromRoom()
    var taskVM by mutableStateOf(TaskModel(0L,task = NO_VALUE, false))

    fun addTask(task: TaskModel) = viewModelScope.launch(Dispatchers.IO) {
        repo.addTaskToRoom(task)
    }

    fun getTask(id: Long) = viewModelScope.launch(Dispatchers.IO) {
        taskVM = repo.getTaskFromRoom(id)
    }

    fun updateTask(task: String) {
        taskVM = taskVM.copy(task = task)
    }

    fun onTaskSelected(selected: Boolean) {
        taskVM = taskVM.copy(selected = selected)
    }

    fun updateTask(task: TaskModel) = viewModelScope.launch(Dispatchers.IO) {
        repo.updateTaskFromRoom(task)
    }

    fun deleteTask(taskModel: TaskModel) {
        /* val task = _tasks.find { it.id == taskModel.id }
         _tasks.remove(task)*/
        viewModelScope.launch(Dispatchers.IO) { repo.deleteTaskFromRoom(taskModel) }
    }

    fun closeDialog() { openDialog = false }
    fun openDialog() { openDialog = true }

  /*  fun onCheckBoxSelected(taskModel: TaskModel) {
        val index = _tasks.indexOf(taskModel)
        _tasks[index] = _tasks[index].let {
            it.copy(selected = !it.selected)
        }

    }*/

}