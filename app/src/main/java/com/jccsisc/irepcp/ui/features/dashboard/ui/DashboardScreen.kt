package com.jccsisc.irepcp.ui.features.dashboard.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jccsisc.irepcp.IREPApp
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.ui.theme.ColorBody
import com.jccsisc.irepcp.ui.theme.Purple700
import com.jccsisc.irepcp.utils.SetNavbarColor
import kotlinx.coroutines.launch

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.features.dashboard.ui
 * Created by Julio Cesar Camacho Silva on 16/11/22
 */
@Composable
fun DashboardScreen() {
    SetNavbarColor(color = Purple700)

    val scaffoldState = rememberScaffoldState()
    val corouteinScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            MyTopAppbar(onClickDrawer = {
                corouteinScope.launch { scaffoldState.drawerState.open() }
            },
                onInfoClick = {
                    Toast.makeText(IREPApp.INSTANCE.baseContext, "Click en info", Toast.LENGTH_SHORT).show()
                })
        },
        scaffoldState = scaffoldState,
        drawerContent = {
            MyDrawerLayout(onClickDrawer = {
                corouteinScope.launch { scaffoldState.drawerState.close() }
            })
        }
    ) { padding ->
        BodyDashboard(modifier = Modifier.padding(padding))
    }
}

@Composable
fun MyTopAppbar(onClickDrawer: () -> Unit, onInfoClick: () -> Unit) {
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
        }
    )
}

@Composable
fun MyDrawerLayout(onClickDrawer: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(6.dp)) {

    }
}

@Composable
fun BodyDashboard(modifier: Modifier = Modifier) {
    Box(modifier = modifier.background(ColorBody)) {

    }
}