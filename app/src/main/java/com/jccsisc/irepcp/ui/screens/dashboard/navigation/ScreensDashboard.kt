package com.jccsisc.irepcp.ui.screens.dashboard.navigation

import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.ui.screens.dashboard.navigation.model.DrawerChildItem
import com.jccsisc.irepcp.ui.screens.dashboard.navigation.model.DrawerItem
import com.jccsisc.irepcp.ui.screens.dashboard.tumascota.TuMascotaScreen

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.dashboardgraph
 * Created by Julio Cesar Camacho Silva on 23/11/22
 * NOTA: Como las opciones del BottomNavigationView no tienen navegacion a otras screen
 *       Se colocaron en el paquete Home
 */
const val DASHBOARD_CONTENT_GRAPH = "DASHBOARD_CONTENT_GRAPH"
const val HOME_GRAPH = "HOME_GRAPH"
sealed class ScreensDashboard(val drawerItem: DrawerItem) {

    object DashboardContentScreen: ScreensDashboard(DrawerItem("content_dashboard", "Content Dashboard", R.drawable.ic_home))

    /**
     * BottomBar
     * */
    object HomeScreen: ScreensDashboard(DrawerItem("home_screen", "Home", R.drawable.ic_home))
    object FavoritesScreen: ScreensDashboard(DrawerItem("favorite_screen", "Favoritos", R.drawable.ic_favorites))
    object GalleryScreen: ScreensDashboard(DrawerItem("gallery_screen", "Gallery", R.drawable.ic_gallery))

    /**
     * Drawer
     * */
    object MascotaFelizScreen: ScreensDashboard(DrawerItem("mascota", "Mascota", R.drawable.ic_pets, listMascotaOptions))
    object Pantalla2Screen: ScreensDashboard(DrawerItem("pantalla2_screen", "Pantalla 2", R.drawable.ic_search))
    object ConsumoApisScreen: ScreensDashboard(DrawerItem("consumo_apis_screen", "Apis", R.drawable.ic_apis))
    object CanvasScreen: ScreensDashboard(DrawerItem("canvas_screen", "Canvas", R.drawable.ic_canvas))

}

val listMascotaOptions = listOf(
    ScreenChildItemDrawer.TuMascotaScreen,
    ScreenChildItemDrawer.EventosScreen,
    ScreenChildItemDrawer.PremiumScreen
)

const val ITEM_MASCOTAS_GRAPH = "ITEM_MASCOTAS_GRAPH"
const val MASCOTA_GRAPH = "MASCOTA_GRAPH"
sealed class  ScreenChildItemDrawer(val drawerChildItem: DrawerChildItem) {
    object TuMascotaScreen: ScreenChildItemDrawer(DrawerChildItem("tu_mascota_screen", "Tu mascota", R.drawable.ic_tu_mascota))
    object EventosScreen: ScreenChildItemDrawer(DrawerChildItem("eventos_screen", "Eventos", R.drawable.ic_cat_2))
    object PremiumScreen: ScreenChildItemDrawer(DrawerChildItem("premium_screen", "Premium", R.drawable.ic_premium))
}
