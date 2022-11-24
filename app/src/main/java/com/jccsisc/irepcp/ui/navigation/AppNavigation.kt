package com.jccsisc.irepcp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jccsisc.irepcp.ui.screens.SplashScreen
import com.jccsisc.irepcp.ui.screens.homegraph.ScreensHome
import com.jccsisc.irepcp.ui.screens.homegraph.dashboardGraph
import com.jccsisc.irepcp.ui.screens.logingraph.login.ui.LoginScreen
import com.jccsisc.irepcp.ui.screens.logingraph.login.ui.LoginViewModel

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
        startDestination = Screens.SplashScreen.route
    ) {
        //todo eliminar este splash, hacerlo con activity
        composable(Screens.SplashScreen.route) {
            SplashScreen(onNavigationToLogin = {
                navController.popBackStack()
                navController.navigate(Screens.LoginScreen.route)
            })
        }
        composable(Screens.LoginScreen.route) {
            LoginScreen(
                loginViewModel,
                onNavigationToDashboard = {
                    navController.popBackStack()
                    navController.navigate(ScreensHome.ContentScreens.route)
                }
            )
        }
        dashboardGraph(navController)
    }
}
