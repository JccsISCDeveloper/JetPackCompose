package com.jccsisc.irepcp.ui.screens.logingraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.jccsisc.irepcp.ui.screens.SplashScreen
import com.jccsisc.irepcp.ui.screens.dashboard.navigation.DASHBOARD_CONTENT_GRAPH
import com.jccsisc.irepcp.ui.screens.logingraph.login.ui.LoginScreen
import com.jccsisc.irepcp.ui.screens.logingraph.login.ui.LoginViewModel

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.logingraph
 * Created by Julio Cesar Camacho Silva on 24/11/22
 */
fun NavGraphBuilder.loginGraph(navController: NavHostController, loginViewModel: LoginViewModel) {
    navigation(
        startDestination = ScreensLogin.SplashScreen.route,
        route = LOGIN_GRAPH
    ) {
        //todo eliminar este splash, hacerlo con activity
        composable(ScreensLogin.SplashScreen.route) {
            SplashScreen(onNavigationToLogin = {
                navController.popBackStack()
                navController.navigate(ScreensLogin.LoginScreen.route)
            })
        }
        composable(ScreensLogin.LoginScreen.route) {
            LoginScreen(
                loginViewModel,
                onNavigationToDashboard = {
                    navController.popBackStack()
                    navController.navigate(DASHBOARD_CONTENT_GRAPH)
                }
            )
        }
    }
}