package com.jccsisc.irepcp.ui.activities.login.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jccsisc.irepcp.core.constants.Constants
import com.jccsisc.irepcp.core.constants.Constants.LOGIN_GRAPH
import com.jccsisc.irepcp.core.constants.Constants.ROOT_GRAPH
import com.jccsisc.irepcp.ui.activities.login.SplashScreen
import com.jccsisc.irepcp.ui.activities.login.ui.login.ui.LoginScreen
import com.jccsisc.irepcp.ui.activities.login.ui.login.ui.LoginViewModel

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.logingraph
 * Created by Julio Cesar Camacho Silva on 24/11/22
 */
@Composable
fun LoginNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreensLogin.SplashScreen.route,
        route = ROOT_GRAPH
    ) {
        //todo eliminar este splash, hacerlo con activity
        composable(ScreensLogin.SplashScreen.route) {
            SplashScreen(onNavigationToLogin = {
                navController.popBackStack()
                navController.navigate(ScreensLogin.LoginScreen.route)
            })
        }
        composable(ScreensLogin.LoginScreen.route) {
            LoginScreen()
        }
    }
}