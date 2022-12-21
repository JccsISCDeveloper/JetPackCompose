package com.jccsisc.irepcp.ui.activities.home.screens.books.favorites

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.core.constants.Constants
import com.jccsisc.irepcp.ui.activities.home.screens.books.home.domain.model.Book
import com.jccsisc.irepcp.ui.theme.GrayBg
import com.jccsisc.irepcp.ui.theme.PrimaryColor
import com.jccsisc.irepcp.utils.setCoilImagePainter

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.activities.home.screens.mascotas.tumascota
 * Created by Julio Cesar Camacho Silva on 20/12/22
 */
@Composable
fun BooksDialog(
    showDialog: (Boolean) -> Unit,
    onCheckBoxSelected: (selected: Boolean) -> Unit,
    addBook: (book: Book) -> Unit
) {
    var imageBook by remember { mutableStateOf(Constants.NO_VALUE) }
    var favoriteBook by remember { mutableStateOf(false) }

    Dialog(
        onDismissRequest = { },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Card(
            shape = MaterialTheme.shapes.small,
            backgroundColor = Color.White,
            elevation = 8.dp
        ) {
            Column(
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_16)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Agregar libro", style = MaterialTheme.typography.h5)
                Spacer(modifier = Modifier.height(14.dp))
                Box(
                    modifier = Modifier
                        .background(Color.Gray)
                        .size(200.dp)
                ) {
                    Image(
                        painter = setCoilImagePainter(image = imageBook),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
                Checkbox(
                    checked = favoriteBook,
                    onCheckedChange = {
                        favoriteBook = it
                        onCheckBoxSelected(favoriteBook)
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = PrimaryColor,
                        uncheckedColor = Color.Gray,
                        checkmarkColor = GrayBg,
                        disabledColor = Color.Gray
                    )
                )
                Spacer(modifier = Modifier.height(14.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedButton(
                        onClick = { showDialog(false) },
                        modifier = Modifier.width(140.dp)
                    ) {
                        Text(text = "Cancelar")
                    }
                    OutlinedButton(
                        onClick = {
                            showDialog(false)
                            val book = Book(0, imageBook, false, favorite = false)
                            addBook(book)
                        },
                        modifier = Modifier.width(140.dp)
                    ) {
                        Text(text = "Agregar")
                    }
                }
            }
        }
    }
}