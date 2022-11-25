package com.jccsisc.irepcp.ui.screens.homegraph.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.ui.navigation.CurrentRoute
import com.jccsisc.irepcp.ui.screens.homegraph.ScreensBottomBar
import com.jccsisc.irepcp.ui.theme.PrimaryColor
import com.jccsisc.irepcp.ui.theme.PrimaryDarkColor
import com.jccsisc.irepcp.ui.theme.PrimaryLight
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.homegraph.home.ui.components
 * Created by Julio Cesar Camacho Silva on 23/11/22
 */
@Composable
fun MyDrawerLayout(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavHostController,
    itemsDrawer: List<ScreensBottomBar>
) {
    Column {
        ConstraintLayout(
            Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            PrimaryLight, PrimaryColor, PrimaryDarkColor
                        )
                    )
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_colombia),
                contentDescription = "Colombia",
                modifier = Modifier.size(60.dp)
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(15.dp)
        )
        val currentRoute = CurrentRoute(navController)
        itemsDrawer.forEach {
            DrawerItem(item = it, selected = currentRoute == it.route) {
                navController.navigate(it.route) {
                    launchSingleTop = true
                }
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            }
        }
    }
}

@Composable
private fun DrawerItem(item: ScreensBottomBar, selected: Boolean, onItemClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(6.dp)
            .clip(RoundedCornerShape(12))
            .background(if (selected) PrimaryColor.copy(alpha = 0.25f) else Color.Transparent)
            .clickable { onItemClick() }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = item.title,
            modifier = Modifier.size(32.dp),
            tint = if (selected) PrimaryDarkColor else Color.Gray
        )
        Spacer(modifier = Modifier.size(12.dp))
        Text(
            text = item.title,
            style = TextStyle(fontSize = 18.sp),
            color = if (selected) PrimaryDarkColor else Color.Black
        )
    }
}