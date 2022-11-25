package com.jccsisc.irepcp.ui.screens.homegraph

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jccsisc.irepcp.IREPApp
import com.jccsisc.irepcp.ui.screens.NavigationDashboard
import com.jccsisc.irepcp.ui.screens.homegraph.components.MyBottomBar
import com.jccsisc.irepcp.ui.screens.homegraph.components.MyDrawerLayout
import com.jccsisc.irepcp.ui.screens.homegraph.components.MyTopAppbar
import com.jccsisc.irepcp.ui.screens.homegraph.favorites.FavoritesScreen
import com.jccsisc.irepcp.ui.screens.homegraph.gallery.GalleryScreen
import com.jccsisc.irepcp.ui.screens.homegraph.home.ui.HomeScreen
import com.jccsisc.irepcp.ui.theme.PrimaryDarkColor
import com.jccsisc.irepcp.utils.SetNavbarColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.homegraph
 * Created by Julio Cesar Camacho Silva on 24/11/22
 */
@Composable
fun DashboardContentScreen(navController: NavHostController) {
    SetNavbarColor(color = PrimaryDarkColor, useDarkIcons = false)

    val bottomBarNavController = rememberNavController()

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val itemsbottomBar = listOf(
        ScreensBottomBar.HomeScreen,
        ScreensBottomBar.FavoritesScreen,
        ScreensBottomBar.GalleryScreen
    )

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            HeaderContentScreens(
                coroutineScope = scope,
                scaffoldState = scaffoldState
            )
        },
        bottomBar = { MyBottomBar(navController = navController, navItems = itemsbottomBar) },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Fab icon")
            }
        },
        isFloatingActionButtonDocked = false,
        floatingActionButtonPosition = FabPosition.End,
        drawerContent = {
            MyDrawerLayout(scope, scaffoldState, navController, itemsbottomBar)
        },
        drawerGesturesEnabled = true
    ) { padding ->
//        NavigationDashboard(navController = bottomBarNavController)
        NavHost(navController = bottomBarNavController, startDestination = ScreensBottomBar.HomeScreen.route) {
            composable(ScreensBottomBar.HomeScreen.route) { HomeScreen() }
            composable(ScreensBottomBar.FavoritesScreen.route) { FavoritesScreen() }
            composable(ScreensBottomBar.GalleryScreen.route) { GalleryScreen() }
        }
        padding.calculateBottomPadding()
    }
}

/**
 * Header MainScreen
 * */
@Composable
private fun HeaderContentScreens(
    coroutineScope: CoroutineScope,
    scaffoldState: ScaffoldState,
) {
    MyTopAppbar(
        onClickDrawer = {
            coroutineScope.launch { scaffoldState.drawerState.open() }
        }, onInfoClick = {
            Toast.makeText(IREPApp.INSTANCE.baseContext, "Click en info", Toast.LENGTH_SHORT)
                .show()
        }, onUpdateClick = {

        }
    )
}