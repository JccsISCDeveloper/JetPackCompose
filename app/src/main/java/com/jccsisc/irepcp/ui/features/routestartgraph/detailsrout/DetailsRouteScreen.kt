package com.jccsisc.irepcp.ui.features.routestartgraph.detailsrout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.jccsisc.irepcp.ui.theme.BlackAbi
import com.jccsisc.irepcp.ui.theme.ColorBody
import com.jccsisc.irepcp.ui.theme.GrayBg

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.features.routestart
 * Created by Julio Cesar Camacho Silva on 23/11/22
 */
@Composable
fun DetailsRouteScreen(
    text: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayBg),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = text, style = TextStyle(color = BlackAbi, fontSize = 27.sp, fontWeight = FontWeight.ExtraBold))
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    DetailsRouteScreen("hola")
}