package com.jccsisc.irepcp.ui.screens.dashboard.detailsfavorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jccsisc.irepcp.ui.theme.GrayBg

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.dashboard.detailsfavorites
 * Created by Julio Cesar Camacho Silva on 30/11/22
 */
@Composable
fun DetailsFavoritesScreen(producto: String) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(GrayBg), contentAlignment = Alignment.Center
    ) {
        Text(text = producto, fontSize = 26.sp, fontWeight = FontWeight.ExtraBold)
    }
}