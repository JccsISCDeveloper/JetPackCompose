package com.jccsisc.irepcp.ui.screens.dashboard.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.*
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.dashboardgraph
 * Created by Julio Cesar Camacho Silva on 23/11/22
 * NOTA: Como las opciones del BottomNavigationView no tienen navegacion a otras screen
 *       Se colocaron en el paquete Home
 */
const val DASHBOARD_CONTENT_GRAPH = "DASHBOARD_CONTENT_GRAPH"
const val HOME_GRAPH = "HOME_GRAPH"
sealed class ScreensDashboard(val route: String, val title: String, val icon: ImageVector) {

    object DashboardContentScreen: ScreensDashboard("dashboard_content_screen", "Home", Icons.Sharp.Home)

    /**
     * BottomBar
     * */
    object HomeScreen: ScreensDashboard("home_screen", "Home", Icons.Sharp.Home)
    object FavoritesScreen: ScreensDashboard("favorite_screen", "Favoritos", Icons.Sharp.Favorite)
    object GalleryScreen: ScreensDashboard("gallery_screen", "Gallery", Icons.Sharp.BrowseGallery)

    /**
     * Drawer
     * */
    object MascotaFelizScreen: ScreensDashboard("mascota_feliz_screen", "Mascota Feliz", Icons.Sharp.FormatShapes )
    object Pantalla2Screen: ScreensDashboard("pantalla2_screen", "Pantalla 2", Icons.Sharp.Dashboard)
    object ConsumoApisScreen: ScreensDashboard("consumo_apis_screen", "Apis", Icons.Sharp.AddReaction)
    object CanvasScreen: ScreensDashboard("canvas_screen", "Canvas", Icons.Sharp.Airplay)

}
