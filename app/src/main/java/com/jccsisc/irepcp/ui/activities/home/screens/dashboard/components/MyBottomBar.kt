package com.jccsisc.irepcp.ui.activities.home.screens.dashboard.components

import android.content.Intent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jccsisc.irepcp.ui.activities.home.navigation.navigateToAnyRoute
import com.jccsisc.irepcp.ui.activities.home.screens.dashboard.isTheseRoute
import com.jccsisc.irepcp.ui.activities.home.screens.dashboard.navigation.ScreensDashboard
import com.jccsisc.irepcp.ui.activities.map.MapActivity

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.homegraph.home.ui.components
 * Created by Julio Cesar Camacho Silva on 23/11/22
 */
@Composable
fun MyBottomBar(
    currenRoute: String,
    navController: NavHostController,
    navItems: List<ScreensDashboard>
) {
    val mContext = LocalContext.current

    BottomAppBar(
        cutoutShape = MaterialTheme.shapes.small.copy(
            CornerSize(percent = 50)
        )
    ) {
        BottomNavigation(
            modifier = Modifier
                .padding(0.dp, 0.dp, if (isTheseRoute(currenRoute)) 60.dp else 0.dp, 0.dp),
            elevation = 0.dp
        ) {
            navItems.forEach { screen ->
                BottomNavigationItem(
                    selected = currenRoute == screen.drawerItem.route,
                    onClick = {
                        if (screen.drawerItem.route == ScreensDashboard.MapsScreen.drawerItem.route) {
                            mContext.startActivity(Intent(mContext, MapActivity::class.java))
                        } else {
                            navigateToAnyRoute(
                                navController = navController,
                                route = screen.drawerItem.route,
                                action = {}
                            )
                        }
                    },
                    icon = {
                        screen.drawerItem.icon?.let { painterResource(id = it) }?.let {
                            Icon(
                                painter = it,
                                contentDescription = "",
                                modifier = Modifier.size(25.dp)
                            )
                        }
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
}