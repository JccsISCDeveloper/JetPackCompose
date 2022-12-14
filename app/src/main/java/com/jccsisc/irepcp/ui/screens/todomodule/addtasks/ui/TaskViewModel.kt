package com.jccsisc.irepcp.ui.screens.todomodule.addtasks.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val _taskVM = MutableLiveData(TaskModel())
    val taskVM: LiveData<TaskModel> = _taskVM

    fun addModelTask(task: TaskModel) = viewModelScope.launch(Dispatchers.IO) {
        repo.addTaskToRoom(task)
    }

    fun getTask(id: Long) = viewModelScope.launch(Dispatchers.IO) {
        _taskVM.postValue(repo.getTaskFromRoom(id))
    }

    fun onSetTitleTask(title: String) {
        _taskVM.value = _taskVM.value?.copy(title = title)
    }

    fun updateTask(task: String) {
        _taskVM.value = taskVM.value?.copy(task = task)
    }

    fun updateModificationTimeTask(time: Long) {
        _taskVM.value = taskVM.value?.copy(modificationDate = time)
    }

    fun onSelectedTask(selected: Boolean) {
        _taskVM.value = taskVM.value?.copy(selected = selected)
    }

    fun onSelectedPriorityTask(priority: Int) {
        _taskVM.value = taskVM.value?.copy(priorityTask = priority)
    }

    fun updateModelTask(task: TaskModel) = viewModelScope.launch(Dispatchers.IO) {
        repo.updateTaskFromRoom(task)
    }

    fun deleteTask(taskModel: TaskModel) {
        viewModelScope.launch(Dispatchers.IO) { repo.deleteTaskFromRoom(taskModel) }
    }

}