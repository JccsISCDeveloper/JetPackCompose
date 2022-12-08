package com.jccsisc.irepcp.ui.screens.rickandmorty.ui.detail

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.jccsisc.irepcp.ui.theme.PrimaryColor

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.rickandmorty.ui.detail
 * Created by Julio Cesar Camacho Silva on 06/12/22
 */
@Composable
fun DetailRickAndMortyScreen(viewModel: DetailRickAndMortyViewModel = hiltViewModel()) {

    val state = viewModel.state

    Box(modifier = Modifier
        .fillMaxSize()
        .background(PrimaryColor)) {
        Text(text = "Detalles rick and morty")
        state.character?.let { Log.d("name", it.name) }
    }
}