package com.jccsisc.irepcp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jccsisc.irepcp.navigation.AppScreens
import com.jccsisc.irepcp.utils.GlobalData
import com.jccsisc.irepcp.utils.SetNavbarColor
import kotlinx.coroutines.delay

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp
 * Created by Julio Cesar Camacho Silva on 14/11/22
 */
@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(key1 = true) {
        delay(1000)
        navController.popBackStack()
        navController.navigate(AppScreens.LoginScreen.route)
    }
    Splash()
}

@Composable
fun Splash() {
//    GlobalData.transparentNavBar(true)
    SetNavbarColor(color = Color.White, false)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_login_logo),
            contentDescription = "",
            modifier = Modifier.size(150.dp, 150.dp)
        )
        Text(text = "Bienvenido", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
    }
}