package com.jccsisc.irepcp.ui.activities.home.screens.books.addbook

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.jccsisc.irepcp.IREPApp
import com.jccsisc.irepcp.core.constants.Constants
import com.jccsisc.irepcp.ui.activities.home.generalcomponents.CameraView
import com.jccsisc.irepcp.utils.GlobalData.getUriImageCamera
import com.jccsisc.irepcp.utils.getOutputDirectory
import java.io.File

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.activities.home.screens.books.addbook
 * Created by Julio Cesar Camacho Silva on 29/12/22
 */
@Composable
fun BookCameraScreen(
    navigateBack: () -> Unit
) {

    var outputDirectory by remember { mutableStateOf(File(Constants.NO_VALUE)) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
            CameraView(
                outputDirectory = outputDirectory,
                executor = ContextCompat.getMainExecutor(LocalContext.current),
                onImageCapturedUri = { uri ->
                    Log.i("permiso", "Image captured: $uri")
                    getUriImageCamera(uri)
                    navigateBack()
                },
                onError = { Log.e("permiso", "View error:", it) }
            ) {
                navigateBack()
            }
    }

    outputDirectory = getOutputDirectory(IREPApp.INSTANCE)
}