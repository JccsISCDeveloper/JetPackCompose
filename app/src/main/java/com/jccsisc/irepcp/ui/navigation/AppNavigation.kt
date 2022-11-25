package com.jccsisc.irepcp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.jccsisc.irepcp.ui.screens.dashboard.dashboardContentGraph
import com.jccsisc.irepcp.ui.screens.logingraph.LOGIN_GRAPH
import com.jccsisc.irepcp.ui.screens.logingraph.ROOT_GRAPH
import com.jccsisc.irepcp.ui.screens.logingraph.login.ui.LoginViewModel
import com.jccsisc.irepcp.ui.screens.logingraph.loginGraph

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.navigation
 * Created by Julio Cesar Camacho Silva on 14/11/22
 */
@Composable
fun AppNavigation(loginViewModel: LoginViewModel) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = LOGIN_GRAPH,
        route = ROOT_GRAPH
    ) {
        loginGraph(navController, loginViewModel)
        dashboardContentGraph()
    }
}
