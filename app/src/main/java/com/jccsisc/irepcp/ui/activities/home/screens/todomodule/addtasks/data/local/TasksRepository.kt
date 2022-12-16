package com.jccsisc.irepcp.ui.activities.home.screens.todomodule.addtasks.data.local

import com.jccsisc.irepcp.ui.activities.home.screens.todomodule.addtasks.domain.model.TaskModel
import com.jccsisc.irepcp.ui.activities.home.screens.todomodule.addtasks.domain.usecase.Tasks
import com.jccsisc.irepcp.ui.activities.home.screens.todomodule.addtasks.domain.usecase.UseCaseTasks
import kotlinx.coroutines.flow.Flow

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.todomodule.addtasks.data.local
 * Created by Julio Cesar Camacho Silva on 02/12/22
 */
class TasksRepository(private val taskDao: TaskDao): UseCaseTasks {

    override fun getTasksFromRoom(): Flow<Tasks> = taskDao.getTasks()
    override fun getTaskFromRoom(id: Long): TaskModel = taskDao.getTask(id)

    override fun addTaskToRoom(task: TaskModel) = taskDao.addTask(task)
    override fun updateTaskFromRoom(task: TaskModel) = taskDao.updateTask(task)
    override fun deleteTaskFromRoom(task: TaskModel) = taskDao.deleteTask(task)
}