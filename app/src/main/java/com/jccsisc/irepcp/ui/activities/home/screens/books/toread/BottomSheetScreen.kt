package com.jccsisc.irepcp.ui.activities.home.screens.books.toread

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.ui.theme.GrayBg
import com.jccsisc.irepcp.ui.theme.PrimaryColor
import kotlinx.coroutines.launch

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.dashboard.eventos
 * Created by Julio Cesar Camacho Silva on 29/11/22
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetScreen() {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val bsScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

    var showDialogDogs by remember { mutableStateOf(false) }
    if (showDialogDogs) {
        AlertDialog(
            onDismissRequest = { /*TODO*/ },
            confirmButton = {
                TextButton(onClick = { }) {
                    Text(text = "Actualizar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialogDogs = false }) {
                    Text(text = "Cancelar")
                }
            },
            title = { Text(text = "Confirmar") },
            text = { Text(text = "¿Está seguro de querer actuaizar la información?") })
    }

    BottomSheetScaffold(
        scaffoldState = bsScaffoldState,
        sheetContent = { BottomSheet() },
        sheetPeekHeight = 0.dp,
        sheetShape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GrayBg)
                .padding(dimensionResource(id = R.dimen.padding_6))
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.padding_6))
                    .clickable { },
                elevation = 6.dp,
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.padding_16))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.dog),
                        contentDescription = "dog",
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = "Cuidados de cachorros en los primeros meses",
                        modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_10)),
                        style = MaterialTheme.typography.h6
                    )
                    Text(
                        text = "En este artículo veremos los cuidados que requieren tus cachorros en los primeros meses de vida.",
                        style = MaterialTheme.typography.subtitle1
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box {
                            Row() {
                                TextButton(onClick = { showDialogDogs = true }) {
                                    Text(text = "PERROS")
                                }
                                TextButton(onClick = {
                                    scope.launch {
                                        if (bsScaffoldState.bottomSheetState.isCollapsed) {
                                            bsScaffoldState.bottomSheetState.expand()
                                        } else {
                                            bsScaffoldState.bottomSheetState.collapse()
                                        }
                                    }
                                }) {
                                    Text(text = "RAZAS")
                                }
                            }
                        }
                        Box {
                            Row() {
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(
                                        imageVector = Icons.Filled.Favorite,
                                        contentDescription = "ic favorites"
                                    )
                                }
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(
                                        imageVector = Icons.Filled.Share,
                                        contentDescription = "ic share"
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewEvents() {
    ToReadScreen()
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun BottomSheet() {
    var swOfertas by remember { mutableStateOf(false) }
    var swInternet by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(PrimaryColor),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Configuración", style = MaterialTheme.typography.subtitle1)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Recibir ofertas especiales", modifier = Modifier.weight(1f))
                Switch(
                    checked = swOfertas,
                    onCheckedChange = { swOfertas = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = PrimaryColor
                    )
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Modo sin internet", modifier = Modifier.weight(1f))
                Switch(
                    checked = swInternet,
                    onCheckedChange = { swInternet = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = PrimaryColor
                    )
                )
            }
            Row(
                modifier = Modifier.padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Condiciones de uso", modifier = Modifier.weight(1f))
                CompositionLocalProvider(
                    LocalMinimumTouchTargetEnforcement provides false
                ) {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowRight,
                            contentDescription = "Condiciones de uso"
                        )
                    }
                }
            }
            Row(
                modifier = Modifier.padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Política de privacidad", modifier = Modifier.weight(1f))
                CompositionLocalProvider(
                    LocalMinimumTouchTargetEnforcement provides false
                ) {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowRight,
                            contentDescription = "Política de privacidad"
                        )
                    }
                }
            }
            Row(
                modifier = Modifier.padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Contrato de licencia", modifier = Modifier.weight(1f))
                CompositionLocalProvider(
                    LocalMinimumTouchTargetEnforcement provides false
                ) {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowRight,
                            contentDescription = "Contrato de licencia"
                        )
                    }
                }
            }
        }
    }
}