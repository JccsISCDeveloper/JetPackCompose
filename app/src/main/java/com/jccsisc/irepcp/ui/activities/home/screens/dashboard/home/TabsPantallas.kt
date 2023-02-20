package com.jccsisc.irepcp.ui.activities.home.screens.dashboard.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.core.constants.Constants.NO_VALUE
import com.jccsisc.irepcp.ui.theme.*

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.dashboard.detailsfavorites
 * Created by Julio Cesar Camacho Silva on 02/12/22
 */
/**
 * ---------------------------SCREEN DESCRIPTION---------------------------
 * */
@Composable
fun DescripcionScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayBg)
            .padding(bottom = dimensionResource(id = R.dimen.padding_58))
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 0.dp, horizontal = 10.dp),
            verticalArrangement = Arrangement.spacedBy((-2).dp)
        ) {
            item {
                Text(
                    text = stringResource(id = R.string.this_app_is_made_with_jetpack_compose),
                    modifier = Modifier.padding(
                        top = dimensionResource(id = R.dimen.padding_10),
                        bottom = dimensionResource(id = R.dimen.padding_10)
                    ),
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
                CardDescription(homeModel = model)
            }
        }
    }
}

private val listDescription = listOf(
    HomeModel(id = 1, title = "Arquitectura: ", descripction = "MVVM"),
    HomeModel(id = 2, title = "Consumo de Servicios: ", descripction = "Retrofit"),
    HomeModel(
        id = 3,
        title = "Persistencia de datos: ",
        descripction = "ROOM, con migración de base de datos."
    ),
    HomeModel(id = 4, title = "Coroutines: ", descripction = "Si"),
    HomeModel(id = 5, title = "LottieFields: ", descripction = "Si, para mostrar animaciones."),
    HomeModel(id = 6, title = "Coil: ", descripction = "Si, para cargar imagenes."),
    HomeModel(id = 7, title = "Camera x: ", descripction = "Si"),
    HomeModel(id = 8, title = "Extension functions: ", descripction = "Si"),
    HomeModel(id = 9, title = "Multilenguaje: ", descripction = "Si, Inglés/Español"),
    HomeModel(id = 10, title = "Inyección de dependencias: ", descripction = "Dagger Hilt"),
    HomeModel(
        id = 11,
        title = "Biometric: ",
        descripction = "Si, para iniciar sesión utilizando la huella dactilar."
    ),
    HomeModel(
        12, title = "Otros: ",
        descripction = "-Control del color barra notificaciones, transparencia en caso de abrir la camara.\n" +
                "-Abrir galería para seleccionar una foto.\n" +
                "-Uso de animasiones en los composables.\n" +
                "-Creación de recursos: Strings, dimens, colors, fuentes.\n" +
                "-Zoom en la imagen del detalle del libro."
    ),
    HomeModel(
        13, title = "Modulos funcionales: ",
        descripction = "Modulo Libros, completamente funcional, CRUD.\nModulo Tareas, completamente funcional. CRUD." +
                "\nModulo Apis: Por el momento solo se hizo el consumo de una api."
    )
)

private data class HomeModel(
    val id: Int = -1,
    val title: String = NO_VALUE,
    @DrawableRes val image: Int = -1,
    val descripction: String = NO_VALUE,
)

@Composable
private fun CardDescription(homeModel: HomeModel) {
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

/**
 * ---------------------------SCREEN MODULES---------------------------
 * */
@Composable
fun ModulesScreen() {
    var showDialog by remember { mutableStateOf(false) }
    var moduleM by remember { mutableStateOf(HomeModel()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayBg)
            .padding(bottom = dimensionResource(id = R.dimen.padding_58))
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = dimensionResource(id = R.dimen.padding_10)),
            contentPadding = PaddingValues(vertical = 5.dp, horizontal = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(listModules) { module ->
                CardModules(model = module) { module ->
                    showDialog = true
                    moduleM = module
                }
            }
        }
    }

    if (showDialog) {
        ShowDialogModuleDescription(
            model = moduleM,
            showDialog = { showDialog = it }
        )
    }
}

private val listModules = listOf(
    HomeModel(
        1,
        "Login",
        R.drawable.img_login,
        "Puedes ingresar sesión por medio de un correo electrónico o por el sensor biométrico, ya sea por huella dactilar o ingresando manualmente la contraseña del dispositivo."
    ),
    HomeModel(
        2,
        "Tareas",
        R.drawable.img_task,
        "Registra tareas por hacer, se puede filtrar por fecha o por prioridad, te muestra la fecha en que se creó la tarea y en la que se modificó."
    ),
    HomeModel(
        3,
        "Books",
        R.drawable.img_books,
        "LLeva el control de los libros que lees, puedes registrar libros que quieras leer, así mismo marcar tus favoritos y marcar qué libros ya has leído."
    ),
    HomeModel(
        4,
        "Rick and Morty",
        R.drawable.img_rick_and_morty,
        "Mira todos los personajes de la caricatura Rick and Morty, podrás revisar las características de cada personaje."
    )
)

