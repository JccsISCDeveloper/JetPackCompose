package com.jccsisc.irepcp.ui.screens.homegraph.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.jccsisc.irepcp.ui.navigation.CurrentRoute
import com.jccsisc.irepcp.ui.screens.homegraph.ScreensHome
import com.jccsisc.irepcp.ui.theme.PrimaryColor
import com.jccsisc.irepcp.ui.theme.PrimaryDarkColor

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.homegraph.home.ui.components
 * Created by Julio Cesar Camacho Silva on 23/11/22
 */
@Composable
fun MyBottomBar(navController: NavHostController, navItems: List<ScreensHome>) {
    val currenRoute = CurrentRoute(navController)

    BottomNavigation(backgroundColor = PrimaryColor) {
        navItems.forEach { screen ->
            BottomNavigationItem(
                selected = currenRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                    }
                },
                icon = { Icon(imageVector = screen.icon, contentDescription = "") },
                label = { Text(text = screen.title) },
                alwaysShowLabel = false
            )
        }
    }
}