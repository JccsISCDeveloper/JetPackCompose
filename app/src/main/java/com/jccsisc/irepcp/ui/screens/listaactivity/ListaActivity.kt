package com.jccsisc.irepcp.ui.screens.listaactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.ui.theme.*

class ListaActivity : ComponentActivity() {

    /*val datos = listOf(
        "Razas",
        "Entrenamiento",
        "Alimento",
        "Belleza",
        "Reproducción",
        "Salud",
        "Noticias"
    )*/

    val listaAarticulos = listOf(
        Articulo(R.drawable.razas, "Razas", "Detalles sobre razas."),
        Articulo(
            R.drawable.mebelleza,
            "Belleza",
            "Recomendaciones para dar la mejor apariencia a tus mascotas."
        ),
        Articulo(
            R.drawable.salud,
            "Salud",
            "Consejos para cuidar y mejorar la salud de tus mascotas."
        ),
        Articulo(
            R.drawable.mantenimiento,
            "Cuidados",
            "Consejos y sugerencias para los cuidados de tu mascota."
        ),
        Articulo(
            R.drawable.reproduccion,
            "Reproducción",
            "Artículos especializados sobre el tema de reproducción."
        ),
        Articulo(
            R.drawable.noticias,
            "Noticias",
            "Novedades y curiosidades del mundo de las mascotas."
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IREPCPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = GrayBg
                ) {
                    ListaScreen(listaAarticulos)
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DefaultPreview2() {
    val listaAarticulos = listOf(
        Articulo(R.drawable.razas, "Razas", "Detalles sobre razas"),
        Articulo(
            R.drawable.mebelleza,
            "Belleza",
            "Recomendaciones para dar la mejor apariencia a tus mascotas"
        ),
        Articulo(
            R.drawable.salud,
            "Salud",
            "Consejos para cuidar y mejorar la salud de tus mascotas"
        ),
        Articulo(
            R.drawable.mantenimiento,
            "Cuidados",
            "Consejos y sugerencias para los cuidados de tu mascota"
        ),
        Articulo(
            R.drawable.reproduccion,
            "Reproducción",
            "Artículos especializados sobre el tema de reproducción"
        ),
        Articulo(
            R.drawable.noticias,
            "Noticias",
            "Novedades y curiosidades del mundo de las mascotas"
        )
    )
    ListaScreen(listaAarticulos)
}

@Composable
private fun ListaScreen(datos: List<Articulo>) {
    LazyColumn(
        modifier = Modifier.background(Gray300),
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_16)),
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.padding_10)
        )
    ) {
        items(datos) { item ->
            ListItemRow(item)
        }
    }
}

@Composable
private fun ListItemRow(item: Articulo) {
    var masInformacion by rememberSaveable { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = MyShapes.medium)
            .background(color = Color.White)
            .padding(
                horizontal = dimensionResource(id = R.dimen.padding_16),
                vertical = dimensionResource(
                    id = R.dimen.padding_10
                )
            )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = item.image),
                contentDescription = item.details,
                modifier = Modifier.size(35.dp)
            )
            Column(
                modifier = Modifier
                    .animateContentSize(
                        animationSpec = tween(70, 0, LinearEasing)
                    )
                    .weight(1f)
                    .padding(start = dimensionResource(id = R.dimen.padding_6))
            ) {
                Text(
                    text = item.title,
                    style = TextStyle(
                        color = PrimaryColor,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = Monserrat
                    )
                )
                if (masInformacion)
                    Text(
                        text = item.details,
                        style = TextStyle(
                            color = PrimaryColor,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = Monserrat
                        )
                    )
            }
            IconButton(onClick = { masInformacion = !masInformacion }) {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "Icono expandir",
                    modifier = Modifier.rotate(if (masInformacion) 180f else 360f)
                )
            }
        }
    }
}