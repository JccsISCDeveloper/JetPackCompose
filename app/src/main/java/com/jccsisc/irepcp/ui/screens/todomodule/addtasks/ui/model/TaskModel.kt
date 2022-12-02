package com.jccsisc.irepcp.ui.screens.todomodule.addtasks.ui.model

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.todomodule.addtasks.ui.model
 * Created by Julio Cesar Camacho Silva on 02/12/22
 */
data class TaskModel(
    val id: Long = System.currentTimeMillis(),
    val task: String,
    var selected: Boolean = false,
)
