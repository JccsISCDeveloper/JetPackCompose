package com.jccsisc.irepcp.ui.screens.todomodule.addtasks.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.ui.screens.todomodule.addtasks.domain.model.TaskModel
import com.jccsisc.irepcp.ui.theme.*
import com.jccsisc.irepcp.utils.components.dialogs.GenericDialog

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
    var showDialog by remember { mutableStateOf(false) }
    var task by remember { mutableStateOf(TaskModel()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayBg)
            .padding(bottom = 60.dp)
    ) {
        TaskList(tasks, viewModel,navigateToModifyTask) { deleteTaskt ->
            showDialog = true
            task = deleteTaskt
        }
        GenericDialog(
            show = showDialog,
            onDismiss = {},
            image = R.drawable.ic_info_circle,
            title = "Eliminar tarea",
            message = "¿Está seguro que desea eliminar la tarea?",
            btnTitleNegative = "Cancelar",
            btnTitlePositive = "Eliminar",
            onNegativeClick = { showDialog = false },
            onPositiveClick = {
                viewModel.deleteTask(task)
                showDialog = false
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewTasks() {
//    TasksScreen()
}

@Composable
private fun TaskList(
    tasks: List<TaskModel>,
    viewModel: TaskViewModel,
    navigateToModifyTask: (taskId: Long) -> Unit,
    onDeleteTask: (taskModel: TaskModel) -> Unit
) {
    LazyColumn(modifier = Modifier.background(Color.Gray)) {
        items(tasks.sortedBy { it.selected }, key = { it.id }) { task ->
            CardTask(
                taskModel = task,
                onCheckBoxSelected = { viewModel.onTaskSelected(it) },
                onUpdateTask = { viewModel.updateTask(it) },
                onDeleteTask = { onDeleteTask(it) },
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
                horizontal = dimensionResource(id = R.dimen.padding_6)
            )
            .combinedClickable(
                onClick = { navigateToModifyTask(taskModel.id) },
                onLongClick = { onDeleteTask(taskModel) }
            ),
        shape = RoundedCornerShape(0.dp),
        backgroundColor = Color.White,
        elevation = 0.dp
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.width(5.dp).height(60.dp).background(
                when(taskModel.priorityTask) {
                    0 -> ColorRed
                    1 -> ColorOrange
                    else -> {
                        ColorYellow
                    }
                }
            ))
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
                    val modifyTask = TaskModel(taskModel.id, taskModel.task, selected, taskModel.modificationDate)
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
