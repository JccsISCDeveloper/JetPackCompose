package com.jccsisc.irepcp.ui.screens.todomodule.addtasks.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jccsisc.irepcp.ui.screens.todomodule.addtasks.ui.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.todomodule.addtasks.ui
 * Created by Julio Cesar Camacho Silva on 02/12/22
 */
@HiltViewModel
class TaskViewModel  @Inject constructor(): ViewModel() {
    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    private val _tasks = mutableStateListOf<TaskModel>()
    val tasks: List<TaskModel> = _tasks

    fun onDialogClose() { _showDialog.value = false }
    fun onTasksCreated(task: String) {
        _showDialog.value = false
        _tasks.add(TaskModel(task = task))
    }

    fun onDialogShow() {
        _showDialog.value = true
    }

    fun onCheckBoxSelected(taskModel: TaskModel) {
        val index = _tasks.indexOf(taskModel)
        _tasks[index] = _tasks[index].let {
            it.copy(selected = !it.selected)
        }
    }

    fun onDeleteTask(taskModel: TaskModel) {
        val task = _tasks.find { it.id == taskModel.id }
        _tasks.remove(task)
    }

}