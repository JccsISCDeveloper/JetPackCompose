package com.jccsisc.irepcp.ui.navigation

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.navigation
 * Created by Julio Cesar Camacho Silva on 14/11/22
 */
sealed class Screens(val route: String) {
    object SplashScreen: Screens("splash_screen")
    object LoginScreen: Screens("login_screen")
}