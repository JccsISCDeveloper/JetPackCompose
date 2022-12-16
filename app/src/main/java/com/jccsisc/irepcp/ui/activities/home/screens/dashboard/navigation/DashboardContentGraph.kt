package com.jccsisc.irepcp.ui.activities.home.screens.dashboard.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.jccsisc.irepcp.core.constants.Constants.DASHBOARD_CONTENT_GRAPH
import com.jccsisc.irepcp.ui.activities.home.screens.dashboard.DashboardContentScreen
import com.jccsisc.irepcp.ui.activities.home.screens.dashboard.navigation.ScreensDashboard.DashboardContentScreen

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.homegraph
 * Created by Julio Cesar Camacho Silva on 24/11/22
 */
fun NavGraphBuilder.dashboardContentGraph(principalNavController: NavHostController) {
    navigation(
        startDestination = DashboardContentScreen.drawerItem.route,
        route = DASHBOARD_CONTENT_GRAPH
    ) {
        composable(DashboardContentScreen.drawerItem.route) {
            DashboardContentScreen(principalNavController)
        }
    }
}