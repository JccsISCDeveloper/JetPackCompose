package com.jccsisc.irepcp.ui.activities.home.screens.todomodule.addtasks.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Save
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.core.constants.Constants
import com.jccsisc.irepcp.core.constants.Constants.HIGH_PRIORITY
import com.jccsisc.irepcp.core.constants.Constants.LOW_PRIORITY
import com.jccsisc.irepcp.core.constants.Constants.MEDIUM_PRIORITY
import com.jccsisc.irepcp.core.constants.Constants.NO_VALUE
import com.jccsisc.irepcp.ui.activities.home.screens.todomodule.addtasks.domain.model.TaskModel
import com.jccsisc.irepcp.ui.theme.*
import com.jccsisc.irepcp.utils.SetNavbarColor
import com.jccsisc.irepcp.utils.lastModifiedTime
import com.jccsisc.irepcp.utils.showToast
import com.jccsisc.irepcp.utils.timeMillisToFormatDate
import java.util.*

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
    val taskModel by viewModel.taskVM.observeAsState(initial = TaskModel())
    var isNewaTask by remember { mutableStateOf(false) }
    var priorityTask by remember { mutableStateOf(-1) }
    var showPriorityMenu by remember { mutableStateOf(false) }

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
                isNewTask = isNewaTask,
                navigateBack = navigateBack,
                taskModel = taskModel,
                showMenu = showPriorityMenu,
                onSetTitle = { viewModel.onSetTitleTask(it) },
                onPriorityClick = {
                    showPriorityMenu = false
                    priorityTask = it
                    viewModel.onSelectedPriorityTask(priorityTask)
                },
                onSaveClick = {
                    if (isNewaTask) {
                        if (priorityTask != -1) {
                            viewModel.addModelTask(taskModel)
                            navigateBack()
                        } else {
                            showPriorityMenu = true
                            showToast("Selecciona la prioridad de la tarea")
                        }
                    } else {
                        viewModel.updateModelTask(taskModel)
                        navigateBack()
                    }
                }
            )
        }
    ) { padding ->
        ContentNewTask(
            modifier = Modifier
                .background(GrayBg)
                .padding(padding),
            isNewTask = isNewaTask,
            taskModel,
            setSelectedCheckbx = { viewModel.onSelectedTask(false) },
            setdateTaskString = { viewModel.updateTask(it) },
            setdateTaskTime = { viewModel.updateModificationTimeTask(it) }
        )
    }
}

@Composable
private fun TopBar(
    title: String,
    isNewTask: Boolean,
    navigateBack: () -> Unit,
    taskModel: TaskModel,
    showMenu: Boolean = false,
    onSetTitle: (String) -> Unit,
    onPriorityClick: (priority: Int) -> Unit,
    onSaveClick: () -> Unit
) {
    var show by remember { mutableStateOf(false) }
    var titleTapBar by remember { mutableStateOf(NO_VALUE) }
    var priorityTask by remember { mutableStateOf(-1) }

    priorityTask = taskModel.priorityTask
    show = showMenu

    SetNavbarColor(color = getColorPriority(true, priorityTask), useDarkIcons = false)

    TopAppBar(
        title = {
            TextField(
                value = if (isNewTask) titleTapBar else taskModel.title,
                onValueChange = {
                    titleTapBar = it
                    onSetTitle(titleTapBar)
                },
                enabled = true,
                textStyle = MaterialTheme.typography.subtitle1,
                label = { Text(text = title) },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = GrayBg,
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = GrayBg
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    capitalization = KeyboardCapitalization.Sentences
                )
            )
        },
        navigationIcon = {
            IconButton(onClick = navigateBack) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "ic arrow back",
                    tint = GrayBg
                )
            }
        },
        backgroundColor = getColorPriority(true, priorityTask),
        actions = {
            IconButton(onClick = {
                show = !show
            }) {
                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .background(
                            shape = RoundedCornerShape(6.dp),
                            color = getColorPriority(priority = priorityTask)
                        )
                )
            }
            DropdownMenu(expanded = show, onDismissRequest = { show = false }) {
                DropdownMenuItem(onClick = {
                    priorityTask = LOW_PRIORITY
                    onPriorityClick(priorityTask)
                    show = false
                }) {
                    shapePriorityMenu(LOW_PRIORITY, stringResource(id = R.string.low_priority))
                }
                DropdownMenuItem(onClick = {
                    priorityTask = MEDIUM_PRIORITY
                    onPriorityClick(priorityTask)
                    show = false
                }) {
                    shapePriorityMenu(
                        MEDIUM_PRIORITY,
                        stringResource(id = R.string.medium_priority)
                    )
                }
                DropdownMenuItem(onClick = {
                    priorityTask = HIGH_PRIORITY
                    onPriorityClick(priorityTask)
                    show = false
                }) {
                    shapePriorityMenu(HIGH_PRIORITY, stringResource(id = R.string.high_priority))
                }
            }
            IconButton(onClick = onSaveClick) {
                Icon(
                    imageVector = Icons.Outlined.Save,
                    contentDescription = "ic save",
                    tint = GrayBg
                )
            }
        }
    )
}

@Composable
private fun shapePriorityMenu(priority: Int, title: String) {
    Box(
        modifier = Modifier
            .size(35.dp)
            .background(
                shape = RoundedCornerShape(6.dp),
                color = getColorPriority(priority = priority)
            )
    )
    Text(
        text = title,
        modifier = Modifier.padding(
            start = dimensionResource(id = R.dimen.padding_6),
            end = dimensionResource(id = R.dimen.padding_6)
        ),
        style = MaterialTheme.typography.overline,
        fontSize = 16.sp
    )
}

fun getColorPriority(isTapbar: Boolean = false, priority: Int): Color = when (priority) {
    LOW_PRIORITY -> if (isTapbar) ColorYellowTapBar else ColorYellow
    MEDIUM_PRIORITY -> if (isTapbar) ColorOrangeTapBar else ColorOrange
    HIGH_PRIORITY -> if (isTapbar) ColorRedTapBar else ColorRed
    else -> PrimaryDarkColor
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ContentNewTask(
    modifier: Modifier = Modifier,
    isNewTask: Boolean,
    taskModel: TaskModel,
    setSelectedCheckbx: () -> Unit,
    setdateTaskString: (task: String) -> Unit,
    setdateTaskTime: (time: Long) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var taskString by remember { mutableStateOf(Constants.NO_VALUE) }
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
                                    .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
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
                    if (!isNewTask) {
                        if (modifyTask(taskString, taskModel.task)) {
                            setdateTaskTime(System.currentTimeMillis())
                        } else {
                            taskModel.modificationDate
                        }
                    }
                    if (taskString.isNotEmpty()) {
                        setdateTaskString(taskString)
                    } else {
                        showToast("Escribe tu nota")
                    }
                },
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        setSelectedCheckbx()
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

fun modifyTask(nowTask: String, lastTask: String): Boolean = nowTask != lastTask
