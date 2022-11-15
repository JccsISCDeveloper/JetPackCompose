package com.jccsisc.irepcp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jccsisc.irepcp.SplashScreen
import com.jccsisc.irepcp.login.ui.LoginScreen

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.navigation
 * Created by Julio Cesar Camacho Silva on 14/11/22
 */
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.SplashScreen.route) {
        composable(AppScreens.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(AppScreens.LoginScreen.route) {
            LoginScreen()
        }
    }
}