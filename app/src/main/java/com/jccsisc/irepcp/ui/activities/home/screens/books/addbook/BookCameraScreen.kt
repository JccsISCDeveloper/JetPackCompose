package com.jccsisc.irepcp.ui.activities.home.screens.books.addbook

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.jccsisc.irepcp.ui.activities.home.generalcomponents.CameraView
import com.jccsisc.irepcp.utils.GlobalData.getBitmapImageCamera
import com.jccsisc.irepcp.utils.showToast

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.activities.home.screens.books.addbook
 * Created by Julio Cesar Camacho Silva on 29/12/22
 */
@Composable
fun BookCameraScreen(
    navigateBack: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
            CameraView(
                executor = ContextCompat.getMainExecutor(LocalContext.current),
                onImageCapturedBitmap = {
                    getBitmapImageCamera(it)
                    navigateBack()
                },
                onError = {
                    Log.e("permiso", "View error:", it)
                    showToast("No se pudo capturar la imagen ERROR: $it")
                }
            ) {
                navigateBack()
            }
    }
}