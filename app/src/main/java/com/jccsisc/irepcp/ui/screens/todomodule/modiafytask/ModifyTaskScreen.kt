package com.jccsisc.irepcp.ui.screens.todomodule.modiafytask

import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.ui.generalcomponents.GenericTopBar
import com.jccsisc.irepcp.ui.screens.todomodule.addtasks.domain.model.TaskModel
import com.jccsisc.irepcp.ui.screens.todomodule.addtasks.ui.TaskViewModel
import com.jccsisc.irepcp.ui.theme.GrayBg
import com.jccsisc.irepcp.ui.theme.PrimaryDarkColor

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.todomodule.modiafytask
 * Created by Julio Cesar Camacho Silva on 05/12/22
 */
@Composable
fun ModifyTaskScreen(
    viewModel: TaskViewModel = hiltViewModel(),
    taskId: Long,
    navigateBack: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getTask(taskId)
    }
    Scaffold(
        topBar = {
            GenericTopBar(
                navigateBack = navigateBack,
                title = "Modificar tarea"
            )
        },
        content = { padding ->
            DetailTaskContent(
                padding = padding,
                task = viewModel.taskVM,
                onCheckSelected = { viewModel.onTaskSelected(false) },
                updateTaskString = { task -> viewModel.updateTask(task) },
                updateTask = { taskModel -> viewModel.updateTask(taskModel) },
                navigateBack = navigateBack
            )
        }
    )
}

@Composable
fun DetailTaskContent(
    padding: PaddingValues,
    task: TaskModel,
    onCheckSelected: () -> Unit,
    updateTaskString: (task: String) -> Unit,
    updateTask: (task: TaskModel) -> Unit,
    navigateBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = task.task,
            onValueChange = { updateTaskString(it) },
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.padding_10))
                .clickable { onCheckSelected() },
            enabled = !task.selected,
            placeholder = { Text(text = "Tarea") },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = GrayBg,
                cursorColor = PrimaryDarkColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        Button(onClick = {
            updateTask(task)
            navigateBack()
        },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(dimensionResource(id = R.dimen.padding_16))
        ) {
            Text(text = "Actualizar")
        }
    }
}