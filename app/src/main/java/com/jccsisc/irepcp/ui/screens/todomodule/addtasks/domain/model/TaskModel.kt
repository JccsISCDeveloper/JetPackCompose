package com.jccsisc.irepcp.ui.screens.todomodule.addtasks.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jccsisc.irepcp.core.constants.ConstantsRoom.TBL_TASK
import javax.annotation.Nonnull

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.todomodule.addtasks.ui.model
 * Created by Julio Cesar Camacho Silva on 02/12/22
 */
@Entity(tableName = TBL_TASK)
data class TaskModel(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "task") @Nonnull val task: String = "",
    @ColumnInfo(name = "selected") var selected: Boolean = false,
    @ColumnInfo(name = "modification_date") val modificationDate: Long = 0,
    @ColumnInfo(name = "title") @Nonnull  var title: String = "",
    @ColumnInfo(name = "priority_task") var priorityTask: Int = -1
)
