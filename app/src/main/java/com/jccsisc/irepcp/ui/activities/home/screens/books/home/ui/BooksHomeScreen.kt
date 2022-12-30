@file:Suppress("UNUSED_EXPRESSION")

package com.jccsisc.irepcp.ui.activities.home.screens.books.home.ui

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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jccsisc.irepcp.IREPApp
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.core.constants.Constants.NO_VALUE
import com.jccsisc.irepcp.ui.activities.home.generalcomponents.ShowLottie
import com.jccsisc.irepcp.ui.activities.home.screens.books.home.domain.model.Book
import com.jccsisc.irepcp.ui.theme.*
import com.jccsisc.irepcp.utils.deleteImage
import com.jccsisc.irepcp.utils.showToast

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.features.dashboard.ui
 * Created by Julio Cesar Camacho Silva on 16/11/22
 */
@Composable
fun BooksHomeScreen(
    viewModel: BooksViewModel = hiltViewModel(),
    navigateToDetailBook: (booId: Int) -> Unit
) {

    val books by viewModel.mascotas.collectAsState(initial = emptyList())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayBg),
        contentAlignment = Alignment.Center
    ) {
        if (books.isNotEmpty()) {

            /* val outputDirectory = getOutputDirectory(IREPApp.INSTANCE)
             Log.i("files", "${outputDirectory.list()}")

             books.forEach {
                 val list = outputDirectory.list()?.filter { file ->
                     file != it.imageName
                 }
                 Log.i("lista", "$list")
                 list?.forEach {
                     deleteImage(filename = it)
                 }
             }*/

            ContentBooks(
                books = books,
                deleteBook = { book ->
                    viewModel.deleteBook(book)
                    deleteImage(filename = book.imageName)
                },
                onCheckBoxSelected = {id, favorite ->
                    viewModel.selectedFavorite(id, favorite)
                },
                navigateToDetailBook = navigateToDetailBook
            )
        } else {
            ShowLottie(
                lottie = R.raw.empty,
                text = "Agrega un libro",
                showText = true
            )
        }
    }

}

@Composable
fun ContentBooks(
    books: List<Book>,
    deleteBook: (book: Book) -> Unit,
    onCheckBoxSelected: (id: Int, selected: Boolean) -> Unit,
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
                    onCheckBoxSelected = {id, favorite ->
                        onCheckBoxSelected(id, favorite)
                        if (favorite) {
                            showToast(
                                IREPApp.INSTANCE.getString(R.string.added_to_your_favorite_books)
                            )
                        } else {
                            showToast(
                                IREPApp.INSTANCE.getString(R.string.removed_from_your_favorite_books)
                            )
                        }
                    },
                    navigateToDetailBook = navigateToDetailBook
                )
            }
        },
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_12))
    )
}

private const val STATUS_TO_READ = 0
private const val STATUS_READING = 1
private const val STATUS_READ = 2
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BookCard(
    book: Book,
    deleteBook: () -> Unit,
    onCheckBoxSelected: (id: Int, selected: Boolean) -> Unit,
    navigateToDetailBook: (bookId: Int) -> Unit
) {
    var favoriteBook by rememberSaveable { mutableStateOf(false) }

    favoriteBook = book.favorite

    Card(
        modifier = Modifier
            .padding(
                top = dimensionResource(id = R.dimen.padding_8),
                start = dimensionResource(id = R.dimen.padding_12),
                end = dimensionResource(id = R.dimen.padding_12),
                bottom = dimensionResource(id = R.dimen.padding_8)
            )
            .fillMaxWidth()
            .height(200.dp),
        shape = RoundedCornerShape(6.dp),
        elevation = 4.dp,
        onClick = { navigateToDetailBook(book.id) }
    ) {

        val constraints = ConstraintSet {
            val imgBook = createRefFor("imgBook")
            val contentLabel = createRefFor("contentLabel")
            val btnDelete = createRefFor("btnDelete")
            val btnFavorite = createRefFor("btnFavorite")

            constrain(imgBook) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }
            constrain(contentLabel) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
            }
            constrain(btnDelete) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
            constrain(btnFavorite) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            }
        }

        ConstraintLayout(
            modifier = Modifier.fillMaxSize(),
            constraintSet = constraints
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(book.image)
                    .crossfade(500)
                    .error(R.drawable.ic_error_image)
                    .build(),
                contentDescription = null,
                modifier = Modifier.layoutId("imgBook"),
                placeholder = painterResource(id = R.drawable.ic_placeholde_image),
                contentScale = ContentScale.Crop
            )
            Box(
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
                    .height(60.dp)
                    .layoutId("contentLabel")
            ) {
                Text(
                    text = when (book.read) {
                        STATUS_TO_READ -> stringResource(id = R.string.to_read)
                        STATUS_READING -> stringResource(id = R.string.reading)
                        STATUS_READ -> stringResource(id = R.string.read)
                        else -> { NO_VALUE }
                    },
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(
                            start = dimensionResource(id = R.dimen.padding_6),
                            bottom = dimensionResource(id = R.dimen.padding_6)
                        ),
                    style = MaterialTheme.typography.subtitle2,
                    color = when (book.read) {
                        STATUS_TO_READ -> Color.White
                        STATUS_READING -> ColorOrangeTapBar
                        STATUS_READ -> Color.Green
                        else -> Color.Gray
                    }
                )
            }
            DeleteIcon(
                deleteMascota = deleteBook,
                modifier = Modifier
                    .size(28.dp)
                    .clip(RoundedCornerShape(bottomEnd = 8.dp))
                    .padding(0.dp)
                    .background(GrayBg)
                    .layoutId("btnDelete")
            )
            IconToggleButton(
                checked = favoriteBook,
                onCheckedChange = {
                    favoriteBook = it
                    onCheckBoxSelected(book.id, favoriteBook)
                },
                modifier = Modifier
                    .size(28.dp)
                    .clip(RoundedCornerShape(bottomStart = 8.dp))
                    .padding(0.dp)
                    .background(GrayBg)
                    .layoutId("btnFavorite")
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_fsborite_tgl),
                    contentDescription = null,
                    tint = if (favoriteBook) PrimaryColor else Color.Gray
                )
            }
        }
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
