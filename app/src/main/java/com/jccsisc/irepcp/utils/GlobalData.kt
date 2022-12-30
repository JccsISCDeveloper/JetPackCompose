package com.jccsisc.irepcp.utils

import android.net.Uri

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
    lateinit var getUriImageCamera: (Uri) -> Unit
}