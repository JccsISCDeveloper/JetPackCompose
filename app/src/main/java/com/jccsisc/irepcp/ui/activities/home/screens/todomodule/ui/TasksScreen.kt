package com.jccsisc.irepcp.ui.activities.home.screens.todomodule.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.*
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.core.constants.Constants.DATE_ASC
import com.jccsisc.irepcp.core.constants.Constants.DATE_DESC
import com.jccsisc.irepcp.core.constants.Constants.NO_VALUE
import com.jccsisc.irepcp.core.constants.Constants.PRIORITY_ASC
import com.jccsisc.irepcp.core.constants.Constants.PRIORITY_DESC
import com.jccsisc.irepcp.ui.activities.home.generalcomponents.ShowLottie
import com.jccsisc.irepcp.ui.activities.home.screens.todomodule.domain.model.TaskModel
import com.jccsisc.irepcp.ui.theme.*
import com.jccsisc.irepcp.utils.components.dialogs.GenericDialog
import com.jccsisc.irepcp.utils.lastModifiedTime

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
    val taskOrderVM: String by viewModel.taskOrder.observeAsState(initial = NO_VALUE)
    var showDialog by remember { mutableStateOf(false) }
    var task by remember { mutableStateOf(TaskModel()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayBg)
    ) {
        if (tasks.isNotEmpty()) {
            TaskList(
                taskOrderVM,
                tasks,
                viewModel,
                navigateToModifyTask
            ) { deleteTaskt ->
                showDialog = true
                task = deleteTaskt
            }
        } else {
            ShowLottie(
                lottie = R.raw.empty,
                text = stringResource(id = R.string.add_a_note),
                showText = true
            )
        }

        GenericDialog(
            show = showDialog,
            onDismiss = {},
            image = R.drawable.ic_warning,
            title = stringResource(id = R.string.delete_task),
            message = stringResource(id = R.string.str_are_you_sure_you_want_to_delete, "la tarea"),
            btnTitleNegative = stringResource(id = R.string.no),
            btnTitlePositive = stringResource(id = R.string.yes),
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

private const val PADDIN_58 = 58
private const val PADDIN_0 = 0
@Composable
private fun TaskList(
    taskOrder: String,
    tasks: List<TaskModel>,
    viewModel: TaskViewModel,
    navigateToModifyTask: (taskId: Long) -> Unit,
    onDeleteTask: (taskModel: TaskModel) -> Unit
) {
    var addPadding by remember { mutableStateOf(0) }
    Column(modifier = Modifier.padding(bottom = addPadding.dp)) {
        LazyColumn(modifier = Modifier.background(Color.Gray)) {

            itemsIndexed(getOrderList(taskOrder, tasks).sortedBy { it.selected }) { index, task ->
                CardTask(
                    taskModel = task,
                    onCheckBoxSelected = { viewModel.onSelectedTask(it) },
                    onUpdateTask = { viewModel.updateModelTask(it) },
                    onDeleteTask = { onDeleteTask(it) },
                    navigateToModifyTask = navigateToModifyTask
                )
                addPadding = if (index >= tasks.size -8) {
                    PADDIN_58
                } else {
                    PADDIN_0
                }
            }
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
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.rounded_0)),
        backgroundColor = Color.White,
        elevation = dimensionResource(id = R.dimen.elevation_0)
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen.width_5))
                    .height(dimensionResource(id = R.dimen.height_70))
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
                    text = taskModel.title.ifEmpty { NO_VALUE },
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.height_4)))
                Text(
                    text = taskModel.task,
                    fontWeight = if (taskModel.selected) FontWeight.Medium else FontWeight.Normal,
                    color = if (taskModel.selected) Color.Gray else Color.Black,
                    textDecoration = if (taskModel.selected) TextDecoration.LineThrough else TextDecoration.None
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.height_4)))
                Text(
                    text = if (taskModel.modificationDate != 0L) {
                        taskModel.modificationDate.lastModifiedTime()
                    } else {
                        taskModel.id.lastModifiedTime()
                    },
                    style = MaterialTheme.typography.caption
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.height_4)))
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
