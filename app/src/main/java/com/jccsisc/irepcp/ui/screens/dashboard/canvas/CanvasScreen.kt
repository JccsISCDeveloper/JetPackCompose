package com.jccsisc.irepcp.ui.screens.dashboard.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jccsisc.irepcp.ui.theme.AndroidColor
import com.jccsisc.irepcp.ui.theme.GrayBg

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.dashboard.canvas
 * Created by Julio Cesar Camacho Silva on 25/11/22
 */
@Composable
fun CanvasScreen() {
    BoxWithConstraints(modifier = Modifier
        .fillMaxSize()
        .background(GrayBg)) {
        AndroidLogo(backgroundColor = GrayBg, contentColor = AndroidColor, padding = 30.dp)
    }
}

@Composable
private fun AndroidLogo(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    contentColor: Color,
    padding: Dp = 30.dp
) {
    val eyeOffset = remember { mutableStateOf(0.0f) }

    Canvas(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(padding)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDrag = { change, dragAmount ->
                        change.consumeAllChanges()
                        eyeOffset.value += dragAmount.x * 0.12f
                    },
                    onDragEnd = {
                        eyeOffset.value = 0f
                    }
                )
            }
    ) {
        drawArc(
            startAngle = -180f,
            sweepAngle = 180f,
            useCenter = true,
            color = contentColor,
            size = Size(size.minDimension, size.minDimension),
            topLeft = Offset(0f, size.minDimension * 0.5f)
        )

        drawCircle(
            color = backgroundColor,
            center = Offset(size.minDimension * 0.3f + eyeOffset.value, size.minDimension * 0.8f),
            radius = size.minDimension * 0.04f
        )

        drawCircle(
            color = backgroundColor,
            center = Offset(size.minDimension * 0.7f  + eyeOffset.value, size.minDimension * 0.8f),
            radius = size.minDimension * 0.04f
        )

       rotate(
           degrees = 340f,
           pivot = Offset(size.minDimension * 0.2f, size.minDimension * 0.4f)
       ) {
           drawRoundRect(
               color = contentColor,
               size = Size(size.minDimension * 0.035f, size.minDimension * 0.22f),
               cornerRadius = CornerRadius(size.minDimension * 0.02f),
               topLeft = Offset(size.minDimension * 0.2f + ( eyeOffset.value * 0.3f), size.minDimension * 0.4f)
           )
       }

        rotate(
            degrees = 20f,
            pivot = Offset(size.minDimension * 0.8f, size.minDimension * 0.4f)
        ) {
            drawRoundRect(
                color = contentColor,
                size = Size(size.minDimension * 0.035f, size.minDimension * 0.22f),
                cornerRadius = CornerRadius(size.minDimension * 0.02f),
                topLeft = Offset(size.minDimension * 0.8f + ( eyeOffset.value * 0.3f), size.minDimension * 0.4f)
            )
        }
    }
}