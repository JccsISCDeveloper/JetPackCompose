package com.jccsisc.irepcp.utils

import android.graphics.Bitmap
import android.net.Uri
import java.io.File

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.utils
 * Created by Julio Cesar Camacho Silva on 15/11/22
 */
object GlobalData {
    lateinit var transparentNavBar: (Boolean) -> Unit
    lateinit var onLoginClick: () -> Unit
    lateinit var askPermissions: () -> Unit
    lateinit var showCameraView: (Boolean) -> Unit
    lateinit var getBitmapImageCamera: (Bitmap) -> Unit
    lateinit var addBook: (photo: File, imageUri: Uri?, imageBitmap: Bitmap?, favoriteBook: Boolean) -> Unit
}