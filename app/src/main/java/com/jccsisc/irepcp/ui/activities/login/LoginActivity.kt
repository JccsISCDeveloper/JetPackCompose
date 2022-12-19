package com.jccsisc.irepcp.ui.activities.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.jccsisc.irepcp.ui.activities.home.MainActivity
import com.jccsisc.irepcp.ui.activities.login.ui.navigation.LoginNavigation
import com.jccsisc.irepcp.ui.theme.GrayBg
import com.jccsisc.irepcp.ui.theme.IREPCPTheme
import com.jccsisc.irepcp.utils.GlobalData.onLoginClick
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    var canAuthenticate = false
    lateinit var prompInfo: BiometricPrompt.PromptInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IREPCPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = GrayBg
                ) {
                    LoginNavigation()
                    onLoginClick = {
                        authenticate(canAuthenticate) {
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }
                    }
                }
            }
        }
        setupAuth {
            canAuthenticate
            canAuthenticate = it
        }
    }

    private fun authenticate(
        canAuthenticate: Boolean,
        auth: (auth: Boolean) -> Unit
    ) {
        if (canAuthenticate) {
            BiometricPrompt(this, ContextCompat.getMainExecutor(this),
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        super.onAuthenticationSucceeded(result)
                        auth(true)
                    }
                }).authenticate(prompInfo)
        } else {
            auth(true)
        }
    }


    private fun setupAuth(canAuthenticate2: (Boolean) -> Unit) {
        if (BiometricManager.from(this)
                .canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL) == BiometricManager.BIOMETRIC_SUCCESS
        ) {
            canAuthenticate = true
            prompInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle("Autenticación Biométrica")
                .setSubtitle("Autentícate utilizando el sensor biométrico")
                .setAllowedAuthenticators(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)
                .build()
            canAuthenticate2(true)
        }
        else {
            Log.e("ERROR", "No pudo acceder a los permisos biometricos")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {

}