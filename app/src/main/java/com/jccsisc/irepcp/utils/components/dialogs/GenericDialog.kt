package com.jccsisc.irepcp.utils.components.dialogs

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.core.constants.Constants.NO_VALUE

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.utils.components.dialogs
 * Created by Julio Cesar Camacho Silva on 12/12/22
 */
@Composable
fun GenericDialog(
    show: Boolean = false,
    onDismiss: () -> Unit,
    @DrawableRes image: Int? = R.drawable.ic_info_circle,
    title: String = NO_VALUE,
    message: String = NO_VALUE,
    btnTitleNegative: String = NO_VALUE,
    btnTitlePositive: String = NO_VALUE,
    onNegativeClick: () -> Unit,
    onPositiveClick: () -> Unit
) {
    if (show) {
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Card(
                shape = MaterialTheme.shapes.small,
                backgroundColor = Color.White,
                elevation = 8.dp
            ) {
                Column(
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_16)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = image!!),
                        contentDescription = NO_VALUE,
                        modifier = Modifier.size(50.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = title, style = MaterialTheme.typography.h5)
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = message,
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        OutlinedButton(
                            onClick = { onNegativeClick() },
                            modifier = Modifier.width(140.dp)
                        ) {
                            Text(text = btnTitleNegative)
                        }
                        OutlinedButton(
                            onClick = { onPositiveClick() },
                            modifier = Modifier.width(140.dp)
                        ) {
                            Text(text = btnTitlePositive)
                        }
                    }
                }
            }
        }
    }
}