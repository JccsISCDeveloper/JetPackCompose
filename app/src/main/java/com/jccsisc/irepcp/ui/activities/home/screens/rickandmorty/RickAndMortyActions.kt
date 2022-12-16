package com.jccsisc.irepcp.ui.activities.home.screens.rickandmorty

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.rickandmorty
 * Created by Julio Cesar Camacho Silva on 06/12/22
 */
open class RickAndMortyActions(navController: NavHostController) {
    val navigateToHome: () -> Unit = {
        navController.navigate(ScreensRickAndMorty.HomeRickAndMortyScreen.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    val navigateToDetail = { id: Int ->
        navController.navigate(ScreensRickAndMorty.DetailRickAndMortyScreen.passId(id)) {
           /* popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }*/
            launchSingleTop = true
        }
    }
}