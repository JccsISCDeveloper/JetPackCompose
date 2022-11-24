package com.jccsisc.irepcp.ui.screens.homegraph

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jccsisc.irepcp.IREPApp
import com.jccsisc.irepcp.ui.screens.homegraph.favorites.FavoritesScreen
import com.jccsisc.irepcp.ui.screens.homegraph.gallery.GalleryScreen
import com.jccsisc.irepcp.ui.screens.homegraph.home.data.model.DrawerOption
import com.jccsisc.irepcp.ui.screens.homegraph.home.data.model.listOptions
import com.jccsisc.irepcp.ui.screens.homegraph.home.ui.HomeScreen
import com.jccsisc.irepcp.ui.screens.homegraph.components.MyBottomBar
import com.jccsisc.irepcp.ui.screens.homegraph.components.MyDrawerLayout
import com.jccsisc.irepcp.ui.screens.homegraph.components.MyTopAppbar
import com.jccsisc.irepcp.utils.SetNavbarColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.homegraph
 * Created by Julio Cesar Camacho Silva on 23/11/22
 */
@Composable
fun ContentScreens(
    navController: NavHostController,
    onClickOption: (option: DrawerOption) -> Unit
) {
    SetNavbarColor(color = Color.Black, useDarkIcons = false)

    val scaffoldState = rememberScaffoldState()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val navigationBottomBar = listOf(
        ScreensHome.HomeScreen,
        ScreensHome.FavoritesScreen,
        ScreensHome.GalleryScreen
    )

    Scaffold(
        topBar = {
            HeaderContentScreens(
                coroutineScope = coroutineScope,
                scaffoldState = scaffoldState
            )
        },
        scaffoldState = scaffoldState,
        drawerContent = {
//            MyDrawerLayout(onClickDrawer = { onClickOption(it) }, optionList = listOptions)
//            MyDrawerLayout()
        },
        bottomBar = { MyBottomBar(navController = navController, navItems = navigationBottomBar) },
        content = { padding ->
            BodyContentScreens(navController, modifier = Modifier.padding(padding))
        }
    )
}

/**
 * Header ContentScreens
 * */
@Composable
private fun HeaderContentScreens(coroutineScope: CoroutineScope, scaffoldState: ScaffoldState) {
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

/**
 * Body ContentScreens
 * */
@Composable
private fun BodyContentScreens(navController: NavHostController, modifier: Modifier = Modifier) {
    HomeScreen()
/*    NavHost(navController = navController, startDestination = ScreensHome.HomeScreen.route) {
        composable(ScreensHome.HomeScreen.route) {
            HomeScreen()
        }
        composable(ScreensHome.FavoritesScreen.route) {
            FavoritesScreen()
        }
        composable(ScreensHome.GalleryScreen.route) {
            GalleryScreen()
        }
    }*/
}

/**
 * Footer ContentScreens
 * */
@Composable
private fun FooterContentScreens() {

}