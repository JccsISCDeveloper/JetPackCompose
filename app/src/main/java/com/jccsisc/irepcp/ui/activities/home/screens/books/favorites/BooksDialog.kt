package com.jccsisc.irepcp.ui.activities.home.screens.books.favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.jccsisc.irepcp.core.constants.Constants
import com.jccsisc.irepcp.ui.activities.home.screens.books.home.domain.model.Mascota
import kotlinx.coroutines.job

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.activities.home.screens.mascotas.tumascota
 * Created by Julio Cesar Camacho Silva on 20/12/22
 */
@Composable
fun BooksDialog(
    showDialog: (Boolean) -> Unit,
    addMascota: (mascota: Mascota) -> Unit
) {
    var animal by remember { mutableStateOf(Constants.NO_VALUE) }
    var raza by remember { mutableStateOf(Constants.NO_VALUE) }
    val focusRequester = FocusRequester()

    AlertDialog(
        onDismissRequest = { },
        confirmButton = {
            TextButton(onClick = {
                showDialog(false)
                val mascota = Mascota(0, animal, raza)
                addMascota(mascota)
            }) {
                Text(text = "Agregar")
            }
        },
        dismissButton = {
            TextButton(onClick = { showDialog(false) }) {
                Text(text = "Cancelar")
            }
        },
        title = { Text(text = "Agregar un libro") },
        text = {
            Column {
                Text(text = "Datos:")
                TextField(
                    value = animal,
                    onValueChange = { animal = it },
                    modifier = Modifier.focusRequester(focusRequester),
                    placeholder = { Text(text = "Titulo") },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color(0xFFB2B2B2),
                        backgroundColor = Color(0xFFFAFAFA),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
                LaunchedEffect(Unit) {
                    coroutineContext.job.invokeOnCompletion {
                        focusRequester.requestFocus()
                    }
                }
                Spacer(modifier = Modifier.size(10.dp))
                TextField(
                    value = raza,
                    onValueChange = { raza = it },
                    placeholder = { Text(text = "Descripción") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color(0xFFB2B2B2),
                        backgroundColor = Color(0xFFFAFAFA),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }
        })
}