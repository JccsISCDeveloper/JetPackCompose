package com.jccsisc.irepcp.ui.screens.mascotas.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jccsisc.irepcp.ui.screens.mascotas.mascotashome.domain.model.Mascota
import com.jccsisc.irepcp.ui.screens.mascotas.mascotashome.ui.MascotasViewModel

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.mascotas.detail
 * Created by Julio Cesar Camacho Silva on 01/12/22
 */
@Composable
fun DetailMascotaScreen(
    viewModel: MascotasViewModel = hiltViewModel(),
    mascotaId: Int,
    navigateBack: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getMascota(mascotaId)
    }
    Scaffold(
        topBar = {
            MascotaTopBar(navigateBack = navigateBack)
        },
        content = { padding ->
            DetailMascotaContent(
                padding = padding,
                mascota = viewModel.mascota,
                updateAnimal = { animal -> viewModel.updateAnimal(animal) },
                updateRaza = { raza -> viewModel.updateRaza(raza) },
                updateMascota = { mascota -> viewModel.updateMascota(mascota) },
                navigateBack = navigateBack
            )
        }
    )
}

@Composable
fun MascotaTopBar(navigateBack: () -> Unit) {
    TopAppBar(
        title = { Text(text = "Modificar mascota") },
        navigationIcon = {
            IconButton(onClick = navigateBack) {
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "ic arrow back")
            }
        }
    )
}

@Composable
fun DetailMascotaContent(
    padding: PaddingValues,
    mascota: Mascota,
    updateAnimal: (animal: String) -> Unit,
    updateRaza: (raza: String) -> Unit,
    updateMascota: (mascota: Mascota) -> Unit,
    navigateBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = mascota.animal,
            onValueChange = { updateAnimal(it) },
            placeholder = { Text(text = "Animal") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = mascota.raza,
            onValueChange = { updateRaza(it) },
            placeholder = { Text(text = "Raza") }
        )
        Button(onClick = {
            updateMascota(mascota)
            navigateBack()
        }) {
            Text(text = "Actualizar")
        }
    }
}
