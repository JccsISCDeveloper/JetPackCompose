package com.jccsisc.irepcp.ui.screens.dashboard.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.jccsisc.irepcp.ui.navigation.CurrentRoute
import com.jccsisc.irepcp.ui.screens.dashboard.navigation.ScreensDashboard
import com.jccsisc.irepcp.ui.theme.PrimaryColor

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.homegraph.home.ui.components
 * Created by Julio Cesar Camacho Silva on 23/11/22
 */
@Composable
fun MyBottomBar(navController: NavHostController, navItems: List<ScreensDashboard>) {
    val currenRoute = CurrentRoute(navController)

    BottomNavigation(backgroundColor = PrimaryColor) {
        navItems.forEach { screen ->
            BottomNavigationItem(
                selected = currenRoute == screen.drawerItem.route,
                onClick = {
                    navController.navigate(screen.drawerItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = screen.drawerItem.icon),
                        contentDescription = "",
                        modifier = Modifier.size(25.dp)
                    )
                },
                label = {
                    Text(
                        text = screen.drawerItem.title,
                        style = MaterialTheme.typography.button
                    )
                },
                alwaysShowLabel = false

            )
        }
    }
}