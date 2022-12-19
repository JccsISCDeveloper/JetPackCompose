package com.jccsisc.irepcp.ui.activities.home.generalcomponents

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.airbnb.lottie.compose.*

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.activities.home.generalcomponents
 * Created by Julio Cesar Camacho Silva on 19/12/22
 */
@Composable
fun ShowLottie(
    @RawRes lottie: Int,
    iterations: Int = LottieConstants.IterateForever,
    text: String = "",
    showText: Boolean = false
) {
    val comositeResult: LottieCompositionResult = rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(lottie)
    )

    val progressAnimation by animateLottieCompositionAsState(
        composition = comositeResult.value,
        isPlaying = true,
        iterations = iterations,
        speed = 1.0f
    )

    Box {
        LottieAnimation(composition = comositeResult.value, progress = progressAnimation)
        if (showText) {
            Text(text = text, modifier = Modifier.align(Alignment.BottomCenter), color = Color.Gray)
        }
    }
}