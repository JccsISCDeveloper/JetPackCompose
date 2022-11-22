package com.jccsisc.irepcp.ui.features.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jccsisc.irepcp.ui.features.SplashScreen
import com.jccsisc.irepcp.ui.features.dashboard.ui.DashboardScreen
import com.jccsisc.irepcp.ui.features.login.ui.LoginScreen
import com.jccsisc.irepcp.ui.features.login.ui.LoginViewModel

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
        startDestination = Screens.DashboardScreen.route
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
                    navController.navigate(Screens.DashboardScreen.route)
                }
            )
        }
        composable(Screens.DashboardScreen.route) {
            DashboardScreen()
        }
    }
}