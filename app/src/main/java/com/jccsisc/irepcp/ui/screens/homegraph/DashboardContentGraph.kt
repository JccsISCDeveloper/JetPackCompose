package com.jccsisc.irepcp.ui.screens.homegraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.homegraph
 * Created by Julio Cesar Camacho Silva on 24/11/22
 */
fun NavGraphBuilder.dashboardContentGraph() {
    navigation(
        startDestination = ScreensBottomBar.DashboardContentScreen.route,
        route = DASHBOARD_CONTENT_GRAPH
    ) {
        composable(ScreensBottomBar.DashboardContentScreen.route) {
            DashboardContentScreen()
        }
    }
}