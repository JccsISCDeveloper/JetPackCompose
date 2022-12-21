package com.jccsisc.irepcp.ui.activities.home.generalcomponents

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.activities.home.generalcomponents
 * Created by Julio Cesar Camacho Silva on 20/12/22
 */
@Composable
fun ImageContainer(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Surface(modifier = modifier.aspectRatio(1f), RoundedCornerShape(4.dp)) {
        content()
    }
}