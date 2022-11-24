package com.jccsisc.irepcp.ui.screens.homegraph.home.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jccsisc.irepcp.IREPApp
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.ui.screens.homegraph.home.data.model.DrawerOption
import com.jccsisc.irepcp.ui.screens.homegraph.home.data.model.listOptions
import com.jccsisc.irepcp.ui.screens.homegraph.home.ui.components.MyDrawerLayout
import com.jccsisc.irepcp.ui.screens.homegraph.home.ui.components.MyTopAppbar
import com.jccsisc.irepcp.ui.theme.BlackAbi
import com.jccsisc.irepcp.ui.theme.ColorHearder
import com.jccsisc.irepcp.ui.theme.Gray50
import com.jccsisc.irepcp.utils.SetNavbarColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.features.dashboard.ui
 * Created by Julio Cesar Camacho Silva on 16/11/22
 */
@Composable
fun HomeScreen(onClickOption: (option: DrawerOption) -> Unit) {
    SetNavbarColor(color = BlackAbi, useDarkIcons = false)

    val scaffoldState = rememberScaffoldState()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = { HeaderHome(coroutineScope = coroutineScope, scaffoldState = scaffoldState) },
        scaffoldState = scaffoldState,
        drawerContent = {
            MyDrawerLayout(onClickDrawer = { onClickOption(it) }, optionList = listOptions)
        }
    ) { padding ->
        BodyDashboard(modifier = Modifier.padding(padding))
    }
}

/**
 * Header Home
 * */
@Composable
private fun HeaderHome(coroutineScope: CoroutineScope, scaffoldState: ScaffoldState) {
    MyTopAppbar(
        onClickDrawer = {
            coroutineScope.launch { scaffoldState.drawerState.open() }
        }, onInfoClick = {
            Toast.makeText(IREPApp.INSTANCE.baseContext, "Click en info", Toast.LENGTH_SHORT)
                .show()
        }, onUpdateClick = {

        }
    )
}

/**
 * Body Home
 * */
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

/**
 * Footer Home
 * */
