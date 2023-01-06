package com.jccsisc.irepcp.ui.activities.home.screens.books.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.calculatePan
import androidx.compose.foundation.gestures.calculateZoom
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import com.jccsisc.irepcp.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.jccsisc.irepcp.IREPApp
import com.jccsisc.irepcp.ui.activities.home.generalcomponents.GenericTopBar
import com.jccsisc.irepcp.ui.activities.home.screens.books.home.domain.model.Book
import com.jccsisc.irepcp.ui.activities.home.screens.books.home.ui.BooksViewModel
import com.jccsisc.irepcp.ui.theme.GrayBg
import com.jccsisc.irepcp.ui.theme.PrimaryColor
import com.jccsisc.irepcp.utils.setCoilImagePainter

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.mascotas.detail
 * Created by Julio Cesar Camacho Silva on 01/12/22
 */
@Composable
fun BooksDetailScreen(
    viewModel: BooksViewModel = hiltViewModel(),
    bookId: Int,
    navigateBack: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getBook(bookId)
    }
    Scaffold(
        topBar = {
            GenericTopBar(navigateBack = navigateBack, "Detalle del libro")
        },
        content = { padding ->
            DetailMascotaContent(
                padding = padding,
                book = viewModel.book,
                updateImage = { image -> viewModel.updateImage(image) },
                updateRead = { read -> viewModel.selectedRead(read) },
                onSelectedFavorite = { id, selected ->
                    viewModel.selectedFavorite(
                        id = id,
                        favorite = selected,
                        saveDB = false
                    )
                },
                updateBook = { book -> viewModel.updateBook(book) },
                navigateBack = navigateBack
            )
        }
    )
}

@Composable
private fun DetailMascotaContent(
    padding: PaddingValues,
    book: Book,
    updateImage: (image: String) -> Unit,
    updateRead: (read: Int) -> Unit,
    onSelectedFavorite: (id: Int, selected: Boolean) -> Unit,
    updateBook: (book: Book) -> Unit,
    navigateBack: () -> Unit
) {
    var favoriteBook by rememberSaveable { mutableStateOf(false) }
    var opt by remember { mutableStateOf(0) }

    favoriteBook = book.favorite
    opt = book.read

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        val constraints = setConstraint()

        var scale by remember { mutableStateOf(1f) }
        var offsetX by remember { mutableStateOf(0f) }
        var offsetY by remember { mutableStateOf(0f) }

        ConstraintLayout(
            modifier = Modifier
                .pointerInput(Unit) {
                    forEachGesture {
                        awaitPointerEventScope {
                            awaitFirstDown()
                            do {
                                val event = awaitPointerEvent()
                                scale *= event.calculateZoom()
                                val offset = event.calculatePan()
                                offsetX += offset.x
                                offsetY += offset.y
                            } while (event.changes.any{ it.pressed })
                        }
                    }
                }
                .fillMaxSize(),
            constraintSet = constraints
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.height_540))
                    .layoutId(IMG_BOOK),
                elevation = dimensionResource(id = R.dimen.elevation_6)
            ) {
                Image(
                    painter = setCoilImagePainter(image = book.image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer(
                            scaleX = scale,
                            scaleY = scale,
                            translationX = offsetX,
                            translationY = offsetY
                        ),
                    contentScale = ContentScale.Crop
                )
            }
            IconToggleButton(
                checked = favoriteBook,
                onCheckedChange = {
                    favoriteBook = it
                    onSelectedFavorite(book.id, favoriteBook)
                },
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.size_50))
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.rounded_36)))
                    .background(GrayBg)
                    .padding(top = dimensionResource(id = R.dimen.padding_0))
                    .layoutId(BTN_FAVORITE)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_fsborite_tgl),
                    contentDescription = null,
                    tint = if (favoriteBook) PrimaryColor else Color.Gray
                )
            }
            ContainerRadioButtonOptions(
                option = opt,
                onItemSelected = {
                    opt = it
                    updateRead(opt)
                },
                modifier = Modifier.layoutId(CONTAINER_OPTIONS)
            )
            Button(
                onClick = {
                    updateBook(book)
                    navigateBack()
                },
                modifier = Modifier.layoutId(BTN_SAVE)
            ) {
                Text(text = stringResource(id = R.string.save))
            }
        }
    }
}

private const val IMG_BOOK = "imgBook"
private const val BTN_FAVORITE = "btnFavorite"
private const val CONTAINER_OPTIONS = "containerOptions"
private const val BTN_SAVE = "btnSave"

private fun setConstraint() = ConstraintSet {
    val imgBook = createRefFor(IMG_BOOK)
    val btnFavorite = createRefFor(BTN_FAVORITE)
    val containerOptions = createRefFor(CONTAINER_OPTIONS)
    val btnSave = createRefFor(BTN_SAVE)

    constrain(imgBook) {
        width = Dimension.fillToConstraints
        top.linkTo(parent.top, margin = 20.dp)
        start.linkTo(parent.start, margin = 12.dp)
        end.linkTo(parent.end, margin = 12.dp)
        bottom.linkTo(containerOptions.top, margin = 4.dp)
    }
    constrain(btnFavorite) {
        width = Dimension.fillToConstraints
        top.linkTo(imgBook.top, margin = (-16).dp)
        end.linkTo(imgBook.end, margin = (-8).dp)
    }
    constrain(containerOptions) {
        width = Dimension.fillToConstraints
        start.linkTo(imgBook.start)
        end.linkTo(imgBook.end)
        bottom.linkTo(btnSave.top)
    }
    constrain(btnSave) {
        width = Dimension.fillToConstraints
        start.linkTo(imgBook.start)
        end.linkTo(imgBook.end)
        bottom.linkTo(parent.bottom, margin = 8.dp)
    }
}

private const val STATUS_TO_READ = 0
private const val STATUS_READING = 1
private const val STATUS_READ = 2

@Composable
private fun ContainerRadioButtonOptions(
    option: Int,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = option == STATUS_TO_READ,
                onClick = { onItemSelected(STATUS_TO_READ) })
            Text(text = stringResource(id = R.string.to_read))
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = option == STATUS_READING,
                onClick = { onItemSelected(STATUS_READING) }
            )
            Text(text = stringResource(id = R.string.reading))
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(selected = option == STATUS_READ, onClick = { onItemSelected(STATUS_READ) })
            Text(text = stringResource(id = R.string.read))
        }
    }
}