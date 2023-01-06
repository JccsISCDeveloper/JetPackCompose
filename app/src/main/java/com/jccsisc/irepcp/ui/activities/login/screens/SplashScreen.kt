package com.jccsisc.irepcp.ui.activities.login.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.ui.theme.RubikDistressed
import com.jccsisc.irepcp.utils.SetNavbarColor
import kotlinx.coroutines.delay

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp
 * Created by Julio Cesar Camacho Silva on 14/11/22
 */
@Composable
fun SplashScreen(onNavigationToLogin: () -> Unit) {
    LaunchedEffect(key1 = true) {
        delay(1000)
        onNavigationToLogin()
    }
    Splash()
}

@Composable
fun Splash() {
//    GlobalData.transparentNavBar(true)
    SetNavbarColor(color = Color.White, useDarkIcons = false)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_login_logo),
            contentDescription = "",
            modifier = Modifier.size(
                dimensionResource(id = R.dimen.width_150),
                dimensionResource(id = R.dimen.width_150)
            )
        )
        Text(
            text = stringResource(id = R.string.label_welcome),
            style = TextStyle(
                fontSize = 35.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = RubikDistressed
            )
        )
    }
}