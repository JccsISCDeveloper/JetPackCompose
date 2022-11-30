package com.jccsisc.irepcp.ui.screens.dashboard

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
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
fun DashboardContentScreen() {
    SetNavbarColor(color = PrimaryDarkColor, useDarkIcons = false)

    val dashboardNavController = rememberNavController()

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val itemsbottomBar = listOf(HomeScreen, FavoritesScreen, GalleryScreen)
    val itemsDrawer = listOf(
        HomeScreen,
        FavoritesScreen,
        MascotaFelizScreen,
        Pantalla2Screen,
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
        floatingActionButton = { FloatActionBttn() },
        isFloatingActionButtonDocked = false,
        floatingActionButtonPosition = FabPosition.End,
        drawerContent = {
            MyDrawerLayout(scope, scaffoldState, dashboardNavController, itemsDrawer)
        },
        drawerGesturesEnabled = true,
        drawerShape = RoundedCornerShape(bottomEnd = 60.dp),
        drawerElevation = 6.dp
    ) { padding ->
        NavigationDashboard(navController = dashboardNavController)
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
fun FloatActionBttn() {
    FloatingActionButton(onClick = { showToast("Click en FAB") }) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "Fab icon")
    }
}