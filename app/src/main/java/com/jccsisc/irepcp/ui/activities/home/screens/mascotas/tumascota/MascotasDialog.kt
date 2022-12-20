package com.jccsisc.irepcp.ui.activities.home.screens.mascotas.tumascota

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.activities.home.screens.mascotas.tumascota
 * Created by Julio Cesar Camacho Silva on 20/12/22
 */
@Composable
fun MascotasDialog(showDialog: (Boolean) -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var aceptar by remember { mutableStateOf(false) }
    AlertDialog(
        onDismissRequest = {},
        confirmButton = {
            TextButton(onClick = {}) {
                Text(text = "Solicitar")
            }
        },
        dismissButton = {
            TextButton(onClick = { showDialog(false) }) {
                Text(text = "Cancelar")
            }
        },
        title = { Text(text = "Recompensas") },
        text = {
            Column {
                Text(text = "Registre sus datos")
                TextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    placeholder = { Text(text = "Nombre") },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color(0xFFB2B2B2),
                        backgroundColor = Color(0xFFFAFAFA),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
                Spacer(modifier = Modifier.size(10.dp))
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text(text = "Correo") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color(0xFFB2B2B2),
                        backgroundColor = Color(0xFFFAFAFA),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = aceptar, onCheckedChange = { aceptar = it })
                    Text(text = "Acepto los términos y condiciones")
                }
            }
        })
}