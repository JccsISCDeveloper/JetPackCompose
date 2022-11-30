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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.ui.theme.GrayBg

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.features.dashboard.ui
 * Created by Julio Cesar Camacho Silva on 16/11/22
 */
@Composable
fun HomeScreen() {
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
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Box {
                        Row() {
                            TextButton(onClick = { /*TODO*/ }) {
                                Text(text = "PERROS")
                            }
                            TextButton(onClick = { /*TODO*/ }) {
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