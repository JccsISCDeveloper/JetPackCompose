package com.jccsisc.irepcp.ui.screens.todomodule.addtasks.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.core.constants.Constants.NO_VALUE
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
        FabDialog(viewModel, Modifier.align(Alignment.TopEnd))
        AddTasksDialog(
            show = showDialog,
            onDismish = { viewModel.onDialogClose() },
            onTaskAdded = { viewModel.onTasksCreated(it) }
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
        Dialog(onDismissRequest = onDismish) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(dimensionResource(id = R.dimen.padding_16))
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
                    singleLine = true,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.size(16.dp))
                Button(onClick = { onTaskAdded(myTask) }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Añadir tarea")
                }
            }
        }
    }
}