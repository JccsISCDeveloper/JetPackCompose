package com.jccsisc.irepcp.ui.features.navigation

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.navigation
 * Created by Julio Cesar Camacho Silva on 14/11/22
 */
sealed class Screens(val route: String) {
    object SplashScreen: Screens("splash_screen")
    object LoginScreen: Screens("login_screen")
    object DashboardScreen: Screens("dashboard_screen")
    object ReportsScreen: Screens("reports_screen")
    object RouteStartScreen: Screens("route_start_screen")
    object DetailsRouteScreen: Screens("details_route_screen/?$NEW_TEXT={$NEW_TEXT}") {
        fun createRoute(newText: String) = "details_route_screen/?$NEW_TEXT=$newText"
    }
}

const val NEW_TEXT = "newText"