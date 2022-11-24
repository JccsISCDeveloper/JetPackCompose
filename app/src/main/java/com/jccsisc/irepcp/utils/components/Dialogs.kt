package com.jccsisc.irepcp.utils.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.utils.components
 * Created by Julio Cesar Camacho Silva on 15/11/22
 */
@Composable
fun MySimpleCustomDialog(show: Boolean, onDismiss: () -> Unit) {
    if (show) {
        Dialog(
            onDismissRequest = { onDismiss() },
            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            //Este nos permite meter cualquier diseño
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
               CircularProgressIndicator()
            }
        }
    }
}