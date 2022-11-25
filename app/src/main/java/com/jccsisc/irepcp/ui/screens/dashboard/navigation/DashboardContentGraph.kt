package com.jccsisc.irepcp.ui.screens.dashboard

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.jccsisc.irepcp.ui.screens.dashboard.navigation.DASHBOARD_CONTENT_GRAPH
import com.jccsisc.irepcp.ui.screens.dashboard.navigation.ScreensDashboard

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.homegraph
 * Created by Julio Cesar Camacho Silva on 24/11/22
 */
fun NavGraphBuilder.dashboardContentGraph() {
    navigation(
        startDestination = ScreensDashboard.DashboardContentScreen.route,
        route = DASHBOARD_CONTENT_GRAPH
    ) {
        composable(ScreensDashboard.DashboardContentScreen.route) {
            DashboardContentScreen()
        }
    }
}