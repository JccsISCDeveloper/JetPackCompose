package com.jccsisc.irepcp.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.ui.theme.*

class ListaActivity : ComponentActivity() {

    val datos = listOf(
        "Razas",
        "Entrenamiento",
        "Alimento",
        "Belleza",
        "Reproducción",
        "Salud",
        "Noticias"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IREPCPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ListaScreen(datos)
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DefaultPreview2() {
    val datos = listOf(
        "Razas",
        "Entrenamiento",
        "Alimento",
        "Belleza",
        "Reproducción",
        "Salud",
        "Noticias"
    )

    ListaScreen(datos)
}

@Composable
private fun ListaScreen(datos: List<String>) {
    LazyColumn(
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_16)),
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.padding_6)
        )
    ) {
        items(datos) { item ->
            ListItemRow(item)
        }
    }
}

@Composable
private fun ListItemRow(item: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = MyShapes.medium)
            .background(color = SecondaryLight)
            .padding(
                horizontal = dimensionResource(id = R.dimen.padding_16),
                vertical = dimensionResource(
                    id = R.dimen.padding_10
                )
            )
    ) {
        Row() {
            Text(
                text = item,
                modifier = Modifier.weight(1f),
                style = TextStyle(color = GrayBg),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = Monserrat
            )
            OutlinedButton(onClick = { /*TODO*/ }) {
                Text(text = "Más...")
            }
        }
    }
}