package com.jccsisc.irepcp.ui.screens.todomodule.addtasks.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Save
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.core.constants.Constants
import com.jccsisc.irepcp.core.constants.Constants.NO_VALUE
import com.jccsisc.irepcp.ui.screens.todomodule.addtasks.domain.model.TaskModel
import com.jccsisc.irepcp.ui.theme.GrayBg
import com.jccsisc.irepcp.ui.theme.PrimaryColor
import com.jccsisc.irepcp.ui.theme.PrimaryDarkColor
import com.jccsisc.irepcp.utils.lastModifiedTime
import com.jccsisc.irepcp.utils.timeMillisToFormatDate

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.todomodule.addtasks.ui
 * Created by Julio Cesar Camacho Silva on 08/12/22
 */
@Composable
fun AddOrModifyTaskScreen(
    taskId: Long = -1L,
    viewModel: TaskViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    var task by remember { mutableStateOf(TaskModel()) }
    var isNewaTask by remember { mutableStateOf(false) }

    isNewaTask = taskId == -1L

    if (!isNewaTask) {
        LaunchedEffect(Unit) {
            viewModel.getTask(taskId)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(
                title = if (isNewaTask) {
                    stringResource(id = R.string.new_task)
                } else {
                    stringResource(id = R.string.modify_task)
                },
                navigateBack = navigateBack,
                onSaveClick = {
                    if (isNewaTask) {
                        viewModel.addTask(task)
                    } else {
                        viewModel.updateTask(task)
                    }
                    navigateBack()
                }
            )
        }
    ) { padding ->
        ContentNewTask(
            modifier = Modifier.padding(padding),
            isNewTask = isNewaTask,
            taskModel = viewModel.taskVM,
            onCheckSelected = { viewModel.onTaskSelected(false) },
            updateTaskString = { viewModel.updateTask(it) },
            updateTaskTime = { viewModel.updateModifyTask(it) },
            addOrModifyTask = { task = it }
        )
    }
}

@Composable
private fun TopBar(
    title: String,
    navigateBack: () -> Unit,
    onSaveClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = title, textAlign = TextAlign.Center)
        },
        navigationIcon = {
            IconButton(onClick = navigateBack) {
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "ic arrow back")
            }
        },
        backgroundColor = PrimaryDarkColor,
        actions = {
            IconButton(onClick = { onSaveClick() }) {
                Icon(imageVector = Icons.Outlined.Save, contentDescription = "ic save")
            }
        }
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ContentNewTask(
    modifier: Modifier = Modifier,
    isNewTask: Boolean,
    taskModel: TaskModel,
    onCheckSelected: () -> Unit,
    updateTaskString: (task: String) -> Unit,
    updateTaskTime: (time: Long) -> Unit,
    addOrModifyTask: (TaskModel) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var taskString by remember { mutableStateOf(Constants.NO_VALUE) }
    var modificationTime by remember { mutableStateOf(0L) }
    val createdDate by remember { mutableStateOf(System.currentTimeMillis()) }
    var enableTask by remember { mutableStateOf(false) }

    Box {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_6))) {
                Text(
                    text = if (isNewTask) {
                        stringResource(id = R.string.editing)
                    } else {
                        if (enableTask) {
                            stringResource(id = R.string.editing)
                        } else {
                            if (taskModel.modificationDate != 0L) {
                                taskModel.modificationDate.lastModifiedTime()
                            } else {
                                NO_VALUE
                            }
                        }
                    },
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.subtitle2,
                    color = Color.Blue
                )
                Text(
                    text = if (isNewTask) {
                        createdDate.timeMillisToFormatDate()
                    } else {
                        taskModel.id.timeMillisToFormatDate()
                    },
                    style = MaterialTheme.typography.subtitle2,
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            TextField(
                value = if (isNewTask) taskString else taskModel.task,
                onValueChange = {
                    taskString = it
                    modificationTime = System.currentTimeMillis()
                    if (isNewTask) {
                        val newTask =
                            TaskModel(id = createdDate, task = taskString, selected = false)
                        addOrModifyTask(newTask)
                    } else {
                        var updateModifyDate = 0L
                        updateModifyDate = if (modifyTask(taskString, taskModel.task)) {
                            modificationTime
                        } else {
                            taskModel.modificationDate
                        }
                        updateTaskString(taskString)
                        updateTaskTime(updateModifyDate)
                        addOrModifyTask(taskModel)
                    }
                },
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        onCheckSelected()
                        enableTask = isNewTask || !taskModel.selected
                    },
                enabled = enableTask,
                placeholder = { Text(text = stringResource(id = R.string.ph_write_your_note)) },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = GrayBg,
                    cursorColor = PrimaryColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledTrailingIconColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    capitalization = KeyboardCapitalization.Sentences
                ),
                keyboardActions = KeyboardActions(
                    //todo en este caso no aplica pero así se ocultaría teclado
                    onSend = { keyboardController?.hide() }
                )
            )
        }
        Text(
            text = stringResource(
                id = R.string.str_number_of_characteres,
                if (isNewTask) taskString.length else taskModel.task.length
            ),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    bottom = dimensionResource(id = R.dimen.padding_6),
                    end = dimensionResource(id = R.dimen.padding_6)
                ),
            style = MaterialTheme.typography.caption
        )
    }
}

fun modifyTask(nowTask: String, lastTask: String): Boolean = nowTask.length != lastTask.length
