package com.jccsisc.irepcp.ui.screens.dashboard.tumascota

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jccsisc.irepcp.ui.theme.GrayBg

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.dashboard.tumascota
 * Created by Julio Cesar Camacho Silva on 29/11/22
 */
@Composable
fun TuMascotaScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(GrayBg), contentAlignment = Alignment.Center) {
        Text(text = "Tu mascota")
    }
}