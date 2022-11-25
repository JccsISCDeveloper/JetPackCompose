package com.jccsisc.irepcp.ui.screens.dashboard.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.ui.theme.PrimaryDarkColor

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.homegraph.home.ui.components
 * Created by Julio Cesar Camacho Silva on 23/11/22
 */
@Composable
fun MyTopAppbar(onClickDrawer: () -> Unit, onInfoClick: () -> Unit, displaySnackBarClick: () -> Unit) {
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
        },
        backgroundColor = PrimaryDarkColor
    )
}