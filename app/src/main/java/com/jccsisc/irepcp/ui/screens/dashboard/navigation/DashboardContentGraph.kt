package com.jccsisc.irepcp.ui.screens.dashboard.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.jccsisc.irepcp.ui.screens.dashboard.DashboardContentScreen
import com.jccsisc.irepcp.ui.screens.dashboard.navigation.ScreensDashboard.*

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.homegraph
 * Created by Julio Cesar Camacho Silva on 24/11/22
 */
fun NavGraphBuilder.dashboardContentGraph() {
    navigation(
        startDestination = DashboardContentScreen.drawerItem.route,
        route = DASHBOARD_CONTENT_GRAPH
    ) {
        composable(DashboardContentScreen.drawerItem.route) {
            DashboardContentScreen()
        }
    }
}