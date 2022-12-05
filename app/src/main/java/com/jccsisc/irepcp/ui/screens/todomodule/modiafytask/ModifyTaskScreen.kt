package com.jccsisc.irepcp.ui.screens.todomodule.modiafytask

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jccsisc.irepcp.ui.theme.GrayBg

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.todomodule.modiafytask
 * Created by Julio Cesar Camacho Silva on 05/12/22
 */
@Composable
fun ModifyTaskScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayBg)
    ) {
        Text(text = "Detalles de la tarea", style = MaterialTheme.typography.h3)
    }
}