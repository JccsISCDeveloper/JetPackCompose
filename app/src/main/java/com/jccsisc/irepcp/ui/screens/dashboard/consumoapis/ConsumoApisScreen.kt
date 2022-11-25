package com.jccsisc.irepcp.ui.screens.dashboard.consumoapis

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jccsisc.irepcp.ui.theme.Gray300

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.dashboard.pantalla3
 * Created by Julio Cesar Camacho Silva on 25/11/22
 */
@Composable
fun ConsumoApisScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Gray300), contentAlignment = Alignment.Center) {
        Text(text = "Consumo apis")
    }
}