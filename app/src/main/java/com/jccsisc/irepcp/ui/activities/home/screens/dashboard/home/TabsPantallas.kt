package com.jccsisc.irepcp.ui.activities.home.screens.dashboard.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jccsisc.irepcp.ui.theme.GrayBg

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.dashboard.detailsfavorites
 * Created by Julio Cesar Camacho Silva on 02/12/22
 */
@Composable
fun DescripcionScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
        .background(GrayBg)
    ) {
        Text(text = "La X fruta es muy importante para la salud ....")
    }
}

@Composable
fun BeneficiosScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
        .background(GrayBg)
    ) {
        Text(text = "Los beneficios de ingerir esta fruta shaalala....")
    }
}

@Composable
fun RecetasScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
        .background(GrayBg)
    ) {
        Text(text = "Puede preparar deliiciosos licuados con ella para lalalshaaaala...")
    }
}