package com.jccsisc.irepcp.ui.activities.home.screens.books.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.ui.activities.home.generalcomponents.ShowLottie
import com.jccsisc.irepcp.ui.activities.home.screens.books.home.domain.model.Book
import com.jccsisc.irepcp.ui.activities.home.screens.books.home.ui.BooksViewModel
import com.jccsisc.irepcp.ui.activities.home.screens.books.toread.ToReadScreen
import com.jccsisc.irepcp.ui.theme.GrayBg
import com.jccsisc.irepcp.ui.theme.PrimaryColor
import com.jccsisc.irepcp.ui.theme.PrimaryDarkColor
import com.jccsisc.irepcp.ui.theme.PrimaryLight2

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.dashboard.tumascota
 * Created by Julio Cesar Camacho Silva on 29/11/22
 */
@Composable
fun BooksFavoritesScreen(viewModel: BooksViewModel = hiltViewModel()) {
    val books by viewModel.books.collectAsState(initial = emptyList())

    val favoritesBooks = books.filter { it.favorite }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayBg),
        contentAlignment = Alignment.Center
    ) {
        if (favoritesBooks.isNotEmpty()) {
            ContentBooks(books = books.filter { book -> book.favorite })
        } else {
            ShowLottie(
                lottie = R.raw.empty,
                text = stringResource(id = R.string.you_have_not_added_to_favorites_yet),
                showText = true
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = dimensionResource(id = R.dimen.rounded_12),
                        bottomEnd = dimensionResource(id = R.dimen.rounded_12)
                    )
                )
                .background(Color.Black.copy(alpha = 0.4f))
                .height(30.dp)
                .align(Alignment.TopCenter),
            contentAlignment = Alignment.Center
        ) {
            val msg = if (favoritesBooks.size == 1) "Libro" else "Libros"
            Text(
                text = "${favoritesBooks.size} $msg",
                style = MaterialTheme.typography.subtitle1,
                color = GrayBg
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewEvents() {
    ToReadScreen()
}

private const val GRID_COUNT = 2

@Composable
private fun ContentBooks(
    books: List<Book>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(GRID_COUNT),
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = dimensionResource(id = R.dimen.padding_8),
                bottom = dimensionResource(id = R.dimen.padding_58)
            ),
        content = {
            itemsIndexed(books) { index, book ->
                BookCard(book = book)
            }
        },
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_12))
    )
}

@Composable
private fun BookCard(book: Book) {
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
        elevation = dimensionResource(id = R.dimen.elevation_4)
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
            IconToggleButton(
                checked = book.favorite,
                onCheckedChange = { },
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.size_28))
                    .clip(RoundedCornerShape(bottomStart = dimensionResource(id = R.dimen.rounded_8)))
                    .background(GrayBg)
                    .padding(dimensionResource(id = R.dimen.padding_0))
                    .layoutId("btnFavorite"),
                enabled = false
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_fsborite_tgl),
                    contentDescription = null,
                    tint = if (book.favorite) PrimaryColor else Color.Gray
                )
            }
        }
    }
}

private const val IMG_BOOK = "imgBook"
private const val BTN_FAVORITE = "btnFavorite"
private fun setConstraint() = ConstraintSet {
    val imgBook = createRefFor(IMG_BOOK)
    val btnFavorite = createRefFor(BTN_FAVORITE)

    constrain(imgBook) {
        top.linkTo(parent.top)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        bottom.linkTo(parent.bottom)
        width = Dimension.fillToConstraints
        height = Dimension.fillToConstraints
    }
    constrain(btnFavorite) {
        top.linkTo(parent.top)
        end.linkTo(parent.end)
    }
}