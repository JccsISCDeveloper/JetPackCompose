package com.jccsisc.irepcp.ui.activities.home.screens.dashboard.consumoapis

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
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
fun ConsumoApisScreen(navigateToRickAndMorty: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray300),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Consumo apis")
        Button(onClick = navigateToRickAndMorty) {
            Text(text = "Go Rick and Morty")
        }
    }
}