package com.jccsisc.irepcp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jccsisc.irepcp.core.constants.Constants.DASHBOARD_CONTENT_GRAPH
import com.jccsisc.irepcp.core.constants.Constants.ROOT_GRAPH
import com.jccsisc.irepcp.core.constants.Constants.TASK_ID
import com.jccsisc.irepcp.ui.screens.dashboard.navigation.dashboardContentGraph
import com.jccsisc.irepcp.ui.screens.logingraph.login.ui.LoginViewModel
import com.jccsisc.irepcp.ui.screens.logingraph.loginGraph
import com.jccsisc.irepcp.ui.screens.rickandmorty.rickAndMortyGraph
import com.jccsisc.irepcp.ui.screens.todomodule.addtasks.ui.AddOrModifyTaskScreen

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
        startDestination = DASHBOARD_CONTENT_GRAPH,
        route = ROOT_GRAPH
    ) {
        loginGraph(navController, loginViewModel)
        dashboardContentGraph(navController)
        rickAndMortyGraph(navController)
        composable(
            route = "${Screens.AddOrModifyTaskScreen.route}/{${TASK_ID}}",
            arguments = listOf(navArgument(TASK_ID) { type = NavType.LongType })
        ) {backStackEntry ->
            val taskId = backStackEntry.arguments?.getLong(TASK_ID) ?: -1L
            AddOrModifyTaskScreen(taskId = taskId) {
                navController.popBackStack()
            }
        }
    }
}
