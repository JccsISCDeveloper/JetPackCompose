package com.jccsisc.irepcp.ui.activities.home.screens.rickandmorty

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.jccsisc.irepcp.core.constants.Constants.ID
import com.jccsisc.irepcp.core.constants.Constants.RICK_AND_MORTY_GRAPH
import com.jccsisc.irepcp.ui.activities.home.screens.rickandmorty.ScreensRickAndMorty.DetailRickAndMortyScreen
import com.jccsisc.irepcp.ui.activities.home.screens.rickandmorty.ScreensRickAndMorty.HomeRickAndMortyScreen
import com.jccsisc.irepcp.ui.activities.home.screens.rickandmorty.ui.detail.DetailRickAndMortyScreen
import com.jccsisc.irepcp.ui.activities.home.screens.rickandmorty.ui.home.HomeRickAndMortyScreen

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
                },
                navigateBack = { navController.popBackStack() }
            )
        }
        composable(
            route = DetailRickAndMortyScreen.route,
            arguments = listOf(navArgument(ID) { type = NavType.IntType })
        ) {
            DetailRickAndMortyScreen(navigateToBack =  { navController.popBackStack() })
        }
    }
}