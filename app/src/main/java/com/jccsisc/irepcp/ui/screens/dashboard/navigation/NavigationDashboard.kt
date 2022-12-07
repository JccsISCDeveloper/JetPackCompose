package com.jccsisc.irepcp.ui.screens.dashboard.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jccsisc.irepcp.core.constants.Constants.MASCOTA_GRAPH
import com.jccsisc.irepcp.core.constants.Constants.MASCOTA_ID
import com.jccsisc.irepcp.core.constants.Constants.PRODUCT
import com.jccsisc.irepcp.ui.navigation.Screens
import com.jccsisc.irepcp.ui.screens.dashboard.canvas.CanvasScreen
import com.jccsisc.irepcp.ui.screens.dashboard.consumoapis.ConsumoApisScreen
import com.jccsisc.irepcp.ui.screens.dashboard.detailsfavorites.DetailsFavoritesScreen
import com.jccsisc.irepcp.ui.screens.dashboard.favorites.FavoritesScreen
import com.jccsisc.irepcp.ui.screens.dashboard.gallery.GalleryScreen
import com.jccsisc.irepcp.ui.screens.dashboard.navigation.ScreensDashboard.*
import com.jccsisc.irepcp.ui.screens.mascotas.detail.DetailMascotaScreen
import com.jccsisc.irepcp.ui.screens.mascotas.events.EventsScreen
import com.jccsisc.irepcp.ui.screens.mascotas.mascotashome.ui.MascotasScreen
import com.jccsisc.irepcp.ui.screens.mascotas.premium.PremiumScreen
import com.jccsisc.irepcp.ui.screens.mascotas.tumascota.TuMascotaScreen
import com.jccsisc.irepcp.ui.screens.rickandmorty.RickAndMortyActions
import com.jccsisc.irepcp.ui.screens.todomodule.addtasks.ui.TasksScreen

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens
 * Created by Julio Cesar Camacho Silva on 24/11/22
 */
@Composable
fun NavigationDashboard(
    principalNavController: NavHostController,
    navController: NavHostController = rememberNavController(),
    startDestination: String = MascotasScreen.drawerItem.route
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
                principalNavController.navigate("${Screens.ModifyTaskScreen.route}/${taskId}")
            })
        }
        composable(ConsumoApisScreen.drawerItem.route) {
            ConsumoApisScreen(
                navigateToRickAndMorty = {
                    RickAndMortyActions(principalNavController).navigateToHome()
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