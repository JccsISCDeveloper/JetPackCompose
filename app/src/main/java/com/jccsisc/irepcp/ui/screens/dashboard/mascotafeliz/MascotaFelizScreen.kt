package com.jccsisc.irepcp.ui.screens.dashboard.mascotafeliz

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.ui.theme.Gray300

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.dashboard.pantalla1
 * Created by Julio Cesar Camacho Silva on 25/11/22
 */
@Composable
fun MascotaFelizScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.dog), contentDescription = "dog")
        Text(
            text = "Mascota Feliz",
            style = MaterialTheme.typography.h3,
            color = Color.Blue
        )
        Text(
            text = "Bienvenido, usuario",
            style = MaterialTheme.typography.subtitle1
        )
        Row {
            OutlinedButton(onClick = { /*TODO*/ }) {
                Text(text = "Tu mascota")
            }
            Spacer(modifier = Modifier.width(10.dp))
            OutlinedButton(onClick = { /*TODO*/ }) {
                Text(text = "Contenidos")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewMascota() {
    MascotaFelizScreen()
}