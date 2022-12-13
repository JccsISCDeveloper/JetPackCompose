package com.jccsisc.irepcp.ui.screens.todomodule.addtasks.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
    val tasks = repo.getTasksFromRoom()
    var taskVM by mutableStateOf(TaskModel(0L,task = NO_VALUE, false, 0L))

    fun addTask(task: TaskModel) = viewModelScope.launch(Dispatchers.IO) {
        repo.addTaskToRoom(task)
    }

    fun getTask(id: Long) = viewModelScope.launch(Dispatchers.IO) {
        taskVM = repo.getTaskFromRoom(id)
    }

    fun updateTask(task: String) {
        taskVM = taskVM.copy(task = task)
    }

    fun updateModifyTask(time: Long) {
        taskVM = taskVM.copy(modificationDate = time)
    }

    fun onTaskSelected(selected: Boolean) {
        taskVM = taskVM.copy(selected = selected)
    }

    fun onPrioritySelected(priority: Int) {
        taskVM = taskVM.copy(priorityTask = priority)
    }

    fun updateTask(task: TaskModel) = viewModelScope.launch(Dispatchers.IO) {
        repo.updateTaskFromRoom(task)
    }

    fun deleteTask(taskModel: TaskModel) {
        viewModelScope.launch(Dispatchers.IO) { repo.deleteTaskFromRoom(taskModel) }
    }

}