package com.jccsisc.irepcp.ui.screens.rickandmorty.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jccsisc.irepcp.ui.theme.PrimaryColor

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.rickandmorty.ui.detail
 * Created by Julio Cesar Camacho Silva on 06/12/22
 */
@Composable
fun DetailRickAndMortyScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(PrimaryColor)) {
        Text(text = "Detalles rick and morty")
    }
}