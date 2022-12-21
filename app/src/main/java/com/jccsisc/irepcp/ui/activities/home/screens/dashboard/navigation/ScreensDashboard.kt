package com.jccsisc.irepcp.ui.activities.home.screens.dashboard.navigation

import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.core.constants.Constants.BOOKS_FAVORITES_SCREEN
import com.jccsisc.irepcp.core.constants.Constants.CANVAS_SCREEN
import com.jccsisc.irepcp.core.constants.Constants.CONSUMO_APIS_HEADER
import com.jccsisc.irepcp.core.constants.Constants.CONTENT_DASHBOARD_SCREEN
import com.jccsisc.irepcp.core.constants.Constants.DETAILS_FAVORITES_SCREEN
import com.jccsisc.irepcp.core.constants.Constants.DETAIL_MASCOTA_SCREEN
import com.jccsisc.irepcp.core.constants.Constants.TO_READ_SCREEN
import com.jccsisc.irepcp.core.constants.Constants.FAVORITES_SCREEN
import com.jccsisc.irepcp.core.constants.Constants.GALLERY_SCREEN
import com.jccsisc.irepcp.core.constants.Constants.HOME_SCREEN
import com.jccsisc.irepcp.core.constants.Constants.BOOKS_HEADER
import com.jccsisc.irepcp.core.constants.Constants.BOOKS_HOME_SCREEN
import com.jccsisc.irepcp.core.constants.Constants.BOOKS_READ_SCREEN
import com.jccsisc.irepcp.core.constants.Constants.TASKS_SCREEN
import com.jccsisc.irepcp.ui.activities.home.screens.dashboard.navigation.model.DrawerChildItem
import com.jccsisc.irepcp.ui.activities.home.screens.dashboard.navigation.model.DrawerItem

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.dashboardgraph
 * Created by Julio Cesar Camacho Silva on 23/11/22
 * NOTA: Como las opciones del BottomNavigationView no tienen navegacion a otras screen
 *       Se colocaron en el paquete Home
 */
sealed class ScreensDashboard(val drawerItem: DrawerItem) {

    object DashboardContentScreen :
        ScreensDashboard(DrawerItem(CONTENT_DASHBOARD_SCREEN, "Content Dashboard"))

    /**
     * BottomBar
     * */
    object HomeScreen :
        ScreensDashboard(DrawerItem(HOME_SCREEN, "Home", R.drawable.ic_home))

    object FavoritesScreen :
        ScreensDashboard(DrawerItem(FAVORITES_SCREEN, "Favoritos", R.drawable.ic_favorites))

    object GalleryScreen :
        ScreensDashboard(DrawerItem(GALLERY_SCREEN, "Gallery", R.drawable.ic_gallery))

    /**
     * Drawer
     * */
    object NavBooksHomeScreen :
        ScreensDashboard(
            DrawerItem(BOOKS_HEADER, "Mis libros", R.drawable.ic_books_home, listBooksOptions)
        )

    object TasksScreen :
        ScreensDashboard(DrawerItem(TASKS_SCREEN, "Tareas", R.drawable.ic_tasks))

    object ConsumoApisScreen :
        ScreensDashboard(DrawerItem(CONSUMO_APIS_HEADER, "Apis", R.drawable.ic_apis))

    object CanvasScreen :
        ScreensDashboard(DrawerItem(CANVAS_SCREEN, "Canvas", R.drawable.ic_canvas))


    object DetailsFavoritesScreen :
        ScreensDashboard(DrawerItem(DETAILS_FAVORITES_SCREEN, "Detalles"))
}

private val listBooksOptions = listOf(
    ScreenBooksChildItemDrawer.BooksHomeScreen,
    ScreenBooksChildItemDrawer.BooksFavoritesScreen,
    ScreenBooksChildItemDrawer.ToReadScreenBooks,
    ScreenBooksChildItemDrawer.BooksReadScreenBooks
)

sealed class ScreenBooksChildItemDrawer(val drawerChildItem: DrawerChildItem) {
    object BooksHomeScreen : ScreenBooksChildItemDrawer(
        DrawerChildItem(BOOKS_HOME_SCREEN, "Todos mis libros", R.drawable.ic_all_books)
    )

    object BooksFavoritesScreen : ScreenBooksChildItemDrawer(
        DrawerChildItem(BOOKS_FAVORITES_SCREEN, "Favoritros", R.drawable.ic_books_favorites)
    )

    object ToReadScreenBooks : ScreenBooksChildItemDrawer(
        DrawerChildItem(TO_READ_SCREEN, "Por leer", R.drawable.ic_books_to_read)
    )

    object BooksReadScreenBooks : ScreenBooksChildItemDrawer(
        DrawerChildItem(BOOKS_READ_SCREEN, "Leídos", R.drawable.ic_books_read)
    )
}
