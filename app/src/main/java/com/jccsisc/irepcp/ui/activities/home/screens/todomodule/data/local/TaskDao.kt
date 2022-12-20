package com.jccsisc.irepcp.ui.activities.home.screens.todomodule.data.local

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.jccsisc.irepcp.core.constants.ConstantsRoom.TBL_TASK
import com.jccsisc.irepcp.ui.activities.home.screens.todomodule.domain.model.TaskModel
import com.jccsisc.irepcp.ui.activities.home.screens.todomodule.domain.usecase.Tasks
import kotlinx.coroutines.flow.Flow

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.todomodule.addtasks.data.local
 * Created by Julio Cesar Camacho Silva on 02/12/22
 */
@Dao
interface TaskDao {
    @Query("SELECT * FROM $TBL_TASK ORDER BY id DESC")
    fun getTasks(): Flow<Tasks>

    @Query("SELECT * FROM $TBL_TASK WHERE id = :id")
    fun getTask(id: Long): TaskModel

    @Insert(onConflict = REPLACE)
    fun addTask(taskModel: TaskModel)

    @Update
    fun updateTask(taskModel: TaskModel)

    @Delete
    fun deleteTask(taskModel: TaskModel)
}