package com.jccsisc.irepcp.ui.features.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jccsisc.irepcp.ui.features.SplashScreen
import com.jccsisc.irepcp.ui.features.dashboardgraph.dashboard.ui.DashboardScreen
import com.jccsisc.irepcp.ui.features.logingraph.login.ui.LoginScreen
import com.jccsisc.irepcp.ui.features.logingraph.login.ui.LoginViewModel
import com.jccsisc.irepcp.ui.features.reports.ReportsScreen
import com.jccsisc.irepcp.ui.features.routestartgraph.detailsrout.DetailsRouteScreen
import com.jccsisc.irepcp.ui.features.routestartgraph.routestart.RouteStartScreen

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.navigation
 * Created by Julio Cesar Camacho Silva on 14/11/22
 */
@Composable
fun AppNavigation(loginViewModel: LoginViewModel) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.DashboardScreen.route
    ) {
        //todo eliminar este splash, hacerlo con activity
        composable(Screens.SplashScreen.route) {
            SplashScreen(onNavigationToLogin = {
                navController.popBackStack()
                navController.navigate(Screens.LoginScreen.route)
            })
        }
        composable(Screens.LoginScreen.route) {
            LoginScreen(
                loginViewModel,
                onNavigationToDashboard = {
                    navController.popBackStack()
                    navController.navigate(Screens.DashboardScreen.route)
                }
            )
        }
        composable(Screens.DashboardScreen.route) {
            DashboardScreen(onClickOption = {
                when(it.id) {
                    NAV_HOME -> {  }
                    NAV_ROUTE_START -> { navController.navigate(Screens.RouteStartScreen.route) }
                    NAV_CLIENT_LIST -> {  }
                    NAV_HOW_AM_I_DOING -> {  }
                    NAV_REPORTS -> {  navController.navigate(Screens.ReportsScreen.route) }
                    NAV_UNSCHEDULE_ROUTE -> {  }
                    NAV_END_OF_ROUTE -> {  }
                    NAV_SIGN_OFF -> {  }
                }
            })
        }
        composable(Screens.ReportsScreen.route) {
            ReportsScreen()
        }
        composable(Screens.RouteStartScreen.route) {
            RouteStartScreen(onNavigationToDetailsRoute = {newText ->
                navController.navigate(Screens.DetailsRouteScreen.createRoute(newText))
            })
        }
        composable(
            Screens.DetailsRouteScreen.route,
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
