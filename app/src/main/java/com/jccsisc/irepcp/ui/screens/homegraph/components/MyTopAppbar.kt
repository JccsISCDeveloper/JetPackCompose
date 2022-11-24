package com.jccsisc.irepcp.ui.screens.homegraph.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
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
fun MyTopAppbar(onClickDrawer: () -> Unit, onInfoClick: () -> Unit, onUpdateClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        navigationIcon = {
            IconButton(onClick = { onClickDrawer() }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "ic menu")
            }
        },
        actions = {
            IconButton(onClick = { onInfoClick() }) {
                Icon(imageVector = Icons.Default.Info, contentDescription = "ic info")
            }
            IconButton(onClick = { onUpdateClick() }) {
                Icon(imageVector = Icons.Default.Refresh, contentDescription = "ic refresh")
            }
        },
        backgroundColor = PrimaryDarkColor
    )
}