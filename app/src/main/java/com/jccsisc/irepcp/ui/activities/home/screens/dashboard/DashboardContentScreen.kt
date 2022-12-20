package com.jccsisc.irepcp.ui.activities.home.screens.dashboard

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jccsisc.irepcp.ui.activities.home.navigation.CurrentRoute
import com.jccsisc.irepcp.ui.activities.home.navigation.Screens
import com.jccsisc.irepcp.ui.activities.home.screens.todomodule.ui.TaskViewModel
import com.jccsisc.irepcp.ui.activities.home.screens.dashboard.components.MyBottomBar
import com.jccsisc.irepcp.ui.activities.home.screens.dashboard.components.MyDrawerLayout
import com.jccsisc.irepcp.ui.activities.home.screens.dashboard.components.MyTopAppbar
import com.jccsisc.irepcp.ui.activities.home.screens.dashboard.navigation.NavigationDashboard
import com.jccsisc.irepcp.ui.activities.home.screens.dashboard.navigation.ScreensDashboard
import com.jccsisc.irepcp.ui.activities.home.screens.dashboard.navigation.ScreensDashboard.*
import com.jccsisc.irepcp.ui.activities.home.screens.books.favorites.BooksDialog
import com.jccsisc.irepcp.ui.activities.home.screens.dashboard.navigation.ScreenBooksChildItemDrawer
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
fun DashboardContentScreen(
    principalNavController: NavHostController,
    taskViewModel: TaskViewModel = hiltViewModel()
) {
    SetNavbarColor(color = PrimaryDarkColor, useDarkIcons = false)
    val dashboardNavController = rememberNavController()
    val currenRoute = CurrentRoute(dashboardNavController)

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val singletonProviderToTaskVM = taskViewModel.mySingletonClass

    val itemsbottomBar = listOf(HomeScreen, FavoritesScreen, GalleryScreen)
    val itemsDrawer = listOf(
        HomeScreen,
        FavoritesScreen,
        NavBooksHomeScreen,
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
                scaffoldState = scaffoldState,
                setTaskOrder = {
                    singletonProviderToTaskVM.taskOrder.value = it
                }
            )
        },
        bottomBar = {
            MyBottomBar(
                navController = dashboardNavController,
                navItems = itemsbottomBar
            )
        },
        floatingActionButton = {
            if (
                currenRoute == TasksScreen.drawerItem.route ||
                currenRoute == HomeScreen.drawerItem.route ||
                currenRoute == ScreenBooksChildItemDrawer.BooksHomeScreen.drawerChildItem.route
            ) {
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
    setTaskOrder: (String) -> Unit
) {
    MyTopAppbar(
        itemsDrawer = itemsDrawer,
        itemsbottomBar = itemsbottomBar,
        dashboardNavController = dashboardNavController,
        onClickDrawer = {
            coroutineScope.launch { scaffoldState.drawerState.open() }
        },
        onInfoClick = {
            setTaskOrder(it)
        },
        displaySnackBarClick = {
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


    if (showDialogData) {
        when (currenRoute) {
            TasksScreen.drawerItem.route -> {
                principalNavController.navigate("${Screens.AddOrModifyTaskScreen.route}/${-1L}")
                showDialogData = false
            }
            HomeScreen.drawerItem.route, ScreenBooksChildItemDrawer.BooksHomeScreen.drawerChildItem.route -> {
                BooksDialog { showDialogData = it }
            }
        }
    }

    FloatingActionButton(onClick = { showDialogData = true }) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "Fab icon")
    }
}