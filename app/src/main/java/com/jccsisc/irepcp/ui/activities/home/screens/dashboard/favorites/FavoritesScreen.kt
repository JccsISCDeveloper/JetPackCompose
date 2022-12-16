package com.jccsisc.irepcp.ui.activities.home.screens.dashboard.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jccsisc.irepcp.ui.activities.home.screens.dashboard.navigation.ScreensDashboard
import com.jccsisc.irepcp.ui.theme.PrimaryColor
import com.jccsisc.irepcp.ui.theme.GrayBg

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.features.reports
 * Created by Julio Cesar Camacho Silva on 22/11/22
 */
@Composable
fun FavoritesScreen(navController: NavHostController) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 10.dp, horizontal = 15.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        item {
            Text(
                text = "Productos de Super Mercado",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryColor
                )
            )
        }

//        items(listaProductos) { producto ->
//            CardProducto(producto = producto)
//        }
        itemsIndexed(listaProductos) { posicion, producto ->
            if (posicion % 5 == 0 && posicion != 0) {
                LazyRow {
                    items(listaPublicidad) { publicidad ->
                        CardPublicidad(publicidad = publicidad)
                    }
                }
            } else {
                CardProducto(producto = producto, navController)
            }
        }
    }
}

data class Producto(val nombre: String, val precio: Double)
data class Publicidad(val titulo: String)

private val listaProductos = listOf(
    Producto(nombre = "Manzana", precio = 18.99),
    Producto(nombre = "Avena 4kg", precio = 150.43),
    Producto(nombre = "Longaniza Kg", precio = 80.00),
    Producto(nombre = "Pera Kg", precio = 46.90),
    Producto(nombre = "Piña Kg", precio = 70.00),
    Producto(nombre = "Pepino Kg", precio = 30.50),
    Producto(nombre = "Mayonesa", precio = 56.00),
    Producto(nombre = "Atún 8PCK", precio = 160.43),
    Producto(nombre = "Cereal", precio = 97.63),
    Producto(nombre = "Leche LALA 12PCK", precio = 315.00),
    Producto(nombre = "Azúcar", precio = 53.00),
    Producto(nombre = "Salmón", precio = 359.96),
    Producto(nombre = "Agua 112PCK", precio = 135.50)
)

private val listaPublicidad = listOf(
    Publicidad("Publicidad 1"),
    Publicidad("Publicidad 2"),
    Publicidad("Publicidad 3"),
    Publicidad("Publicidad 4")
)

@Composable
fun CardProducto(producto: Producto, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .clickable {
                navController.navigate("${ScreensDashboard.DetailsFavoritesScreen.drawerItem.route}/${producto.nombre}")
            },
        shape = RoundedCornerShape(14.dp),
        elevation = 3.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(GrayBg)
                .padding(6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = producto.nombre,
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium)
            )
            Text(
                text = "${producto.precio} MX",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    fontStyle = FontStyle.Italic
                )
            )
        }
    }
}

@Composable
fun CardPublicidad(publicidad: Publicidad) {
    Box(
        modifier = Modifier
            .padding(end = 8.dp)
            .size(140.dp)
            .clip(RoundedCornerShape(12))
            .background(
                Color.LightGray
            ), contentAlignment = Alignment.Center
    ) {
        Text(text = publicidad.titulo, style = TextStyle(fontWeight = FontWeight.Black))
    }
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.NEXUS_5)
@Composable
private fun DefaultPreview() {
    FavoritesScreen(navController = rememberNavController())
}