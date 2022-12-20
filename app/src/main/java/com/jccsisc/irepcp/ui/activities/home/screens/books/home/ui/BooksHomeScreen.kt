@file:Suppress("UNUSED_EXPRESSION")

package com.jccsisc.irepcp.ui.activities.home.screens.books.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.ui.activities.home.screens.books.home.domain.model.Mascota
import com.jccsisc.irepcp.ui.theme.GrayBg

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.features.dashboard.ui
 * Created by Julio Cesar Camacho Silva on 16/11/22
 */
@Composable
fun BooksHomeScreen(
    viewModel: BooksViewModel = hiltViewModel(),
    navigateToDetailMascota: (mascotaId: Int) -> Unit
) {

    val books by viewModel.mascotas.collectAsState(initial = emptyList())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayBg), contentAlignment = Alignment.Center
    ) {

        ContentBooks(
            books = books,
            deleteMascota = { mascota ->
                viewModel.deleteMascota(mascota)
            },
            navigateToDetailMascota = navigateToDetailMascota
        )
    }

}

@Composable
fun ContentBooks(
    books: List<Mascota>,
    deleteMascota: (mascota: Mascota) -> Unit,
    navigateToDetailMascota: (mascotaId: Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.padding_10))
    ) {
        items(books) { mascota ->
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
            Column(modifier = Modifier.weight(1f)) {
                Text(mascota.animal)
                Text(mascota.raza)
            }
//            Spacer(modifier = Modifier.weight(1f))
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
