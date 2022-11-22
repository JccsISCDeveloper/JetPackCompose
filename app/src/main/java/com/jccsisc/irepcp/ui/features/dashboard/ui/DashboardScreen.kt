package com.jccsisc.irepcp.ui.features.dashboard.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jccsisc.irepcp.IREPApp
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.ui.features.dashboard.data.model.DrawerOption
import com.jccsisc.irepcp.ui.features.dashboard.data.model.listOptions
import com.jccsisc.irepcp.ui.theme.BlackAbi
import com.jccsisc.irepcp.ui.theme.ColorHearder
import com.jccsisc.irepcp.utils.SetNavbarColor
import kotlinx.coroutines.launch

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.features.dashboard.ui
 * Created by Julio Cesar Camacho Silva on 16/11/22
 */
@Composable
fun DashboardScreen() {
    SetNavbarColor(color = BlackAbi, useDarkIcons = false)

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            MyTopAppbar(
                onClickDrawer = {
                    coroutineScope.launch { scaffoldState.drawerState.open() }
                }, onInfoClick = {
                    Toast.makeText(
                        IREPApp.INSTANCE.baseContext,
                        "Click en info", Toast.LENGTH_SHORT
                    ).show()
                }, onUpdateClick = {

                }
            )
        },
        scaffoldState = scaffoldState,
        drawerContent = {
            MyDrawerLayout(onClickDrawer = {
                coroutineScope.launch { scaffoldState.drawerState.close() }
            }, optionList = listOptions)
        }
    ) { padding ->
        BodyDashboard(modifier = Modifier.padding(padding))
    }
}

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
        }
    )
}

@Composable
fun MyDrawerLayout(
    onClickDrawer: () -> Unit,
    optionList: List<DrawerOption>,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BlackAbi)
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(ColorHearder)
        ) {

            CircularProgressIndicator(
                progress = 0.8f,
                modifier = modifier.padding(12.dp)
                    .size(60.dp),
                color = Color.Red,
                strokeWidth = 3.dp
            )
        }

        LazyColumn {
            items(optionList) { option ->
                ItemDrawer(option = option, onOptionClick = {
                    //todo navegar a la otra vista
                    Toast.makeText(IREPApp.INSTANCE, "Click ${it.name}", Toast.LENGTH_SHORT).show()
                })
            }
        }
    }
}

@Composable
fun ItemDrawer(
    option: DrawerOption,
    onOptionClick: (option: DrawerOption) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable { onOptionClick(option) }) {
        Icon(
            imageVector = option.navIcon,
            contentDescription = option.name,
            modifier = modifier.padding(10.dp),
            tint = Color.White
        )
        Text(text = option.name, modifier = modifier.padding(10.dp), color = Color.White)
    }
}

@Composable
fun BodyDashboard(modifier: Modifier = Modifier) {
    Box() {
        Card(
            modifier = modifier
                .background(Color.White)
                .width(200.dp)
                .height(100.dp),
            shape = RoundedCornerShape(30.dp),
        ) {

        }
    }
}