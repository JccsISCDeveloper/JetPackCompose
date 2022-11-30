package com.jccsisc.irepcp.ui.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.navigation
 * Created by Julio Cesar Camacho Silva on 30/11/22
 */
fun navigateToAnyRoute(navController: NavHostController, route: String, action: () -> Unit) {
    navController.navigate(route) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
    }
    action()
}