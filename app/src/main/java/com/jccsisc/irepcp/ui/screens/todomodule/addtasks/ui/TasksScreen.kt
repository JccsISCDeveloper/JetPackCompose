package com.jccsisc.irepcp.ui.screens.todomodule.addtasks.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.core.constants.Constants.NO_VALUE
import com.jccsisc.irepcp.ui.screens.todomodule.addtasks.ui.model.TaskModel
import com.jccsisc.irepcp.ui.theme.GrayBg

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.todomodule.addtasks.ui
 * Created by Julio Cesar Camacho Silva on 02/12/22
 */
@Composable
fun TasksScreen(viewModel: TaskViewModel = hiltViewModel()) {
    val showDialog: Boolean by viewModel.showDialog.observeAsState(false)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayBg)
    ) {
        AddTasksDialog(
            show = showDialog,
            onDismish = { viewModel.onDialogClose() },
            onTaskAdded = { viewModel.onTasksCreated(it) }
        )
        TaskList(viewModel)
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
    TasksScreen()
}

@Composable
fun FabDialog(viewModel: TaskViewModel, modifier: Modifier) {
    FloatingActionButton(
        onClick = { viewModel.onDialogShow() },
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding_10))
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "ic add")
    }
}

@Composable
private fun AddTasksDialog(show: Boolean, onDismish: () -> Unit, onTaskAdded: (String) -> Unit) {
    var myTask by remember { mutableStateOf(NO_VALUE) }
    if (show) {
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
                        value = myTask,
                        onValueChange = { myTask = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    Button(
                        onClick = {
                            onTaskAdded(myTask)
                            myTask = NO_VALUE
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
private fun TaskList(viewModel: TaskViewModel) {
    val myTasks: List<TaskModel> = viewModel.tasks
    LazyColumn {
        items(myTasks, key = { it.id }) { task ->
            CardTask(
                taskModel = task,
                onCheckBoxSelected = { viewModel.onCheckBoxSelected(it) },
                onDeleteTask = { viewModel.onDeleteTask(it)}
            )
        }
    }
}

@Composable
fun CardTask(
    taskModel: TaskModel,
    onCheckBoxSelected: (taskModel: TaskModel) -> Unit,
    onDeleteTask: (taskModel: TaskModel) -> Unit
) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(
                vertical = dimensionResource(id = R.dimen.padding_3),
                horizontal = dimensionResource(id = R.dimen.padding_10)
            )
            .pointerInput(Unit) {
                detectTapGestures(onLongPress = {
                    onDeleteTask(taskModel)
                })
            },
        backgroundColor = Color.White,
        elevation = 6.dp
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = taskModel.task, modifier = Modifier
                    .weight(1f)
                    .padding(start = dimensionResource(id = R.dimen.padding_6))
            )
            Checkbox(
                checked = taskModel.selected,
                onCheckedChange = { onCheckBoxSelected(taskModel) }
            )
        }
    }
}
