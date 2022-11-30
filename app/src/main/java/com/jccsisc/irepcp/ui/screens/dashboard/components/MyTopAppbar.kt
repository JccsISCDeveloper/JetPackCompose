package com.jccsisc.irepcp.ui.screens.dashboard.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.ui.navigation.CurrentRoute
import com.jccsisc.irepcp.ui.screens.dashboard.navigation.ScreenChildItemDrawer
import com.jccsisc.irepcp.ui.screens.dashboard.navigation.ScreensDashboard
import com.jccsisc.irepcp.ui.screens.dashboard.navigation.model.DrawerChildItem
import com.jccsisc.irepcp.ui.theme.PrimaryDarkColor

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.homegraph.home.ui.components
 * Created by Julio Cesar Camacho Silva on 23/11/22
 */
@Composable
fun MyTopAppbar(
    itemsDrawer: List<ScreensDashboard>,
    itemsbottomBar: List<ScreensDashboard>,
    dashboardNavController: NavHostController,
    onClickDrawer: () -> Unit,
    onInfoClick: () -> Unit,
    displaySnackBarClick: () -> Unit
) {
    var showMenu by remember { mutableStateOf(false) }
    val currentRoute = CurrentRoute(dashboardNavController)
    val chilItems = mutableListOf<ScreenChildItemDrawer>()

    var myTitle = stringResource(id = R.string.app_name)
    itemsDrawer.forEach { item ->
        if (currentRoute == item.drawerItem.route) myTitle = item.drawerItem.title
        item.drawerItem.expandableOptions?.forEach {
            if (it.drawerChildItem.route.isNotEmpty()) {
                chilItems.add(it)
            }
        }
    }
    chilItems.forEach {
        if (currentRoute == it.drawerChildItem.route) myTitle = it.drawerChildItem.title
    }
    itemsbottomBar.forEach {
        if (currentRoute == it.drawerItem.route) myTitle = it.drawerItem.title
    }


    TopAppBar(
        title = { Text(text = myTitle, style = MaterialTheme.typography.h5) },
        navigationIcon = {
            IconButton(onClick = { onClickDrawer() }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "ic menu")
            }
        },
        actions = {
            IconButton(onClick = { onInfoClick() }) {
                Icon(imageVector = Icons.Default.Info, contentDescription = "ic info")
            }
            IconButton(onClick = { displaySnackBarClick() }) {
                Icon(imageVector = Icons.Default.Refresh, contentDescription = "ic refresh")
            }
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "ic morevert")
            }
            DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                DropdownMenuItem(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.Person, contentDescription = "ic person")
                    Text(
                        text = "Idiomas",
                        style = MaterialTheme.typography.overline,
                        fontSize = 16.sp
                    )
                }
                DropdownMenuItem(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.Share, contentDescription = "ic share")
                    Text(
                        text = "Compartir",
                        style = MaterialTheme.typography.overline,
                        fontSize = 16.sp
                    )
                }
            }
        },
        backgroundColor = PrimaryDarkColor
    )
}