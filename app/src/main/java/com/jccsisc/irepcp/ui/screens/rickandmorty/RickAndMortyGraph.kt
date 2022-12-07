package com.jccsisc.irepcp.ui.screens.rickandmorty

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.jccsisc.irepcp.core.constants.Constants.RICK_AND_MORTY_GRAPH
import com.jccsisc.irepcp.ui.screens.rickandmorty.ScreensRickAndMorty.*
import com.jccsisc.irepcp.ui.screens.rickandmorty.ui.detail.DetailRickAndMortyScreen
import com.jccsisc.irepcp.ui.screens.rickandmorty.ui.home.HomeRickAndMortyScreen

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.rickandmorty
 * Created by Julio Cesar Camacho Silva on 07/12/22
 */
fun NavGraphBuilder.rickAndMortyGraph(navController: NavHostController) {
    navigation(
        startDestination = HomeRickAndMortyScreen.route,
        route = RICK_AND_MORTY_GRAPH
    ) {
        composable(HomeRickAndMortyScreen.route) {
            HomeRickAndMortyScreen(
                navigateToDetail = {
                    RickAndMortyActions(navController).navigateToDetail(it)
                }
            )
        }
        composable(DetailRickAndMortyScreen.route) {
            DetailRickAndMortyScreen()
        }
    }
}