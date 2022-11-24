package com.jccsisc.irepcp.ui.screens

import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.jccsisc.irepcp.IREPApp
import com.jccsisc.irepcp.ui.screens.homegraph.ScreensHome
import com.jccsisc.irepcp.ui.screens.homegraph.components.MyBottomBar
import com.jccsisc.irepcp.ui.screens.homegraph.components.MyDrawerLayout
import com.jccsisc.irepcp.ui.screens.homegraph.components.MyTopAppbar
import com.jccsisc.irepcp.ui.screens.logingraph.login.ui.LoginViewModel
import com.jccsisc.irepcp.ui.theme.IREPCPTheme
import com.jccsisc.irepcp.ui.theme.PrimaryDarkColor
import com.jccsisc.irepcp.utils.SetNavbarColor
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        //Todo controlar bien el estado del navBar
     /*   transparentNavBar = {
            if (it) {
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                )
            } else {
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                )
            }
        }*/
        setContent {
            IREPCPTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    AppNavigation(loginViewModel)
                    MainScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    IREPCPTheme {
        //LoginScreen()
        //AppNavigation()
    }
}

@Composable
fun MainScreen() {
    SetNavbarColor(color = PrimaryDarkColor, useDarkIcons = false)
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState(
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    )
    val scope = rememberCoroutineScope()
    val itemsbottomBar = listOf(
        ScreensHome.HomeScreen,
        ScreensHome.FavoritesScreen,
        ScreensHome.GalleryScreen
    )

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            HeaderContentScreens(
                coroutineScope = scope,
                scaffoldState = scaffoldState
            )
        },
        bottomBar = { MyBottomBar(navController = navController, navItems = itemsbottomBar) },
        floatingActionButton = { FloatingActionButton(onClick = {}) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Fab icon")
        } },
        isFloatingActionButtonDocked = false,
        floatingActionButtonPosition = FabPosition.End,
        drawerContent = {
//            MyDrawerLayout(onClickDrawer = { onClickOption(it) }, optionList = listOptions)
            MyDrawerLayout(scope, scaffoldState, navController, itemsbottomBar)
        },
        drawerGesturesEnabled = true
    ) { padding ->
        NavigationDashboard(navController = navController, modifier = Modifier.padding(padding))
    }
}

/**
 * Header MainScreen
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