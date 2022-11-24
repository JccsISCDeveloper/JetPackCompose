package com.jccsisc.irepcp.ui.screens.routestartgraph.routestart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.jccsisc.irepcp.ui.theme.BlackAbi
import com.jccsisc.irepcp.ui.theme.GrayBg

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.features.routestart
 * Created by Julio Cesar Camacho Silva on 23/11/22
 */
@Composable
fun RouteStartScreen(onNavigationToDetailsRoute: (String) -> Unit) {
    var textValue by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayBg),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Pantalla 1", style = TextStyle(color = BlackAbi, fontSize = 25.sp))
        TextField(
            value = textValue,
            onValueChange = { textValue = it },
            label = { Text(text = "Introduce un texto") }
        )
        Button(onClick = { onNavigationToDetailsRoute(textValue) }) {
            Text(text = "Ir a detalles")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    RouteStartScreen(onNavigationToDetailsRoute = {})
}