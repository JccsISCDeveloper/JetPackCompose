package com.jccsisc.irepcp.ui.screens.logingraph

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.navigation
 * Created by Julio Cesar Camacho Silva on 14/11/22
 */
const val ROOT_GRAPH = "ROOT_GRAPH"
const val LOGIN_GRAPH = "LOGIN_GRAPH"
sealed class ScreensLogin(val route: String) {
    object SplashScreen: ScreensLogin("splash_screen")
    object LoginScreen: ScreensLogin("login_screen")
}