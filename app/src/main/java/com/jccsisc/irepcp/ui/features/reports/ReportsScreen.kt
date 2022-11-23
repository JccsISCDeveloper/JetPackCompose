package com.jccsisc.irepcp.ui.features.reports

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.features.reports
 * Created by Julio Cesar Camacho Silva on 22/11/22
 */
@Composable
fun ReportsScreen() {
    var num by rememberSaveable { mutableStateOf(0) }

    Column {
        Text(text = "Conteo: $num")

        Divider(thickness = 20.dp, color = Color.Transparent)

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Counter(buttonTitle = "Aumentar conteo") {
                num++
            }
            Counter("Disminuir conteo") {
                num--
            }
        }
    }
}

@Composable
fun Counter(buttonTitle: String, updateCount: () -> Unit) {
    Button(onClick = {
        updateCount()
    }) {
        Text(text = buttonTitle)
    }
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.NEXUS_5)
@Composable
private fun DefaultPreview() {
    ReportsScreen()
}