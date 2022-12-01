package com.jccsisc.irepcp.ui.screens.mascotas.mascotafeliz

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.ui.screens.listaactivity.ListaActivity

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.dashboard.pantalla1
 * Created by Julio Cesar Camacho Silva on 25/11/22
 */
@Composable
fun MascotaFelizScreen() {
    val mContext = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .clip(shape = MaterialTheme.shapes.medium)
                .padding(dimensionResource(id = R.dimen.padding_6))
                .background(MaterialTheme.colors.secondary)
                .border(dimensionResource(id = R.dimen.border_2), MaterialTheme.colors.primary)
        ) {
            Image(painter = painterResource(id = R.drawable.dog), contentDescription = "dog")
        }
        Text(
            text = "Mascota Feliz",
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.secondaryVariant
        )
        Text(
            text = "Bienvenido, usuario",
            style = MaterialTheme.typography.body2
        )
        Row {
            OutlinedButton(onClick = {

            }) {
                Text(text = "Tu mascota")
            }
            Spacer(modifier = Modifier.width(10.dp))
            OutlinedButton(onClick = {
                mContext.startActivity(
                    Intent(
                        mContext,
                        ListaActivity::class.java
                    )
                )
            }) {
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