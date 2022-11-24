package com.jccsisc.irepcp.ui.screens.homegraph

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.jccsisc.irepcp.IREPApp
import com.jccsisc.irepcp.ui.screens.homegraph.home.data.model.DrawerOption
import com.jccsisc.irepcp.ui.screens.homegraph.home.data.model.listOptions
import com.jccsisc.irepcp.ui.screens.homegraph.home.ui.HomeScreen
import com.jccsisc.irepcp.ui.screens.homegraph.home.ui.components.MyDrawerLayout
import com.jccsisc.irepcp.ui.screens.homegraph.home.ui.components.MyTopAppbar
import com.jccsisc.irepcp.utils.SetNavbarColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.homegraph
 * Created by Julio Cesar Camacho Silva on 23/11/22
 */
@Composable
fun ContentScreens(onClickOption: (option: DrawerOption) -> Unit) {
    SetNavbarColor(color = Color.Black, useDarkIcons = false)

    val scaffoldState = rememberScaffoldState()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            HeaderContentScreens(
                coroutineScope = coroutineScope,
                scaffoldState = scaffoldState
            )
        },
        scaffoldState = scaffoldState,
        drawerContent = {
            MyDrawerLayout(onClickDrawer = { onClickOption(it) }, optionList = listOptions)
        }
    ) { padding ->
        BodyDashboard(modifier = Modifier.padding(padding))
    }
}

/**
 * Header ContentScreens
 * */
@Composable
private fun HeaderContentScreens(coroutineScope: CoroutineScope, scaffoldState: ScaffoldState) {
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
 * Body ContentScreens
 * */
@Composable
fun BodyDashboard(modifier: Modifier = Modifier) {
    HomeScreen()
}

/**
 * Footer ContentScreens
 * */
