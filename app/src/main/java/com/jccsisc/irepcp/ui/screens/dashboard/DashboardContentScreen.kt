package com.jccsisc.irepcp.ui.screens.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jccsisc.irepcp.ui.navigation.CurrentRoute
import com.jccsisc.irepcp.ui.navigation.Screens
import com.jccsisc.irepcp.ui.screens.dashboard.components.MyBottomBar
import com.jccsisc.irepcp.ui.screens.dashboard.components.MyDrawerLayout
import com.jccsisc.irepcp.ui.screens.dashboard.components.MyTopAppbar
import com.jccsisc.irepcp.ui.screens.dashboard.navigation.NavigationDashboard
import com.jccsisc.irepcp.ui.screens.dashboard.navigation.ScreensDashboard
import com.jccsisc.irepcp.ui.screens.dashboard.navigation.ScreensDashboard.*
import com.jccsisc.irepcp.ui.theme.PrimaryDarkColor
import com.jccsisc.irepcp.utils.SetNavbarColor
import com.jccsisc.irepcp.utils.showToast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.homegraph
 * Created by Julio Cesar Camacho Silva on 24/11/22
 */
@Composable
fun DashboardContentScreen(principalNavController: NavHostController) {
    SetNavbarColor(color = PrimaryDarkColor, useDarkIcons = false)

    val dashboardNavController = rememberNavController()
    val currenRoute = CurrentRoute(dashboardNavController)

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val itemsbottomBar = listOf(MascotasScreen, FavoritesScreen, GalleryScreen)
    val itemsDrawer = listOf(
        MascotasScreen,
        FavoritesScreen,
        MascotaFelizScreen,
        TasksScreen,
        ConsumoApisScreen,
        CanvasScreen
    )

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            HeaderContentScreens(
                itemsDrawer,
                itemsbottomBar,
                dashboardNavController,
                coroutineScope = scope,
                scaffoldState = scaffoldState
            )
        },
        bottomBar = {
            MyBottomBar(
                navController = dashboardNavController,
                navItems = itemsbottomBar
            )
        },
        floatingActionButton = {
            if (currenRoute == TasksScreen.drawerItem.route || currenRoute == MascotasScreen.drawerItem.route) {
                FloatActionBttn(currenRoute, principalNavController)
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.End,
        drawerContent = {
            MyDrawerLayout(scope, scaffoldState, dashboardNavController, itemsDrawer)
        },
        drawerGesturesEnabled = true,
        drawerShape = RoundedCornerShape(bottomEnd = 60.dp),
        drawerElevation = 6.dp
    ) { padding ->
        NavigationDashboard(
            principalNavController = principalNavController,
            navController = dashboardNavController
        )
        padding.calculateBottomPadding()
    }
}

/**
 * Header MainScreen
 * */
@Composable
private fun HeaderContentScreens(
    itemsDrawer: List<ScreensDashboard>,
    itemsbottomBar: List<ScreensDashboard>,
    dashboardNavController: NavHostController,
    coroutineScope: CoroutineScope,
    scaffoldState: ScaffoldState,
) {
    MyTopAppbar(
        itemsDrawer = itemsDrawer,
        itemsbottomBar = itemsbottomBar,
        dashboardNavController = dashboardNavController,
        onClickDrawer = {
            coroutineScope.launch { scaffoldState.drawerState.open() }
        }, onInfoClick = {
            showToast("Click en info")
        }, displaySnackBarClick = {
            coroutineScope.launch {
                val result = scaffoldState.snackbarHostState.showSnackbar(
                    message = "Este es un snackbar",
                    actionLabel = "Da click",
                    duration = SnackbarDuration.Indefinite,
                )

                when (result) {
                    SnackbarResult.ActionPerformed -> {
                        showToast("Logica de codigo")
                    }
                    SnackbarResult.Dismissed -> {
                        showToast("Dismiss")
                    }
                }
            }
        }
    )
}

@Composable
fun FloatActionBttn(
    currenRoute: String?,
    principalNavController: NavHostController
) {
    var showDialogData by remember { mutableStateOf(false) }
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var aceptar by remember { mutableStateOf(false) }

    if (showDialogData) {
        when (currenRoute) {
            TasksScreen.drawerItem.route -> {
                principalNavController.navigate(Screens.AddTaskScreen.route)
                showDialogData = false
            }
            MascotasScreen.drawerItem.route -> {
                AlertDialog(
                    onDismissRequest = {},
                    confirmButton = {
                        TextButton(onClick = {}) {
                            Text(text = "Solicitar")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDialogData = false }) {
                            Text(text = "Cancelar")
                        }
                    },
                    title = { Text(text = "Recompensas") },
                    text = {
                        Column {
                            Text(text = "Registre sus datos")
                            TextField(
                                value = nombre,
                                onValueChange = { nombre = it },
                                placeholder = { Text(text = "Nombre") },
                                singleLine = true,
                                colors = TextFieldDefaults.textFieldColors(
                                    textColor = Color(0xFFB2B2B2),
                                    backgroundColor = Color(0xFFFAFAFA),
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent
                                )
                            )
                            Spacer(modifier = Modifier.size(10.dp))
                            TextField(
                                value = email,
                                onValueChange = { email = it },
                                placeholder = { Text(text = "Correo") },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                                singleLine = true,
                                colors = TextFieldDefaults.textFieldColors(
                                    textColor = Color(0xFFB2B2B2),
                                    backgroundColor = Color(0xFFFAFAFA),
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent
                                )
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Checkbox(checked = aceptar, onCheckedChange = { aceptar = it })
                                Text(text = "Acepto los términos y condiciones")
                            }
                        }
                    })
            }
        }
    }

    FloatingActionButton(onClick = { showDialogData = true }) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "Fab icon")
    }
}