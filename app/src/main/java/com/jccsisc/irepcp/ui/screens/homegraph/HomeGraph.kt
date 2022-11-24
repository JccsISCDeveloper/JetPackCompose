package com.jccsisc.irepcp.ui.screens.homegraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.jccsisc.irepcp.ui.screens.homegraph.home.ui.HomeScreen
import com.jccsisc.irepcp.ui.screens.reports.ReportsScreen
import com.jccsisc.irepcp.ui.screens.routestartgraph.detailsrout.DetailsRouteScreen
import com.jccsisc.irepcp.ui.screens.routestartgraph.routestart.RouteStartScreen

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.dashboardgraph
 * Created by Julio Cesar Camacho Silva on 23/11/22
 */
fun NavGraphBuilder.dashboardGraph(navController: NavHostController) {
    navigation(
        startDestination = ScreensDashboard.DashboardScreen.route,
        route = DASHBOARD_GRAPH
    ) {
        composable(ScreensDashboard.DashboardScreen.route) {
            HomeScreen(onClickOption = {
                when(it.id) {
                    NAV_HOME -> {  }
                    NAV_ROUTE_START -> { navController.navigate(ScreensDashboard.RouteStartScreen.route) }
                    NAV_CLIENT_LIST -> {  }
                    NAV_HOW_AM_I_DOING -> {  }
                    NAV_REPORTS -> {  navController.navigate(ScreensDashboard.ReportsScreen.route) }
                    NAV_UNSCHEDULE_ROUTE -> {  }
                    NAV_END_OF_ROUTE -> {  }
                    NAV_SIGN_OFF -> {  }
                }
            })
        }
        composable(ScreensDashboard.ReportsScreen.route) {
            ReportsScreen()
        }
        composable(ScreensDashboard.RouteStartScreen.route) {
            RouteStartScreen(onNavigationToDetailsRoute = {newText ->
                navController.navigate(ScreensDashboard.DetailsRouteScreen.createRoute(newText))
            })
        }
        composable(
            ScreensDashboard.DetailsRouteScreen.route,
            arguments = listOf(navArgument(NEW_TEXT) { defaultValue = "Pantalla Details" })
        ) { navBackStackEntry ->
            val newText = navBackStackEntry.arguments?.getString(NEW_TEXT)
            requireNotNull(newText)
            DetailsRouteScreen(newText)
        }
    }
}

const val NAV_HOME = 0
const val NAV_ROUTE_START = 1
const val NAV_CLIENT_LIST = 2
const val NAV_HOW_AM_I_DOING = 3
const val NAV_REPORTS = 4
const val NAV_UNSCHEDULE_ROUTE = 5
const val NAV_END_OF_ROUTE = 6
const val NAV_SIGN_OFF = 7
