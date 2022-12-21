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
import com.jccsisc.irepcp.ui.activities.home.screens.books.home.domain.model.Book
import com.jccsisc.irepcp.ui.theme.*
import com.jccsisc.irepcp.utils.setCoilImagePainter

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
            deleteBook = { book ->
                viewModel.deleteBook(book)
            },
            navigateToDetailBook = navigateToDetailMascota
        )
    }

}

@Composable
fun ContentBooks(
    books: List<Book>,
    deleteBook: (book: Book) -> Unit,
    navigateToDetailBook: (bookdId: Int) -> Unit
) {


    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize(),
        content = {
            items(books) { book ->
                BookCard(
                    book = book,
                    deleteBook = { deleteBook(book) },
                    navigateToDetailBook = navigateToDetailBook
                )
            }
        },
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_12))
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BookCard(
    book: Book,
    deleteBook: () -> Unit,
    navigateToDetailBook: (bookId: Int) -> Unit
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
        onClick = { navigateToDetailBook(book.id) }
    ) {

        val constraints = ConstraintSet {
            val imgBook = createRefFor("imgBook")
            val tfRead = createRefFor("tfRead")
            val btnDelete = createRefFor("btnDelete")

            constrain(imgBook) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }
            constrain(tfRead) {
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
                content = { bookImage(book) },
                modifier = Modifier.layoutId("imgBook")
            )
            Text(
                text = if (book.read) "Lerído" else "Por leer",
                modifier = Modifier
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                PrimaryDarkColor.copy(alpha = 0.0f),
                                PrimaryDarkColor.copy(alpha = 0.5f),
                                PrimaryDarkColor.copy(alpha = 0.7f)
                            )
                        )
                    )
                    .padding(dimensionResource(id = R.dimen.padding_6))
                    .layoutId("tfRead"),
                style = MaterialTheme.typography.caption,
                color = Color.White
            )
            DeleteIcon(
                deleteMascota = deleteBook,
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
    book: Book
) {
    Box {
        Image(
            painter = setCoilImagePainter(image = book.image),
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
