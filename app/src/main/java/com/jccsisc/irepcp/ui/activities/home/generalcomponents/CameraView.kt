package com.jccsisc.irepcp.ui.activities.home.generalcomponents

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.camera.core.*
import androidx.camera.core.ImageCapture.OnImageCapturedCallback
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material.icons.sharp.Lens
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.ui.theme.GrayBg
import com.jccsisc.irepcp.utils.GlobalData
import java.nio.ByteBuffer
import java.util.concurrent.Executor
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.activities.home.generalcomponents
 * Created by Julio Cesar Camacho Silva on 28/12/22
 */
@Composable
fun CameraView(
    executor: Executor,
    onImageCapturedBitmap: (Bitmap) -> Unit,
    onError: (ImageCaptureException) -> Unit,
    navigateBack: () -> Unit
) {
    // 1
    val lensFacing = CameraSelector.LENS_FACING_BACK
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val preview = Preview.Builder().build()
    val previewView = remember { PreviewView(context) }
    val imageCapture: ImageCapture = remember { ImageCapture.Builder().build() }
    val cameraSelector = CameraSelector.Builder()
        .requireLensFacing(lensFacing)
        .build()

    // 2
    LaunchedEffect(lensFacing) {
        val cameraProvider = context.getCameraProvider()
        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(
            lifecycleOwner,
            cameraSelector,
            preview,
            imageCapture
        )

        preview.setSurfaceProvider(previewView.surfaceProvider)
    }

    // 3
    Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier.fillMaxSize()) {
        AndroidView({ previewView }, modifier = Modifier.fillMaxSize())
        IconButton(
            onClick = {
                navigateBack()
                GlobalData.transparentNavBar(false)
            },
            modifier = Modifier
                .padding(
                    top = dimensionResource(id = R.dimen.padding_26),
                    start = dimensionResource(id = R.dimen.padding_12)
                )
                .align(Alignment.TopStart)
                .clip(CircleShape)
                .background(GrayBg.copy(alpha = 0.4f))
        ) {
            Icon(
                imageVector = Icons.Sharp.ArrowBack,
                contentDescription = null,
                tint = Color.White
            )
        }
        IconButton(
            modifier = Modifier.padding(bottom = 20.dp),
            onClick = {
                Log.i("permiso", "ON CLICK")
                takePhoto(
                    imageCapture = imageCapture,
                    executor = executor,
                    onImageCapturedBitmap = {
                        onImageCapturedBitmap(it)
                        GlobalData.transparentNavBar(false)
                    },
                    onError = onError
                )
            },
            content = {
                Icon(
                    imageVector = Icons.Sharp.Lens,
                    contentDescription = "Take picture",
                    tint = Color.White,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(1.dp)
                        .border(1.dp, Color.White, CircleShape)
                )
            }
        )
    }
}

private fun takePhoto(
    imageCapture: ImageCapture,
    executor: Executor,
    onImageCapturedBitmap: (Bitmap) -> Unit,
    onError: (ImageCaptureException) -> Unit
) {
    imageCapture.takePicture(executor, @ExperimentalGetImage object : OnImageCapturedCallback() {
        override fun onCaptureSuccess(image: ImageProxy) {
            //get bitmap from image
            val bitmap = imageProxyToBitmap(image)
            super.onCaptureSuccess(image)
            onImageCapturedBitmap(bitmap)
            image.close()
        }

        override fun onError(exception: ImageCaptureException) {
            onError(exception)
        }
    })
}


/**
 *  convert image proxy to bitmap
 *  @param image
 */
private fun imageProxyToBitmap(image: ImageProxy): Bitmap {
    val planeProxy = image.planes[0]
    val buffer: ByteBuffer = planeProxy.buffer
    val bytes = ByteArray(buffer.remaining())
    buffer.get(bytes)
    return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
}

private suspend fun Context.getCameraProvider(): ProcessCameraProvider =
    suspendCoroutine { continuation ->
        ProcessCameraProvider.getInstance(this).also { cameraProvider ->
            cameraProvider.addListener({
                continuation.resume(cameraProvider.get())
            }, ContextCompat.getMainExecutor(this))
        }
    }