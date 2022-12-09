package com.jccsisc.irepcp.ui.screens.todomodule.addtasks.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jccsisc.irepcp.core.constants.ConstantsRoom.TBL_TASK

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.todomodule.addtasks.ui.model
 * Created by Julio Cesar Camacho Silva on 02/12/22
 */
@Entity(tableName = TBL_TASK)
data class TaskModel(
    @PrimaryKey
    val id: Long = System.currentTimeMillis(),
    val task: String = "",
    var selected: Boolean = false,
)