@Composable
private fun CardModules(
    model: HomeModel,
    onItemClick: (model: HomeModel) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clickable { onItemClick(model) },
        elevation = dimensionResource(id = R.dimen.elevation_8),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.rounded_12))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Card(
                modifier = Modifier
                    .width(300.dp)
                    .fillMaxHeight(),
                elevation = dimensionResource(id = R.dimen.elevation_4),
                shape = CutCornerShape(
                    topStart = 80.dp,
                    topEnd = 0.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 170.dp
                ),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = PrimaryColor.copy(alpha = 0.3f)),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(model.image),
                        contentDescription = null,
                        modifier = Modifier.padding(end = dimensionResource(id = R.dimen.padding_72)),
                        contentScale = ContentScale.FillHeight
                    )
                }
            }
            Text(
                text = model.title,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = dimensionResource(id = R.dimen.padding_6)),
                style = TextStyle(
                    fontFamily = RubikDistressed,
                    fontWeight = FontWeight.Normal,
                    fontSize = 38.sp,
                    color = Color.Black.copy(alpha = 0.8f)
                )
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun ShowDialogModuleDescription(
    showDialog: (Boolean) -> Unit,
    model: HomeModel,
) {
    Dialog(
        onDismissRequest = { },
        properties = DialogProperties(
            dismissOnClickOutside = false,
            dismissOnBackPress = false,
            usePlatformDefaultWidth = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = { showDialog(false) },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(
                        top = dimensionResource(id = R.dimen.padding_18),
                        end = dimensionResource(id = R.dimen.padding_10)
                    )
                    .clip(CircleShape)
                    .background(GrayBg.copy(alpha = 0.4f))
            ) {
                Icon(
                    imageVector = Icons.Sharp.Close,
                    contentDescription = null,
                    tint = Color.White
                )
            }
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(50.dp, 80.dp, 50.dp, 80.dp),
                elevation = dimensionResource(id = R.dimen.elevation_8),
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.rounded_12))
            ) {
                Image(
                    painter = painterResource(id = model.image),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(0.0f),
                                Color.Black.copy(0.6f),
                                Color.Black.copy(0.8f)
                            )
                        )
                    )
                    .padding(dimensionResource(id = R.dimen.padding_20)),
                contentAlignment = Alignment.Center
                ) {
                Text(
                    text = model.descripction,
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_10)),
                    style = TextStyle(
                        fontFamily = Monserrat,
                        fontWeight = FontWeight.Light,
                        fontSize = 24.sp,
                        color = GrayBg,
                        fontSynthesis = FontSynthesis.All
                    )
                )
            }
        }
    }
}

/**
 * ---------------------------SCREEN COMPONENTS---------------------------
 * */
@Composable
fun ComponentsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayBg)
            .padding(bottom = dimensionResource(id = R.dimen.padding_58))
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = dimensionResource(id = R.dimen.padding_10)),
            contentPadding = PaddingValues(vertical = 5.dp, horizontal = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(listComponents) { model ->
                CardComponents(model = model)
            }
        }
    }
}

private val listComponents = listOf(
    HomeModel(id = 1, title = "TopBar", image = R.drawable.img_tapbar),
    HomeModel(id = 2, title = "Drawer", image = R.drawable.img_drawer),
    HomeModel(id = 3, title = "BottomBar", image = R.drawable.img_bottom_bar),
    HomeModel(id = 4, title = "TapBar", image = R.drawable.img_tapbar),
    HomeModel(id = 5, title = "Dialogs", image = R.drawable.img_dialog),
    HomeModel(id = 6, title = "AlertDialog", image = R.drawable.img_alert_dialog),
    HomeModel(id = 7, title = "SnackBar", image = R.drawable.ic_error_image),
    HomeModel(id = 8, title = "ViewPager", image = R.drawable.img_view_pager),
)

@Composable
private fun CardComponents(model: HomeModel) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = dimensionResource(id = R.dimen.elevation_6),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.rounded_12))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = model.image),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.size(20.dp))
            Text(
                text = model.title,
                modifier = Modifier.padding(end = dimensionResource(id = R.dimen.padding_6)),
                style = TextStyle(
                    fontFamily = RubikDistressed,
                    fontWeight = FontWeight.Normal,
                    fontSize = 38.sp,
                    color = Color.Black.copy(alpha = 0.8f)
                )
            )
        }
    }
}