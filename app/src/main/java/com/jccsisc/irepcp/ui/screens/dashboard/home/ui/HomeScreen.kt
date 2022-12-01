package com.jccsisc.irepcp.ui.screens.dashboard.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.ui.theme.GrayBg

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.features.dashboard.ui
 * Created by Julio Cesar Camacho Silva on 16/11/22
 */
@Composable
fun HomeScreen() {
    var showDialogDogs by remember { mutableStateOf(false) }
    if (showDialogDogs) {
        AlertDialog(
            onDismissRequest = { /*TODO*/ },
            confirmButton = {
                TextButton(onClick = {  }) {
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
                            TextButton(onClick = { }) {
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewHome() {
    HomeScreen()
}