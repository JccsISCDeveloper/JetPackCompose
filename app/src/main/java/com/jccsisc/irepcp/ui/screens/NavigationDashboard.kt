package com.jccsisc.irepcp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jccsisc.irepcp.ui.screens.homegraph.ScreensHome
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
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ScreensHome.HomeScreen.route
) {
    NavHost(navController = navController, startDestination = startDestination) {

        /**
         * BorromBar
         * */
        composable(ScreensHome.HomeScreen.route) { HomeScreen() }
        composable(ScreensHome.FavoritesScreen.route) { FavoritesScreen() }
        composable(ScreensHome.GalleryScreen.route) { GalleryScreen() }


    }
}