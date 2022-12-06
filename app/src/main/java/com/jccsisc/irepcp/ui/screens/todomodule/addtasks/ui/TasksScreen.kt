package com.jccsisc.irepcp.ui.screens.todomodule.addtasks.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.core.constants.Constants.NO_VALUE
import com.jccsisc.irepcp.ui.screens.todomodule.addtasks.domain.model.TaskModel
import com.jccsisc.irepcp.ui.theme.Gray50
import com.jccsisc.irepcp.ui.theme.GrayBg
import com.jccsisc.irepcp.ui.theme.PrimaryColor

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.todomodule.addtasks.ui
 * Created by Julio Cesar Camacho Silva on 02/12/22
 */
@Composable
fun TasksScreen(
    viewModel: TaskViewModel = hiltViewModel(),
    navigateToModifyTask: (taskId: Long) -> Unit
) {
    val tasks by viewModel.tasks.collectAsState(initial = emptyList())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayBg)
    ) {
        AddTasksDialog(
            show = viewModel.openDialog,
            onDismish = { viewModel.closeDialog() },
            addTask = { task ->
                viewModel.addTask(task)
            }
        )
        TaskList(tasks, viewModel, navigateToModifyTask)
        FabDialog(
            viewModel,
            Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 40.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewTasks() {
//    TasksScreen()
}

@Composable
fun FabDialog(viewModel: TaskViewModel, modifier: Modifier) {
    FloatingActionButton(
        onClick = { viewModel.openDialog() },
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding_10))
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "ic add")
    }
}

@Composable
private fun AddTasksDialog(show: Boolean, onDismish: () -> Unit, addTask: (TaskModel) -> Unit) {
    if (show) {
        var task by remember { mutableStateOf(NO_VALUE) }
        val selected by remember { mutableStateOf(false) }

        Dialog(onDismissRequest = { }) {
            Box(modifier = Modifier.background(Color.White)) {
                IconButton(onClick = onDismish, modifier = Modifier.align(Alignment.TopEnd)) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "ic close"
                    )
                }
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(
                            top = dimensionResource(id = R.dimen.padding_16),
                            start = dimensionResource(id = R.dimen.padding_10),
                            end = dimensionResource(id = R.dimen.padding_10),
                            bottom = dimensionResource(id = R.dimen.padding_10)
                        )
                ) {
                    Text(
                        text = "Añade tu tarea",
                        style = MaterialTheme.typography.subtitle1,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    TextField(
                        value = task,
                        onValueChange = { task = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    Button(
                        onClick = {
                            onDismish()
                            val newTask = TaskModel(task = task, selected = selected)
                            addTask(newTask)
                        }, modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = dimensionResource(id = R.dimen.padding_10))
                    ) {
                        Text(text = "Añadir tarea")
                    }
                }
            }
        }
    }
}

@Composable
private fun TaskList(
    tasks: List<TaskModel>,
    viewModel: TaskViewModel,
    navigateToModifyTask: (taskId: Long) -> Unit
) {
    LazyColumn {
        items(tasks, key = { it.id }) { task ->
            CardTask(
                taskModel = task,
                onCheckBoxSelected = { viewModel.onTaskSelected(it) },
                onUpdateTask = { viewModel.updateTask(it) },
                onDeleteTask = { viewModel.deleteTask(it) },
                navigateToModifyTask = navigateToModifyTask
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardTask(
    taskModel: TaskModel,
    onCheckBoxSelected: (selected: Boolean) -> Unit,
    onUpdateTask: (taskModel: TaskModel) -> Unit,
    onDeleteTask: (taskModel: TaskModel) -> Unit,
    navigateToModifyTask: (taskId: Long) -> Unit
) {
    var selected by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = dimensionResource(id = R.dimen.padding_3),
                horizontal = dimensionResource(id = R.dimen.padding_10)
            )
            .combinedClickable(
                onClick = { navigateToModifyTask(taskModel.id) },
                onLongClick = {
                    //todo abrir un dialog para confirmar eliminar tarea
                    onDeleteTask(taskModel)
                }
            ),
        backgroundColor = Color.White,
        elevation = 6.dp
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = taskModel.task, modifier = Modifier
                    .weight(1f)
                    .padding(start = dimensionResource(id = R.dimen.padding_6)),
                fontWeight = if (taskModel.selected) FontWeight.Medium else FontWeight.Normal,
                color = if (taskModel.selected) Color.Gray else Color.Black,
                textDecoration = if (taskModel.selected) TextDecoration.LineThrough else TextDecoration.None
            )
            Checkbox(
                checked = taskModel.selected,
                onCheckedChange = {
                    selected = it
                    onCheckBoxSelected(selected)
                    val modifyTask = TaskModel(taskModel.id, taskModel.task, selected)
                    onUpdateTask(modifyTask)
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = PrimaryColor,
                    uncheckedColor = Gray50,
                    checkmarkColor = GrayBg,
                    disabledColor = Color.Gray
                )
            )
        }
    }
}
