package com.jccsisc.irepcp.ui.activities.home.screens.books.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jccsisc.irepcp.ui.activities.home.generalcomponents.GenericTopBar
import com.jccsisc.irepcp.ui.activities.home.screens.books.home.domain.model.Mascota
import com.jccsisc.irepcp.ui.activities.home.screens.books.home.ui.BooksViewModel

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.mascotas.detail
 * Created by Julio Cesar Camacho Silva on 01/12/22
 */
@Composable
fun BooksDetailScreen(
    viewModel: BooksViewModel = hiltViewModel(),
    mascotaId: Int,
    navigateBack: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getMascota(mascotaId)
    }
    Scaffold(
        topBar = {
            GenericTopBar(navigateBack = navigateBack, "Modificar mascota")
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
