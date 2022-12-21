package com.jccsisc.irepcp.ui.activities.home.screens.books.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jccsisc.irepcp.ui.activities.home.generalcomponents.GenericTopBar
import com.jccsisc.irepcp.ui.activities.home.screens.books.home.domain.model.Book
import com.jccsisc.irepcp.ui.activities.home.screens.books.home.ui.BooksViewModel
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
            GenericTopBar(navigateBack = navigateBack, "Modificar mascota")
        },
        content = { padding ->
            DetailMascotaContent(
                padding = padding,
                book = viewModel.book,
                updateImage = { animal -> viewModel.updateImage(animal) },
                updateRead = { raza -> viewModel.selectedRead(raza) },
                updateBook = { mascota -> viewModel.updateBook(mascota) },
                navigateBack = navigateBack
            )
        }
    )
}

@Composable
fun DetailMascotaContent(
    padding: PaddingValues,
    book: Book,
    updateImage: (image: String) -> Unit,
    updateRead: (read: Boolean) -> Unit,
    updateBook: (book: Book) -> Unit,
    navigateBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = setCoilImagePainter(image = book.image),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            updateBook(book)
            navigateBack()
        }) {
            Text(text = "Actualizar")
        }
    }
}
