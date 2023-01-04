package com.jccsisc.irepcp.ui.activities.home.screens.dashboard.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.ui.activities.login.ui.theme.Shapes
import com.jccsisc.irepcp.ui.theme.GrayBg

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.dashboard.detailsfavorites
 * Created by Julio Cesar Camacho Silva on 02/12/22
 */
@Composable
fun DescripcionScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayBg)
            .padding(bottom = dimensionResource(id = R.dimen.padding_58))
    ) {
        LazyColumn(
            contentPadding = PaddingValues(vertical = 0.dp, horizontal = 10.dp),
            verticalArrangement = Arrangement.spacedBy((-2).dp)
        ) {
            item {
                Text(
                    text = stringResource(id = R.string.this_app_is_made_with_jetpack_compose),
                    modifier = Modifier.padding(
                        top = dimensionResource(id = R.dimen.padding_10),
                        bottom = dimensionResource(id = R.dimen.padding_10)),
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Monospace,
                )
            }
            item {
                Text(
                    text = stringResource(id = R.string.content),
                    modifier = Modifier.padding(
                        top = dimensionResource(id = R.dimen.padding_6),
                        bottom = dimensionResource(id = R.dimen.padding_6)
                    ),
                    fontSize = 16.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold
                )
            }
            items(listDescription) { model ->
                CardHome(homeModel = model)
            }
        }
    }
}

@Composable
fun ModulesScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .background(GrayBg)
    ) {
        Text(text = "Los beneficios de ingerir esta fruta shaalala....")
    }
}

@Composable
fun ComponentsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .background(GrayBg)
    ) {
        Text(text = "Puede preparar deliiciosos licuados con ella para lalalshaaaala...")
    }
}

private val listDescription = listOf(
    HomeModel(1, "Arquitectura: ", "MVVM"),
    HomeModel(2, "Consumo de Servicios: ", "Retrofit"),
    HomeModel(3, "Persistencia de datos: ", "ROOM, con migración de base de datos."),
    HomeModel(4, "Coroutines: ", "Si"),
    HomeModel(5, "LottieFields: ", "Si, para mostrar animaciones."),
    HomeModel(6, "Coil: ", "Si, para cargar imagenes."),
    HomeModel(7, "Camera x: ", "Si"),
    HomeModel(8, "Extension functions: ", "Si"),
    HomeModel(9, "Multilenguaje: ", "Si, Inglés/Español"),
    HomeModel(10, "Biometric: ", "Si, para iniciar sesión utilizando la huella dactilar."),
    HomeModel(11, "Otros: ",
        "-Control del color barra notificaciones, transparencia en caso de abrir la camara.\n" +
            "-Abrir galería para seleccionar una foto.\n"
    )
)

private data class HomeModel(
    val id: Int,
    val title: String,
    val descripction: String,
)
@Composable
private fun CardHome(homeModel: HomeModel) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        border = BorderStroke(width = 2.dp, color = Color.Gray),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.rounded_0))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
        ) {
            Text(
                text = homeModel.title,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = homeModel.descripction,
                fontWeight = FontWeight.Medium
            )
        }
    }
}