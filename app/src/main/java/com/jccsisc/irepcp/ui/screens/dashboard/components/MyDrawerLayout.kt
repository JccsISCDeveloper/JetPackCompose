package com.jccsisc.irepcp.ui.screens.dashboard.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.ui.navigation.CurrentRoute
import com.jccsisc.irepcp.ui.navigation.navigateToAnyRoute
import com.jccsisc.irepcp.ui.screens.dashboard.navigation.ScreensDashboard
import com.jccsisc.irepcp.ui.screens.dashboard.navigation.model.DrawerChildItem
import com.jccsisc.irepcp.ui.theme.Monserrat
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
    itemsDrawer: List<ScreensDashboard>
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
        itemsDrawer.forEachIndexed { index, item ->
            if (index % 3 == 0 && index != 0) Divider()
            DrawerItem(
                item = item,
                navController,
                selected = currentRoute == item.drawerItem.route,
                onItemClick = { isHeaderItem ->
                    if (isHeaderItem) {
                        navigateToAnyRoute(
                            navController = navController,
                            route = item.drawerItem.route
                        ) {
                            scope.launch {
                                scaffoldState.drawerState.close()
                            }
                        }
                    }
                },
                onItemChileClick = { childItemSelected ->
                    if (childItemSelected.isNotEmpty()) {
                        navigateToAnyRoute(
                            navController = navController,
                            route = childItemSelected
                        ) {
                            scope.launch {
                                scaffoldState.drawerState.close()
                            }
                        }
                    }
                }
            )
        }
    }
}

@Composable
private fun DrawerItem(
    item: ScreensDashboard,
    navController: NavHostController,
    selected: Boolean,
    onItemClick: (isHeaderItem: Boolean) -> Unit,
    onItemChileClick: (childItemSelected: String) -> Unit
) {
    var masInformacion by remember { mutableStateOf(false) }
    val currentRoute = CurrentRoute(navController)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_6))
            .clip(RoundedCornerShape(12))
            .background(if (selected) PrimaryColor.copy(alpha = 0.25f) else Color.Transparent)
            .clickable {
                onItemClick(item.drawerItem.expandableOptions.isNullOrEmpty())
            }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = item.drawerItem.icon),
            contentDescription = item.drawerItem.title,
            modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.size(12.dp))
        Column(
            modifier = Modifier
                .animateContentSize(animationSpec = tween(80, 0, LinearEasing))
                .weight(1f)
        ) {

            Text(
                text = item.drawerItem.title,
                style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontFamily = Monserrat,
                    fontSize = if (masInformacion) 18.sp else 16.sp,
                    color = if (selected) PrimaryDarkColor else Color.Gray
                )
            )
            if (masInformacion)
                item.drawerItem.expandableOptions?.forEach {
                    DrawerItemChild(
                        it.drawerChildItem,
                        currentRoute == it.drawerChildItem.route
                    ) { chilRouteSelected ->
                        onItemChileClick(chilRouteSelected)
                    }
                }
        }
        if (!item.drawerItem.expandableOptions.isNullOrEmpty()) {
            IconButton(onClick = { masInformacion = !masInformacion }) {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "Icono expandir",
                    modifier = Modifier.rotate(if (masInformacion) 180f else 360f)
                )
            }
        }
    }
}

@Composable
private fun DrawerItemChild(
    item: DrawerChildItem,
    selected: Boolean,
    onItemChildClick: (chilRouteSelected: String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_3))
            .clip(RoundedCornerShape(10))
            .background(if (selected) PrimaryColor.copy(alpha = 0.25f) else Color.Transparent)
            .clickable {
                onItemChildClick(item.route)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = item.icon),
            contentDescription = item.title,
            modifier = Modifier.size(28.dp)
        )
        Text(
            text = item.title,
            style = typography.button,
        )
    }
}
