package com.jccsisc.irepcp.ui.activities.home.screens.todomodule.domain.usecase

import com.jccsisc.irepcp.ui.activities.home.screens.todomodule.domain.model.TaskModel
import kotlinx.coroutines.flow.Flow

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.todomodule.addtasks.domain.usecase
 * Created by Julio Cesar Camacho Silva on 02/12/22
 */
typealias Tasks = List<TaskModel>
interface UseCaseTasks {
    fun getTasksFromRoom(): Flow<Tasks>
    fun getTaskFromRoom(id: Long): TaskModel
    fun addTaskToRoom(task: TaskModel)
    fun updateTaskFromRoom(task: TaskModel)
    fun deleteTaskFromRoom(task: TaskModel)
}