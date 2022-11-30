package com.jccsisc.irepcp.ui.screens.dashboard.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.jccsisc.irepcp.ui.screens.dashboard.canvas.CanvasScreen
import com.jccsisc.irepcp.ui.screens.dashboard.favorites.FavoritesScreen
import com.jccsisc.irepcp.ui.screens.dashboard.gallery.GalleryScreen
import com.jccsisc.irepcp.ui.screens.dashboard.home.ui.HomeScreen
import com.jccsisc.irepcp.ui.screens.dashboard.mascotafeliz.MascotaFelizScreen
import com.jccsisc.irepcp.ui.screens.dashboard.pantalla2.Pantalla2Screen
import com.jccsisc.irepcp.ui.screens.dashboard.consumoapis.ConsumoApisScreen
import com.jccsisc.irepcp.ui.screens.dashboard.detailsfavorites.DetailsFavoritesScreen
import com.jccsisc.irepcp.ui.screens.dashboard.events.EventsScreen
import com.jccsisc.irepcp.ui.screens.dashboard.navigation.ScreensDashboard.*
import com.jccsisc.irepcp.ui.screens.dashboard.premium.PremiumScreen
import com.jccsisc.irepcp.ui.screens.dashboard.tumascota.TuMascotaScreen

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens
 * Created by Julio Cesar Camacho Silva on 24/11/22
 */
@Composable
fun NavigationDashboard(
    navController: NavHostController = rememberNavController(),
    startDestination: String = HomeScreen.drawerItem.route
) {
    NavHost(navController = navController, startDestination = startDestination) {

        /**
         * BorromBar
         * */
        composable(HomeScreen.drawerItem.route) { HomeScreen() }
        composable(FavoritesScreen.drawerItem.route) { FavoritesScreen(navController) }
        composable(DetailsFavoritesScreen.drawerItem.route) { DetailsFavoritesScreen() }
        composable(GalleryScreen.drawerItem.route) { GalleryScreen() }

        /**
         * Drawer
         * */
        composable(MascotaFelizScreen.drawerItem.route) { MascotaFelizScreen() }
        composable(Pantalla2Screen.drawerItem.route) { Pantalla2Screen() }
        composable(ConsumoApisScreen.drawerItem.route) { ConsumoApisScreen() }
        composable(CanvasScreen.drawerItem.route) { CanvasScreen() }
        itemChildDrawerGraph(navController)

    }
}

fun NavGraphBuilder.itemChildDrawerGraph(navController: NavHostController) {
    navigation(
        startDestination = ScreenChildItemDrawer.TuMascotaScreen.drawerChildItem.route,
        route = MASCOTA_GRAPH
    ) {
        composable(ScreenChildItemDrawer.TuMascotaScreen.drawerChildItem.route) {
            TuMascotaScreen()
        }
        composable(ScreenChildItemDrawer.EventosScreen.drawerChildItem.route) {
            EventsScreen()
        }
        composable(ScreenChildItemDrawer.PremiumScreen.drawerChildItem.route) {
            PremiumScreen()
        }
    }
}