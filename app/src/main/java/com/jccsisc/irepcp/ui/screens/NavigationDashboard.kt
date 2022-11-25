package com.jccsisc.irepcp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jccsisc.irepcp.ui.screens.homegraph.ScreensBottomBar
import com.jccsisc.irepcp.ui.screens.homegraph.favorites.FavoritesScreen
import com.jccsisc.irepcp.ui.screens.homegraph.gallery.GalleryScreen
import com.jccsisc.irepcp.ui.screens.homegraph.home.ui.HomeScreen

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens
 * Created by Julio Cesar Camacho Silva on 24/11/22
 */
@Composable
fun NavigationDashboard(
    navController: NavHostController = rememberNavController(),
    startDestination: String = ScreensBottomBar.HomeScreen.route
) {
    NavHost(navController = navController, startDestination = startDestination) {

        /**
         * BorromBar
         * */
        composable(ScreensBottomBar.HomeScreen.route) { HomeScreen() }
        composable(ScreensBottomBar.FavoritesScreen.route) { FavoritesScreen() }
        composable(ScreensBottomBar.GalleryScreen.route) { GalleryScreen() }


    }
}