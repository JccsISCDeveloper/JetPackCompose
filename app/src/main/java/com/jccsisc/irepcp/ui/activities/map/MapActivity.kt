package com.jccsisc.irepcp.ui.activities.map

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.google.android.gms.location.LocationCallback
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.ui.activities.map.screens.map.MapScreen
import com.jccsisc.irepcp.ui.theme.GrayBg
import com.jccsisc.irepcp.ui.theme.IREPCPTheme
import com.jccsisc.irepcp.utils.GlobalData.transparentNavBar
import com.jccsisc.irepcp.utils.setColorNavBar

class MapActivity : ComponentActivity() {

    private var locationCallback: LocationCallback? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IREPCPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = GrayBg
                ) {
                    transparentNavBar(true)
                    MapScreen(permission = Manifest.permission.ACCESS_FINE_LOCATION)
                }
            }
        }

        transparentNavBar = {
            if (it) {
                setColorNavBar(android.graphics.Color.TRANSPARENT)
            } else {
                setColorNavBar(R.color.primaryColor)
            }
        }
    }
}