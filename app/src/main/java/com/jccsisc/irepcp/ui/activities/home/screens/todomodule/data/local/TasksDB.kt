package com.jccsisc.irepcp.ui.activities.home.screens.todomodule.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jccsisc.irepcp.core.constants.ConstantsRoom.DB_TASKS_VERSION
import com.jccsisc.irepcp.ui.activities.home.screens.todomodule.domain.model.TaskModel

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.todomodule.addtasks.data.local
 * Created by Julio Cesar Camacho Silva on 02/12/22
 */
@Database(entities = [TaskModel::class], version = DB_TASKS_VERSION, exportSchema = false)
abstract class TasksDB: RoomDatabase() {
    abstract fun taskDao(): TaskDao
}