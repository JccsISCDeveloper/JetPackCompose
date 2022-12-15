package com.jccsisc.irepcp.ui.screens.dashboard.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.core.constants.Constants.DATE_ASC
import com.jccsisc.irepcp.core.constants.Constants.DATE_DESC
import com.jccsisc.irepcp.core.constants.Constants.PRIORITY_ASC
import com.jccsisc.irepcp.core.constants.Constants.PRIORITY_DESC
import com.jccsisc.irepcp.ui.navigation.CurrentRoute
import com.jccsisc.irepcp.ui.screens.dashboard.navigation.ScreenChildItemDrawer
import com.jccsisc.irepcp.ui.screens.dashboard.navigation.ScreensDashboard
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
    onInfoClick: (String) -> Unit,
    displaySnackBarClick: () -> Unit
) {
    var showMenu by remember { mutableStateOf(false) }
    var showFilterMenu by remember { mutableStateOf(false) }
    var showIconInfo by remember { mutableStateOf(false) }
    var showIconRefresh by remember { mutableStateOf(false) }
    val currentRoute = CurrentRoute(dashboardNavController)
    val chilItems = mutableListOf<ScreenChildItemDrawer>()


    var dateTitle by remember { mutableStateOf("Fecha") }
    var prioridyTitle by remember { mutableStateOf("Prioridad") }
    var clickDate by remember { mutableStateOf(true) }
    var clickPriority by remember { mutableStateOf(true) }
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

    showIconInfo = currentRoute == ScreensDashboard.TasksScreen.drawerItem.route

    TopAppBar(
        title = { Text(text = myTitle, style = MaterialTheme.typography.h5) },
        navigationIcon = {
            IconButton(onClick = { onClickDrawer() }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "ic menu")
            }
        },
        actions = {
            if (showIconInfo) {
                IconButton(onClick = { showFilterMenu = !showFilterMenu }) {
                    Icon(imageVector = Icons.Default.Info, contentDescription = "ic info")
                }
                DropdownMenu(
                    expanded = showFilterMenu,
                    onDismissRequest = { showFilterMenu = false }) {
                    DropdownMenuItem(onClick = {
                        showFilterMenu = false
                        clickDate = !clickDate
                        onInfoClick(if (clickDate) DATE_DESC else DATE_ASC)
                    }) {
                        Icon(
                            imageVector = if (clickDate) Icons.Filled.ArrowCircleDown else Icons.Filled.ArrowCircleUp,
                            contentDescription = "ic person"
                        )
                        Text(
                            text = if (clickDate) "FECHA: DESC" else "FECHA: ASC",
                            style = MaterialTheme.typography.overline,
                            fontSize = 16.sp
                        )
                    }
                    DropdownMenuItem(onClick = {
                        showFilterMenu = false
                        clickPriority = !clickPriority
                        onInfoClick(if (clickPriority) PRIORITY_DESC else PRIORITY_ASC)
                    }) {
                        Icon(
                            imageVector = if (clickPriority) Icons.Filled.ArrowCircleDown else Icons.Filled.ArrowCircleUp,
                            contentDescription = "ic share"
                        )
                        Text(
                            text = if (clickPriority) "Prioridad: DESC" else "Prioridad: ASC",
                            style = MaterialTheme.typography.overline,
                            fontSize = 16.sp
                        )
                    }
                }
            }
            if (showIconRefresh) {
                IconButton(onClick = { displaySnackBarClick() }) {
                    Icon(imageVector = Icons.Default.Refresh, contentDescription = "ic refresh")
                }
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