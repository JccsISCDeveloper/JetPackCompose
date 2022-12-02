package com.jccsisc.irepcp.ui.screens.todomodule.addtasks.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.jccsisc.irepcp.ui.theme.GrayBg

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.todomodule.addtasks.ui
 * Created by Julio Cesar Camacho Silva on 02/12/22
 */
@Composable
fun TasksScreen(viewModel: TaskViewModel = hiltViewModel()) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(GrayBg), contentAlignment = Alignment.Center) {
        Text(text = "TASK")
    }
}