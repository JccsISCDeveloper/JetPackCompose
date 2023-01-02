package com.jccsisc.irepcp.ui.activities.home.screens.books.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.jccsisc.irepcp.IREPApp
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.core.constants.Constants.NO_VALUE
import com.jccsisc.irepcp.ui.activities.home.generalcomponents.ShowLottie
import com.jccsisc.irepcp.ui.activities.home.screens.books.home.domain.model.Book
import com.jccsisc.irepcp.ui.theme.*
import com.jccsisc.irepcp.utils.GlobalData
import com.jccsisc.irepcp.utils.components.dialogs.GenericDialog
import com.jccsisc.irepcp.utils.deleteImage
import com.jccsisc.irepcp.utils.saveImage
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

    val ctx = LocalContext.current

    val books by viewModel.books.collectAsState(initial = emptyList())
    val isLoading by viewModel.isLoading.collectAsState()
    val swipeRefresh = rememberSwipeRefreshState(isRefreshing = isLoading)
    var showDialog by remember { mutableStateOf(false) }
    var book by remember { mutableStateOf(Book()) }

    GlobalData.transparentNavBar(false)

    GlobalData.addBook = { photo, imageUri, imageBitmap, favoriteBook ->
        saveImage(ctx, photo, imageUri, imageBitmap) { uri, fileName ->
            book = Book(0, uri.toString(), 0, favoriteBook, fileName)
            viewModel.addBook(book)
        }
    }

//    SwipeRefresh(
//        state = swipeRefresh,
//        onRefresh = { viewModel.getBooks() }
//    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(GrayBg),
            contentAlignment = Alignment.Center
        ) {
            if (books.isNotEmpty()) {
                ContentBooks(
                    books = books,
                    deleteBook = { deleteBook ->
                        book = deleteBook
                        showDialog = true
                    },
                    onSelectedFavorite = { id, favorite ->
                        viewModel.selectedFavorite(id, favorite)
                    },
                    navigateToDetailBook = navigateToDetailBook
                )
            } else {
                ShowLottie(
                    lottie = R.raw.empty,
                    text = stringResource(id = R.string.add_book),
                    showText = true
                )
            }
        }
//    }

    GenericDialog(
        show = showDialog,
        onDismiss = {},
        image = R.drawable.ic_warning,
        title = stringResource(id = R.string.delete_book),
        message = stringResource(id = R.string.str_are_you_sure_you_want_to_delete, "el libro"),
        btnTitleNegative = stringResource(id = R.string.no),
        btnTitlePositive = stringResource(id = R.string.yes),
        onNegativeClick = { showDialog = false },
        onPositiveClick = {
            viewModel.deleteBook(book)
            deleteImage(filename = book.imageName)
            showDialog = false
        }
    )
}

private const val GRID_COUNT = 2
private const val PADDIN_58 = 58
private const val PADDIN_0 = 0

@Composable
private fun ContentBooks(
    books: List<Book>,
    deleteBook: (book: Book) -> Unit,
    onSelectedFavorite: (id: Int, selected: Boolean) -> Unit,
    navigateToDetailBook: (bookdId: Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(GRID_COUNT),
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = dimensionResource(id = R.dimen.padding_58)),
        content = {
            itemsIndexed(books) { index, book ->
                BookCard(
                    book = book,
                    deleteBook = { deleteBook(book) },
                    onSelectedFavorite = { id, favorite ->
                        onSelectedFavorite(id, favorite)
                        showMessageIsAddedFavorite(favorite)
                    },
                    navigateToDetailBook = navigateToDetailBook
                )
            }
        },
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_12))
    )
}

private fun showMessageIsAddedFavorite(favorite: Boolean) {
    val msg = if (favorite)
        R.string.added_to_your_favorite_books else R.string.removed_from_your_favorite_books
    showToast(IREPApp.INSTANCE.getString(msg))
}

private const val STATUS_TO_READ = 0
private const val STATUS_READING = 1
private const val STATUS_READ = 2

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun BookCard(
    book: Book,
    deleteBook: () -> Unit,
    onSelectedFavorite: (id: Int, selected: Boolean) -> Unit,
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
            .height(dimensionResource(id = R.dimen.height_200)),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.rounded_6)),
        elevation = dimensionResource(id = R.dimen.elevation_4),
        onClick = { navigateToDetailBook(book.id) }
    ) {

        val constraints = setConstraint()

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
                modifier = Modifier.layoutId(IMG_BOOK),
                placeholder = painterResource(id = R.drawable.ic_placeholde_image),
                contentScale = ContentScale.Crop
            )
            StatusText(book.read)
            DeleteIcon(
                deleteMascota = deleteBook,
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.size_28))
                    .clip(RoundedCornerShape(bottomEnd = dimensionResource(id = R.dimen.rounded_8)))
                    .background(GrayBg)
                    .padding(dimensionResource(id = R.dimen.padding_0))
                    .layoutId("btnDelete")
            )
            IconToggleButton(
                checked = favoriteBook,
                onCheckedChange = {
                    favoriteBook = it
                    onSelectedFavorite(book.id, favoriteBook)
                },
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.size_28))
                    .clip(RoundedCornerShape(bottomStart = dimensionResource(id = R.dimen.rounded_8)))
                    .background(GrayBg)
                    .padding(dimensionResource(id = R.dimen.padding_0))
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

private const val IMG_BOOK = "imgBook"
private const val CONTENT_LABEL = "contentLabel"
private const val BTN_DELETE = "btnDelete"
private const val BTN_FAVORITE = "btnFavorite"
private fun setConstraint() = ConstraintSet {
    val imgBook = createRefFor(IMG_BOOK)
    val contentLabel = createRefFor(CONTENT_LABEL)
    val btnDelete = createRefFor(BTN_DELETE)
    val btnFavorite = createRefFor(BTN_FAVORITE)

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

@Composable
private fun StatusText(status: Int) {
    //todo hacer una funcion para el background desbanecido
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
            .height(dimensionResource(id = R.dimen.height_60))
            .layoutId("contentLabel")
    ) {
        Text(
            text = when (status) {
                STATUS_TO_READ -> stringResource(id = R.string.to_read)
                STATUS_READING -> stringResource(id = R.string.reading)
                STATUS_READ -> stringResource(id = R.string.read)
                else -> {
                    NO_VALUE
                }
            },
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(
                    start = dimensionResource(id = R.dimen.padding_6),
                    bottom = dimensionResource(id = R.dimen.padding_6)
                ),
            style = MaterialTheme.typography.subtitle2,
            color = when (status) {
                STATUS_TO_READ -> Color.White
                STATUS_READING -> ColorOrangeTapBar
                STATUS_READ -> Color.Green
                else -> Color.Gray
            }
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
            contentDescription = null,
            tint = ColorRedTapBar
        )
    }
}
