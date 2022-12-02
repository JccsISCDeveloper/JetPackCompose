@file:Suppress("UNUSED_EXPRESSION")

package com.jccsisc.irepcp.ui.screens.mascotas.mascotashome.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.core.constants.Constants.NO_VALUE
import com.jccsisc.irepcp.ui.screens.mascotas.mascotashome.domain.model.Mascota
import com.jccsisc.irepcp.ui.theme.GrayBg
import kotlinx.coroutines.job

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.features.dashboard.ui
 * Created by Julio Cesar Camacho Silva on 16/11/22
 */
const val MASCOTA_ID = "mascotaId"

@Composable
fun MascotasScreen(
    viewModel: MascotasViewModel = hiltViewModel(),
    navigateToDetailMascota: (mascotaId: Int) -> Unit
) {

    val mascotas by viewModel.mascotas.collectAsState(initial = emptyList())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayBg), contentAlignment = Alignment.Center
    ) {

        BodyMascotas(
            mascotas = mascotas,
            deleteMascota = { mascota ->
                viewModel.deleteMascota(mascota)
            },
            navigateToDetailMascota = navigateToDetailMascota
        )
        AddMascotaAlertDialog(
            opendDialog = viewModel.openDialog,
            closeDialog = { viewModel.closeDialog() },
            addMascota = { mascota ->
                viewModel.addMascota(mascota)
            }
        )
        Button(onClick = { viewModel.openDialog() }, modifier = Modifier.align(Alignment.TopEnd)) {
            Text(text = "Agregar mascota")
        }
    }

}

@Composable
fun AddMascotaAlertDialog(
    opendDialog: Boolean,
    closeDialog: () -> Unit,
    addMascota: (mascota: Mascota) -> Unit
) {
    if (opendDialog) {
        var animal by remember { mutableStateOf(NO_VALUE) }
        var raza by remember { mutableStateOf(NO_VALUE) }
        val focusRequester = FocusRequester()

        AlertDialog(
            onDismissRequest = { closeDialog() },
            title = { Text(text = "Agregar mascota") },
            text = {
                Column {
                    TextField(
                        value = animal,
                        onValueChange = { animal = it },
                        modifier = Modifier.focusRequester(focusRequester),
                        placeholder = { Text(text = "Animal") })
                    LaunchedEffect(Unit) {
                        coroutineContext.job.invokeOnCompletion {
                            focusRequester.requestFocus()
                        }
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                    TextField(value = raza, onValueChange = { raza = it }, placeholder = { "Raza" })
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    closeDialog()
                    val mascota = Mascota(0, animal, raza)
                    addMascota(mascota)
                }) {
                    Text(text = "Agregar")
                }
            },
            dismissButton = {
                TextButton(onClick = closeDialog) {
                    Text(text = "Cerrar")
                }
            }
        )
    }
}

@Composable
fun BodyMascotas(
    mascotas: List<Mascota>,
    deleteMascota: (mascota: Mascota) -> Unit,
    navigateToDetailMascota: (mascotaId: Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.padding_10))
    ) {
        items(mascotas) { mascota ->
            MascotaCard(
                mascota = mascota,
                deleteMascota = { deleteMascota(mascota) },
                navigateToDetailMascota = navigateToDetailMascota
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MascotaCard(
    mascota: Mascota,
    deleteMascota: () -> Unit,
    navigateToDetailMascota: (idMascota: Int) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(
                top = dimensionResource(id = R.dimen.padding_3),
                start = dimensionResource(id = R.dimen.padding_8),
                end = dimensionResource(id = R.dimen.padding_8),
                bottom = dimensionResource(id = R.dimen.padding_3)
            )
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        elevation = 4.dp,
        onClick = {
            navigateToDetailMascota(mascota.id)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_10)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(mascota.animal)
                Text(mascota.raza)
            }
            Spacer(modifier = Modifier.weight(1f))
            DeleteIcon(
                deleteMascota = deleteMascota
            )
        }
    }
}

@Composable
fun DeleteIcon(deleteMascota: () -> Unit) {
    IconButton(onClick = deleteMascota) {
        Icon(imageVector = Icons.Filled.Delete, contentDescription = "ic delete")
    }
}
