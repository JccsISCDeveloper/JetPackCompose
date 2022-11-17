package com.jccsisc.irepcp.utils

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.utils
 * Created by Julio Cesar Camacho Silva on 14/11/22
 * En este archivo encontraremos las xtension functions y algunos componentes Composable
 */


/**
 * --------------------Componentes Composable
 * */
@Composable
fun SpacerApp(size: Int) {
    Spacer(modifier = Modifier.size(size.dp))
}
/**
 * Manipular el color y texto de la barra de tareas
 * */
@Composable
fun SetNavbarColor(color: Color, useDarkIcons: Boolean = true) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(color = color, darkIcons = useDarkIcons)
    }
}

/**
 * -------------------- Extesion functions
 * */
