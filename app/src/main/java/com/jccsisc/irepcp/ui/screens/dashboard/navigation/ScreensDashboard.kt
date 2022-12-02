package com.jccsisc.irepcp.ui.screens.dashboard.navigation

import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.core.constants.Constants.CANVAS_SCREEN
import com.jccsisc.irepcp.core.constants.Constants.CONSUMO_APIS_HEADER
import com.jccsisc.irepcp.core.constants.Constants.CONTENT_DASHBOARD_SCREEN
import com.jccsisc.irepcp.core.constants.Constants.DETAILS_FAVORITES_SCREEN
import com.jccsisc.irepcp.core.constants.Constants.DETAIL_MASCOTA_SCREEN
import com.jccsisc.irepcp.core.constants.Constants.EVENTS_SCREEN
import com.jccsisc.irepcp.core.constants.Constants.FAVORITES_SCREEN
import com.jccsisc.irepcp.core.constants.Constants.GALLERY_SCREEN
import com.jccsisc.irepcp.core.constants.Constants.MASCOTAS_SCREEN
import com.jccsisc.irepcp.core.constants.Constants.MASCOTA_HEADER
import com.jccsisc.irepcp.core.constants.Constants.PREMIUM_SCREEN
import com.jccsisc.irepcp.core.constants.Constants.TASKS_SCREEN
import com.jccsisc.irepcp.core.constants.Constants.TU_MASCOTA_SCREEN
import com.jccsisc.irepcp.ui.screens.dashboard.navigation.model.DrawerChildItem
import com.jccsisc.irepcp.ui.screens.dashboard.navigation.model.DrawerItem

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.dashboardgraph
 * Created by Julio Cesar Camacho Silva on 23/11/22
 * NOTA: Como las opciones del BottomNavigationView no tienen navegacion a otras screen
 *       Se colocaron en el paquete Home
 */
sealed class ScreensDashboard(val drawerItem: DrawerItem) {

    object DashboardContentScreen : ScreensDashboard(
        DrawerItem(
            CONTENT_DASHBOARD_SCREEN,
            "Content Dashboard",
            R.drawable.ic_home
        )
    )

    /**
     * BottomBar
     * */
    object MascotasScreen : ScreensDashboard(DrawerItem(MASCOTAS_SCREEN, "Home", R.drawable.ic_home))
    object DetailMascotaScreen : ScreensDashboard(DrawerItem(DETAIL_MASCOTA_SCREEN, "Detalle mascota"))
    object FavoritesScreen :
        ScreensDashboard(DrawerItem(FAVORITES_SCREEN, "Favoritos", R.drawable.ic_favorites))

    object GalleryScreen :
        ScreensDashboard(DrawerItem(GALLERY_SCREEN, "Gallery", R.drawable.ic_gallery))

    /**
     * Drawer
     * */
    object MascotaFelizScreen : ScreensDashboard(
        DrawerItem(
            MASCOTA_HEADER,
            "Mascota",
            R.drawable.ic_pets,
            listMascotaOptions
        )
    ) //todo esta clase ya no se visualiza, se tomó como padre pero en qué momoento se mostrará? RESOLVER

    object TasksScreen :
        ScreensDashboard(DrawerItem(TASKS_SCREEN, "Tareas", R.drawable.ic_tasks))

    object ConsumoApisScreen :
        ScreensDashboard(DrawerItem(CONSUMO_APIS_HEADER, "Apis", R.drawable.ic_apis))

    object CanvasScreen :
        ScreensDashboard(DrawerItem(CANVAS_SCREEN, "Canvas", R.drawable.ic_canvas))


    object DetailsFavoritesScreen :
        ScreensDashboard(DrawerItem(DETAILS_FAVORITES_SCREEN, "Detalles"))
}

val listMascotaOptions = listOf(
    ScreenChildItemDrawer.TuMascotaScreen,
    ScreenChildItemDrawer.EventosScreen,
    ScreenChildItemDrawer.PremiumScreen
)

sealed class ScreenChildItemDrawer(val drawerChildItem: DrawerChildItem) {
    object TuMascotaScreen : ScreenChildItemDrawer(
        DrawerChildItem(
            TU_MASCOTA_SCREEN,
            "Tu mascota",
            R.drawable.ic_tu_mascota
        )
    )

    object EventosScreen :
        ScreenChildItemDrawer(DrawerChildItem(EVENTS_SCREEN, "Eventos", R.drawable.ic_cat_2))

    object PremiumScreen :
        ScreenChildItemDrawer(DrawerChildItem(PREMIUM_SCREEN, "Premium", R.drawable.ic_premium))
}
