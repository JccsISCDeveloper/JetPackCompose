package com.jccsisc.irepcp.ui.screens.todomodule.addtasks.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

    fun onDialogClose() { _showDialog.value = false }
    fun onTasksCreated(task: String) {
        _showDialog.value = false
        Log.i("log", task)
    }

    fun onDialogShow() {
        _showDialog.value = true
    }


}