package com.jccsisc.irepcp.ui.activities.home

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.ui.activities.home.navigation.AppNavigation
import com.jccsisc.irepcp.ui.activities.login.ui.login.ui.LoginViewModel
import com.jccsisc.irepcp.ui.theme.IREPCPTheme
import com.jccsisc.irepcp.ui.theme.PrimaryColor
import com.jccsisc.irepcp.utils.GlobalData
import com.jccsisc.irepcp.utils.GlobalData.askPermissions
import com.jccsisc.irepcp.utils.GlobalData.showCameraView
import com.jccsisc.irepcp.utils.GlobalData.transparentNavBar
import com.jccsisc.irepcp.utils.setColor
import com.jccsisc.irepcp.utils.setColorNavBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Log.i("permiso", "Permiso aceptado")
        } else {
            Log.i("permiso", "Permiso rechazado")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

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

        askPermissions = {
            requestCameraPermission()
        }
        transparentNavBar = {
            if (it) {
                setColorNavBar(android.graphics.Color.TRANSPARENT)
            } else {
                setColorNavBar(R.color.primaryColor)
            }
        }
    }

    private fun requestCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                Log.i("permiso", "previamente aceptado")
                showCameraView(true)
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.CAMERA
            ) -> {
                Log.i(
                    "permiso",
                    "Muestra dialog explicando porque debe aceptar los permisos de la camara"
                )
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
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