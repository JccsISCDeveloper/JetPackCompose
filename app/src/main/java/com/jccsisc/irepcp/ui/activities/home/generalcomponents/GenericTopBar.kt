package com.jccsisc.irepcp.ui.activities.home.generalcomponents

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import com.jccsisc.irepcp.ui.theme.PrimaryDarkColor

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.generalcomponents
 * Created by Julio Cesar Camacho Silva on 05/12/22
 */
@Composable
fun GenericTopBar(
    navigateBack: () -> Unit,
    title: String,
    showClick: Boolean = false,
    onClick: (() -> Unit?)? = null,
    icon: ImageVector = Icons.Default.Info,
    backgroundColor: Color = PrimaryDarkColor
) {
    TopAppBar(
        title = { Text(text = title, textAlign = TextAlign.Center) },
        navigationIcon = {
            IconButton(onClick = navigateBack) {
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "ic arrow back")
            }
        },
        backgroundColor = backgroundColor,
        actions = {
            if (showClick) {
                IconButton(onClick = {
                    onClick?.let { callBack ->
                        callBack()
                    }
                }) {
                    Icon(imageVector = icon, contentDescription = "ic click")
                }
            }
        }
    )
}