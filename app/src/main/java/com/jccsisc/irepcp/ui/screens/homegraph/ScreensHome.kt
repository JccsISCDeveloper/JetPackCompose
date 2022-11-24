package com.jccsisc.irepcp.ui.screens.homegraph

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.BrowseGallery
import androidx.compose.material.icons.sharp.Favorite
import androidx.compose.material.icons.sharp.Home
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.jccsisc.irepcp.R

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.dashboardgraph
 * Created by Julio Cesar Camacho Silva on 23/11/22
 * NOTA: Como las opciones del BottomNavigationView no tienen navegacion a otras screen
 *       Se colocaron en el paquete Home
 */
sealed class ScreensHome(val route: String, val title: String, icon: ImageVector?) {
    object ContentScreens: ScreensHome("content_screens", "Content", Icons.Sharp.Home)
    object HomeScreen: ScreensHome("home_screen", "Home", Icons.Sharp.Home)
    object FavoritesScreen: ScreensHome("favorite_screen", "Favoritos", Icons.Sharp.Favorite)
    object GalleryScreen: ScreensHome("gallery_screen", "Gallery", Icons.Sharp.BrowseGallery)
 /*   object DetailsRouteScreen: ScreensHome("details_route_screen/?$NEW_TEXT={$NEW_TEXT}") {
        fun createRoute(newText: String) = "details_route_screen/?$NEW_TEXT=$newText"
    }*/
}

const val NEW_TEXT = "newText"
const val DASHBOARD_GRAPH = "DASHBOARD_GRAPH"