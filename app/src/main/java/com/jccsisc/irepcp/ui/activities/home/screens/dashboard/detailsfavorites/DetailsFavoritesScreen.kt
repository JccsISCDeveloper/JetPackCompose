package com.jccsisc.irepcp.ui.activities.home.screens.dashboard.detailsfavorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jccsisc.irepcp.ui.theme.PrimaryLight2

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.dashboard.detailsfavorites
 * Created by Julio Cesar Camacho Silva on 30/11/22
 */
@Composable
fun DetailsFavoritesScreen(producto: String) {
    Box(
        Modifier
            .fillMaxSize()
            .background(PrimaryLight2),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "DETALLES FAVORITOS")
    }
}