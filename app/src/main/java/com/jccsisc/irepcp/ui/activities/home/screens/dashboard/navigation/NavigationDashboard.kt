package com.jccsisc.irepcp.ui.activities.home.screens.dashboard.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jccsisc.irepcp.core.constants.Constants
import com.jccsisc.irepcp.core.constants.Constants.MASCOTA_GRAPH
import com.jccsisc.irepcp.core.constants.Constants.MASCOTA_ID
import com.jccsisc.irepcp.core.constants.Constants.PRODUCT
import com.jccsisc.irepcp.ui.activities.home.navigation.Screens
import com.jccsisc.irepcp.ui.activities.home.screens.dashboard.canvas.CanvasScreen
import com.jccsisc.irepcp.ui.activities.home.screens.dashboard.consumoapis.ConsumoApisScreen
import com.jccsisc.irepcp.ui.activities.home.screens.dashboard.detailsfavorites.DetailsFavoritesScreen
import com.jccsisc.irepcp.ui.activities.home.screens.dashboard.favorites.FavoritesScreen
import com.jccsisc.irepcp.ui.activities.home.screens.dashboard.gallery.GalleryScreen
import com.jccsisc.irepcp.ui.activities.home.screens.dashboard.navigation.ScreensDashboard.*
import com.jccsisc.irepcp.ui.activities.home.screens.mascotas.detail.DetailMascotaScreen
import com.jccsisc.irepcp.ui.activities.home.screens.mascotas.events.EventsScreen
import com.jccsisc.irepcp.ui.activities.home.screens.mascotas.mascotashome.ui.MascotasScreen
import com.jccsisc.irepcp.ui.activities.home.screens.mascotas.premium.PremiumScreen
import com.jccsisc.irepcp.ui.activities.home.screens.mascotas.tumascota.TuMascotaScreen
import com.jccsisc.irepcp.ui.activities.home.screens.todomodule.addtasks.ui.TasksScreen

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens
 * Created by Julio Cesar Camacho Silva on 24/11/22
 */
@Composable
fun NavigationDashboard(
    principalNavController: NavHostController,
    navController: NavHostController = rememberNavController(),
    startDestination: String = TasksScreen.drawerItem.route
) {
    NavHost(navController = navController, startDestination = startDestination) {

        /**
         * BorromBar
         * */
        composable(MascotasScreen.drawerItem.route) {
            MascotasScreen(navigateToDetailMascota = { mascotaId ->
                navController.navigate("${DetailMascotaScreen.drawerItem.route}/${mascotaId}")
            })
        }
        composable(
            route = "${DetailMascotaScreen.drawerItem.route}/{$MASCOTA_ID}",
            arguments = listOf(navArgument(MASCOTA_ID) { type = NavType.IntType })
        ) { backStackEntry ->
            val mascotaId = backStackEntry.arguments?.getInt(MASCOTA_ID) ?: 0
            DetailMascotaScreen(
                mascotaId = mascotaId
            ) {
                navController.popBackStack()
            }
        }
        composable(FavoritesScreen.drawerItem.route) { FavoritesScreen(navController) }
        composable("${DetailsFavoritesScreen.drawerItem.route}/{$PRODUCT}") { backStackEntry ->
            val producto = backStackEntry.arguments?.getString(PRODUCT)
            requireNotNull(producto)
            DetailsFavoritesScreen(producto)
        }
        composable(GalleryScreen.drawerItem.route) { GalleryScreen() }

        /**
         * Drawer
         * */
        composable(TasksScreen.drawerItem.route) {
            TasksScreen(navigateToModifyTask = { taskId ->
                principalNavController.navigate("${Screens.AddOrModifyTaskScreen.route}/${taskId}")
            })
        }
        composable(ConsumoApisScreen.drawerItem.route) {
            ConsumoApisScreen(
                navigateToRickAndMorty = {
                    principalNavController.navigate(Constants.RICK_AND_MORTY_GRAPH)
                }
            )
        }
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