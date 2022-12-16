package com.jccsisc.irepcp.ui.activities.home

import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jccsisc.irepcp.ui.activities.login.ui.login.ui.LoginViewModel
import com.jccsisc.irepcp.ui.activities.home.navigation.AppNavigation
import com.jccsisc.irepcp.ui.theme.IREPCPTheme
import dagger.hilt.android.AndroidEntryPoint

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
                    AppNavigation(loginViewModel)
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