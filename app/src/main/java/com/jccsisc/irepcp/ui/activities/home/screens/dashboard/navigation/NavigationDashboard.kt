package com.jccsisc.irepcp.ui.activities.home.screens.dashboard.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.jccsisc.irepcp.core.constants.Constants
import com.jccsisc.irepcp.core.constants.Constants.BOOKS_GRAPH
import com.jccsisc.irepcp.core.constants.Constants.PRODUCT
import com.jccsisc.irepcp.ui.activities.home.navigation.Screens
import com.jccsisc.irepcp.ui.activities.home.screens.books.favorites.BooksFavoritesScreen
import com.jccsisc.irepcp.ui.activities.home.screens.books.home.ui.BooksHomeScreen
import com.jccsisc.irepcp.ui.activities.home.screens.books.readbooks.BooksReadScreen
import com.jccsisc.irepcp.ui.activities.home.screens.books.toread.ToReadScreen
import com.jccsisc.irepcp.ui.activities.home.screens.dashboard.canvas.CanvasScreen
import com.jccsisc.irepcp.ui.activities.home.screens.dashboard.consumoapis.ConsumoApisScreen
import com.jccsisc.irepcp.ui.activities.home.screens.dashboard.detailsfavorites.DetailsFavoritesScreen
import com.jccsisc.irepcp.ui.activities.home.screens.dashboard.favorites.FavoritesScreen
import com.jccsisc.irepcp.ui.activities.home.screens.dashboard.gallery.GalleryScreen
import com.jccsisc.irepcp.ui.activities.home.screens.dashboard.navigation.ScreenBooksChildItemDrawer.*
import com.jccsisc.irepcp.ui.activities.home.screens.dashboard.navigation.ScreensDashboard.*
import com.jccsisc.irepcp.ui.activities.home.screens.todomodule.ui.TasksScreen

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens
 * Created by Julio Cesar Camacho Silva on 24/11/22
 */
@Composable
fun NavigationDashboard(
    principalNavController: NavHostController,
    navController: NavHostController = rememberNavController(),
    startDestination: String = HomeScreen.drawerItem.route
) {
    NavHost(navController = navController, startDestination = startDestination) {

        /**
         * BorromBar
         * */
        composable(HomeScreen.drawerItem.route) {
            BooksHomeScreen(navigateToDetailBook = { bookId ->
                principalNavController.navigate("${Screens.DetailBookScreen.route}/${bookId}")
            })
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
                principalNavController.navigate("${Screens.AddOrModifyTaskScreen.route}/${taskId}")
            })
        }
        composable(ConsumoApisScreen.drawerItem.route) {
            ConsumoApisScreen(
                navigateToRickAndMorty = {
                    principalNavController.navigate(Constants.RICK_AND_MORTY_GRAPH)
                }
            )
        }
        composable(CanvasScreen.drawerItem.route) { CanvasScreen() }
        itemChildDrawerGraph(principalNavController)

    }
}

fun NavGraphBuilder.itemChildDrawerGraph(principalNavController: NavHostController) {
    navigation(
        startDestination = BooksHomeScreen.drawerChildItem.route,
        route = BOOKS_GRAPH
    ) {
        composable(BooksHomeScreen.drawerChildItem.route) {
            BooksHomeScreen(navigateToDetailBook = { bookId ->
                principalNavController.navigate("${Screens.DetailBookScreen.route}/${bookId}")
            })
        }
        composable(BooksFavoritesScreen.drawerChildItem.route) {
            BooksFavoritesScreen()
        }
        composable(ToReadScreenBooks.drawerChildItem.route) {
            ToReadScreen()
        }
        composable(BooksReadScreenBooks.drawerChildItem.route) {
            BooksReadScreen()
        }
    }
}