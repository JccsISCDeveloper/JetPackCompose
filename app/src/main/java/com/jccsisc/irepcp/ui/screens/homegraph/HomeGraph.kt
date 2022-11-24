package com.jccsisc.irepcp.ui.screens.homegraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.jccsisc.irepcp.ui.screens.homegraph.favorites.FavoritesScreen
import com.jccsisc.irepcp.ui.screens.homegraph.gallery.GalleryScreen
import com.jccsisc.irepcp.ui.screens.homegraph.home.ui.HomeScreen

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.dashboardgraph
 * Created by Julio Cesar Camacho Silva on 23/11/22
 */
fun NavGraphBuilder.dashboardGraph(navController: NavHostController) {

    navigation(
        startDestination = ScreensHome.ContentScreens.route,
        route = DASHBOARD_GRAPH
    ) {
        composable(ScreensHome.ContentScreens.route) {
            ContentScreens( navController, onClickOption = {
                when(it.id) {
                    NAV_HOME -> {  }
                    NAV_ROUTE_START -> { navController.navigate(ScreensHome.GalleryScreen.route) }
                    NAV_CLIENT_LIST -> {  }
                    NAV_HOW_AM_I_DOING -> {  }
                    NAV_REPORTS -> {  navController.navigate(ScreensHome.FavoritesScreen.route) }
                    NAV_UNSCHEDULE_ROUTE -> {  }
                    NAV_END_OF_ROUTE -> {  }
                    NAV_SIGN_OFF -> {  }
                }
            })
        }
        composable(ScreensHome.HomeScreen.route) {
            HomeScreen()
        }
        composable(ScreensHome.FavoritesScreen.route) {
            FavoritesScreen()
        }
        composable(ScreensHome.GalleryScreen.route) {
            GalleryScreen()
        }
/*        composable(ScreensHome.GalleryScreen.route) {
            RouteStartScreen(onNavigationToDetailsRoute = {newText ->
                navController.navigate(ScreensHome.DetailsRouteScreen.createRoute(newText))
            })
        }
        composable(
            ScreensHome.DetailsRouteScreen.route,
            arguments = listOf(navArgument(NEW_TEXT) { defaultValue = "Pantalla Details" })
        ) { navBackStackEntry ->
            val newText = navBackStackEntry.arguments?.getString(NEW_TEXT)
            requireNotNull(newText)
            DetailsRouteScreen(newText)
        }*/
    }
}

private const val NAV_HOME = 0
private const val NAV_ROUTE_START = 1
private const val NAV_CLIENT_LIST = 2
private const val NAV_HOW_AM_I_DOING = 3
private const val NAV_REPORTS = 4
private const val NAV_UNSCHEDULE_ROUTE = 5
private const val NAV_END_OF_ROUTE = 6
private const val NAV_SIGN_OFF = 7
