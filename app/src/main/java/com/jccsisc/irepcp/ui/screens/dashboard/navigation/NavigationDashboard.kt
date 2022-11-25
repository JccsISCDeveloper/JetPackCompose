package com.jccsisc.irepcp.ui.screens.dashboard.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jccsisc.irepcp.ui.screens.dashboard.canvas.CanvasScreen
import com.jccsisc.irepcp.ui.screens.dashboard.favorites.FavoritesScreen
import com.jccsisc.irepcp.ui.screens.dashboard.gallery.GalleryScreen
import com.jccsisc.irepcp.ui.screens.dashboard.home.ui.HomeScreen
import com.jccsisc.irepcp.ui.screens.dashboard.pantalla1.Pantalla1Screen
import com.jccsisc.irepcp.ui.screens.dashboard.pantalla2.Pantalla2Screen
import com.jccsisc.irepcp.ui.screens.dashboard.consumoapis.ConsumoApisScreen

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens
 * Created by Julio Cesar Camacho Silva on 24/11/22
 */
@Composable
fun NavigationDashboard(
    navController: NavHostController = rememberNavController(),
    startDestination: String = ScreensDashboard.HomeScreen.route
) {
    NavHost(navController = navController, startDestination = startDestination) {

        /**
         * BorromBar
         * */
        composable(ScreensDashboard.HomeScreen.route) { HomeScreen() }
        composable(ScreensDashboard.FavoritesScreen.route) { FavoritesScreen() }
        composable(ScreensDashboard.GalleryScreen.route) { GalleryScreen() }

        /**
         * Drawer
         * */
        composable(ScreensDashboard.Pantalla1Screen.route) { Pantalla1Screen() }
        composable(ScreensDashboard.Pantalla2Screen.route) { Pantalla2Screen() }
        composable(ScreensDashboard.ConsumoApisScreen.route) { ConsumoApisScreen() }
        composable(ScreensDashboard.CanvasScreen.route) { CanvasScreen() }
    }
}