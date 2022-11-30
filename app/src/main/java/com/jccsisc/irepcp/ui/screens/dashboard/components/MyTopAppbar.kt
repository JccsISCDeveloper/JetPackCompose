package com.jccsisc.irepcp.ui.screens.dashboard.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.ui.theme.PrimaryDarkColor

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.homegraph.home.ui.components
 * Created by Julio Cesar Camacho Silva on 23/11/22
 */
@Composable
fun MyTopAppbar(onClickDrawer: () -> Unit, onInfoClick: () -> Unit, displaySnackBarClick: () -> Unit) {
    var showMenu by remember { mutableStateOf(false) }
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name), style = MaterialTheme.typography.h5) },
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
                    Text(text = "Idiomas", style = MaterialTheme.typography.overline, fontSize = 16.sp)
                }
                DropdownMenuItem(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.Share, contentDescription = "ic share")
                    Text(text = "Compartir", style = MaterialTheme.typography.overline, fontSize = 16.sp)
                }
            }
        },
        backgroundColor = PrimaryDarkColor
    )
}