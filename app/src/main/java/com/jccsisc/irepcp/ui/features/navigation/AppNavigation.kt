package com.jccsisc.irepcp.ui.features.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
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
        startDestination = AppScreens.SplashScreen.route
    ) {
        composable(AppScreens.SplashScreen.route) { SplashScreen(navController) }
        composable(AppScreens.LoginScreen.route) {
            LoginScreen(
                loginViewModel,
                onNavigationToDashboard = {
                    navController.navigate(AppScreens.DashboardScreen.route)
                })
        }
        composable(AppScreens.DashboardScreen.route) { DashboardScreen() }
/*        navigation(
            startDestination = AppScreens.DashboardScreen.route,
            route = AppScreens.DashboardScreen.route
        ) {
            composable(AppScreens.DashboardScreen.route) { DashboardScreen() }
        }*/
    }
}