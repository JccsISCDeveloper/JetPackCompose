package com.jccsisc.irepcp.ui.activities.login.ui.navigation

import com.jccsisc.irepcp.core.constants.Constants.LOGIN_SCREEN
import com.jccsisc.irepcp.core.constants.Constants.SPLASH_SCREEN

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.navigation
 * Created by Julio Cesar Camacho Silva on 14/11/22
 */
sealed class ScreensLogin(val route: String) {
    object SplashScreen: ScreensLogin(SPLASH_SCREEN)
    object LoginScreen: ScreensLogin(LOGIN_SCREEN)
}