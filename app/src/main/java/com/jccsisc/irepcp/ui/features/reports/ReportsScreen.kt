package com.jccsisc.irepcp.ui.features.reports

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Device
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.features.reports
 * Created by Julio Cesar Camacho Silva on 22/11/22
 */
@Composable
fun ReportsScreen() {
    val constrains = ConstraintSet {
        val greenBox = createRefFor("greenBox")
        val redBox = createRefFor("redBox")
        val blueBox = createRefFor("blueBox")

        constrain(greenBox) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }

        constrain(redBox) {
            top.linkTo(greenBox.top, margin = 50.dp)
            start.linkTo(greenBox.start, margin = 50.dp)
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }

        constrain(blueBox) {
            top.linkTo(redBox.top, margin = 50.dp)
            start.linkTo(redBox.start, margin = 50.dp)
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }
    }

    ConstraintLayout(constrains, modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.background(Color.Green).layoutId("greenBox"))
        Box(modifier = Modifier.background(Color.Red).layoutId("redBox"))
        Box(modifier = Modifier.background(Color.Blue).layoutId("blueBox"))
    }
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.NEXUS_5)
@Composable
private fun DefaultPreview() {
    ReportsScreen()
}