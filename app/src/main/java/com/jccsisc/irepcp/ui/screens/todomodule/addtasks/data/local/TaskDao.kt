package com.jccsisc.irepcp.ui.screens.todomodule.addtasks.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.jccsisc.irepcp.core.constants.ConstantsRoom.TBL_TASK
import com.jccsisc.irepcp.ui.screens.todomodule.addtasks.domain.model.TaskModel
import com.jccsisc.irepcp.ui.screens.todomodule.addtasks.domain.usecase.Tasks
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