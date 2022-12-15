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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.core.constants.Constants.DATE_ASC
import com.jccsisc.irepcp.core.constants.Constants.DATE_DESC
import com.jccsisc.irepcp.core.constants.Constants.NO_VALUE
import com.jccsisc.irepcp.core.constants.Constants.PRIORITY_ASC
import com.jccsisc.irepcp.core.constants.Constants.PRIORITY_DESC
import com.jccsisc.irepcp.ui.screens.todomodule.addtasks.domain.model.TaskModel
import com.jccsisc.irepcp.ui.theme.*
import com.jccsisc.irepcp.utils.components.dialogs.GenericDialog
import com.jccsisc.irepcp.utils.lastModifiedTime
import com.jccsisc.irepcp.utils.showToast

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.todomodule.addtasks.ui
 * Created by Julio Cesar Camacho Silva on 02/12/22
 */
@Composable
fun TasksScreen(
    viewModel: TaskViewModel = hiltViewModel(),
    navigateToModifyTask: (taskId: Long) -> Unit) {
    val tasks by viewModel.tasks.collectAsState(initial = emptyList())
    val taskOrderVM: String by viewModel.taskOrder.observeAsState(initial = "")
    var showDialog by remember { mutableStateOf(false) }
    var task by remember { mutableStateOf(TaskModel()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayBg)
            .padding(bottom = 60.dp)
    ) {
        TaskList(
            taskOrderVM,
            tasks,
            viewModel,
            navigateToModifyTask
        ) { deleteTaskt ->
            showDialog = true
            task = deleteTaskt
        }
        GenericDialog(
            show = showDialog,
            onDismiss = {},
            image = R.drawable.ic_warning,
            title = "Eliminar tarea",
            message = "¿Está seguro que desea eliminar la tarea?",
            btnTitleNegative = "No",
            btnTitlePositive = "Sí",
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
    taskOrder: String,
    tasks: List<TaskModel>,
    viewModel: TaskViewModel,
    navigateToModifyTask: (taskId: Long) -> Unit,
    onDeleteTask: (taskModel: TaskModel) -> Unit
) {
    LazyColumn(modifier = Modifier.background(Color.Gray)) {

        items(getOrderList(taskOrder, tasks).sortedBy { it.selected }) { task ->
            CardTask(
                taskModel = task,
                onCheckBoxSelected = { viewModel.onSelectedTask(it) },
                onUpdateTask = { viewModel.updateModelTask(it) },
                onDeleteTask = { onDeleteTask(it) },
                navigateToModifyTask = navigateToModifyTask
            )
        }
    }
}

//todo moverl al viewModel
private fun getOrderList(taskOrder: String, tasks: List<TaskModel>) = when (taskOrder) {
    DATE_DESC -> tasks.sortedByDescending { it.id }
    DATE_ASC -> tasks.sortedBy { it.id }
    PRIORITY_DESC -> tasks.sortedByDescending { it.priorityTask }
    PRIORITY_ASC -> tasks.sortedBy { it.priorityTask }
    else -> {
        tasks
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
    var dateTask by remember { mutableStateOf(NO_VALUE) }

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
            Box(
                modifier = Modifier
                    .width(5.dp)
                    .height(70.dp)
                    .background(
                        getColorPriority(priority = taskModel.priorityTask)
                    )
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = dimensionResource(id = R.dimen.padding_6))
            ) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = taskModel.title.ifEmpty { "Sin título" },
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = taskModel.task,
                    fontWeight = if (taskModel.selected) FontWeight.Medium else FontWeight.Normal,
                    color = if (taskModel.selected) Color.Gray else Color.Black,
                    textDecoration = if (taskModel.selected) TextDecoration.LineThrough else TextDecoration.None
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = if (taskModel.modificationDate != 0L) {
                        taskModel.modificationDate.lastModifiedTime()
                    } else {
                        taskModel.id.lastModifiedTime()
                    },
                    style = MaterialTheme.typography.caption
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
            Checkbox(
                checked = taskModel.selected,
                onCheckedChange = {
                    selected = it
                    onCheckBoxSelected(selected)
                    val modifyTask = TaskModel(
                        taskModel.id,
                        taskModel.task,
                        selected,
                        taskModel.modificationDate,
                        taskModel.title,
                        taskModel.priorityTask
                    )
                    onUpdateTask(modifyTask)
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = PrimaryColor,
                    uncheckedColor = Color.Gray,
                    checkmarkColor = GrayBg,
                    disabledColor = Color.Gray
                )
            )
        }
    }
}
