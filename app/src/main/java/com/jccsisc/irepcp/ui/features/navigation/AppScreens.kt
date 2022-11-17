package com.jccsisc.irepcp.ui.features.navigation

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.navigation
 * Created by Julio Cesar Camacho Silva on 14/11/22
 */
sealed class AppScreens(val route: String) {
    object SplashScreen: AppScreens("splash_screen")
    object LoginScreen: AppScreens("login_screen")
    object DashboardScreen: AppScreens("dashboard")
}
