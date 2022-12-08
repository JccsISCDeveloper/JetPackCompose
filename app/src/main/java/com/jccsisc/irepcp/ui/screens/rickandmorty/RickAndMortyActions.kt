package com.jccsisc.irepcp.ui.screens.rickandmorty

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.jccsisc.irepcp.ui.screens.rickandmorty.ScreensRickAndMorty.DetailRickAndMortyScreen
import com.jccsisc.irepcp.ui.screens.rickandmorty.ScreensRickAndMorty.HomeRickAndMortyScreen

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.rickandmorty
 * Created by Julio Cesar Camacho Silva on 06/12/22
 */
open class RickAndMortyActions(navController: NavHostController) {
    val navigateToHome: () -> Unit = {
        navController.navigate(HomeRickAndMortyScreen.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    val navigateToDetail = { id: Int ->
        navController.navigate(DetailRickAndMortyScreen.passId(id)) {
           /* popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }*/
            launchSingleTop = true
        }

        //
    }
}