@file:Suppress("UNUSED_EXPRESSION")

package com.jccsisc.irepcp.ui.activities.home.screens.books.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.ui.activities.home.generalcomponents.ImageContainer
import com.jccsisc.irepcp.ui.activities.home.screens.books.home.domain.model.Mascota
import com.jccsisc.irepcp.ui.theme.*

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
            .background(GrayBg),
        contentAlignment = Alignment.Center
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


    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize(),
        content = {
            items(books) { mascota ->
                BookCard(
                    mascota = mascota,
                    deleteMascota = { deleteMascota(mascota) },
                    navigateToDetailMascota = navigateToDetailMascota
                )
            }
        },
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_12))
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BookCard(
    mascota: Mascota,
    deleteMascota: () -> Unit,
    navigateToDetailMascota: (idMascota: Int) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(
                top = dimensionResource(id = R.dimen.padding_8),
                start = dimensionResource(id = R.dimen.padding_8),
                end = dimensionResource(id = R.dimen.padding_8),
                bottom = dimensionResource(id = R.dimen.padding_8)
            )
            .fillMaxWidth()
            .height(170.dp),
        shape = RoundedCornerShape(6.dp),
        elevation = 4.dp,
        onClick = { navigateToDetailMascota(mascota.id) }
    ) {

        val constraints = ConstraintSet {
            val imgBook = createRefFor("imgBook")
            val columnTFls = createRefFor("columnTFls")
            val btnDelete = createRefFor("btnDelete")

            constrain(imgBook) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }
            constrain(columnTFls) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
            }
            constrain(btnDelete) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            }
        }

        ConstraintLayout(
            modifier = Modifier.fillMaxSize(),
            constraintSet = constraints
        ) {
            ImageContainer(
                content = { bookImage(mascota) },
                modifier = Modifier.layoutId("imgBook")
            )
            Text(
                text = mascota.animal,
                modifier = Modifier
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                SecondaryDarkColor.copy(alpha = 0.0f),
                                SecondaryDarkColor.copy(alpha = 0.5f),
                                SecondaryDarkColor.copy(alpha = 0.7f)
                            )
                        )
                    )
                    .padding(dimensionResource(id = R.dimen.padding_6))
                    .layoutId("columnTFls"),
                style = MaterialTheme.typography.caption,
                color = Color.White
            )
            DeleteIcon(
                deleteMascota = deleteMascota,
                modifier = Modifier
                    .size(28.dp)
                    .clip(RoundedCornerShape(bottomStart = 8.dp))
                    .padding(0.dp)
                    .background(GrayBg)
                    .layoutId("btnDelete")
            )
        }
    }
}

@Composable
fun bookImage(
    mascota: Mascota
) {
    Box {
        Image(
            painter = painterResource(id = R.drawable.tiende_tu_cama),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun DeleteIcon(
    deleteMascota: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = deleteMascota,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = "ic delete",
            tint = ColorRedTapBar
        )
    }
}
