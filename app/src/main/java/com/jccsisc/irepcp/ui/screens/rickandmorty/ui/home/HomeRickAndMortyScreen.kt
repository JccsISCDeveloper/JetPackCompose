package com.jccsisc.irepcp.ui.screens.rickandmorty.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jccsisc.irepcp.ui.theme.PrimaryColor

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.rickandmorty.ui
 * Created by Julio Cesar Camacho Silva on 06/12/22
 * url: https://rickandmortyapi.com
 */
@Composable
fun HomeRickAndMortyScreen(navigateToDetail: (Int) -> Unit) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(PrimaryColor)) {
        Text(text = "Lista de personajes")
        Button(onClick = {navigateToDetail(1)}) {
            Text(text = "Ir a detalles")
        }
    }
}