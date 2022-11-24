package com.jccsisc.irepcp.ui.screens.homegraph

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.dashboardgraph
 * Created by Julio Cesar Camacho Silva on 23/11/22
 */
sealed class ScreensDashboard(val route: String) {
    object DashboardScreen: ScreensDashboard("dashboard_screen")
    object ReportsScreen: ScreensDashboard("reports_screen")
    object RouteStartScreen: ScreensDashboard("route_start_screen")
    object DetailsRouteScreen: ScreensDashboard("details_route_screen/?$NEW_TEXT={$NEW_TEXT}") {
        fun createRoute(newText: String) = "details_route_screen/?$NEW_TEXT=$newText"
    }
}

const val NEW_TEXT = "newText"
const val DASHBOARD_GRAPH = "DASHBOARD_GRAPH"