package com.jccsisc.irepcp.ui.activities.home.screens.books.addbook

import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.jccsisc.irepcp.IREPApp
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.ui.activities.home.screens.books.home.domain.model.Book
import com.jccsisc.irepcp.ui.activities.home.screens.books.home.ui.BooksViewModel
import com.jccsisc.irepcp.ui.theme.Gray50
import com.jccsisc.irepcp.ui.theme.PrimaryLight2
import com.jccsisc.irepcp.utils.*
import com.jccsisc.irepcp.utils.GlobalData.askPermissions
import com.jccsisc.irepcp.utils.GlobalData.getBitmapImageCamera
import com.jccsisc.irepcp.utils.GlobalData.showCameraView
import com.jccsisc.irepcp.utils.GlobalData.transparentNavBar
import java.io.File

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.activities.home.screens.mascotas.tumascota
 * Created by Julio Cesar Camacho Silva on 20/12/22
 */
private const val ROUTE_GALLERY = "image/*"
@Composable
fun BooksDialog(
    showDialog: (Boolean) -> Unit,
    booksViewModel: BooksViewModel = hiltViewModel(),
    navigateToCameraView: () -> Unit
) {

    val ctx = LocalContext.current
    var outputDirectory by rememberSaveable { mutableStateOf<File?>(null) }
    var shouldShowCamera by remember { mutableStateOf(false) }

    var imageUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    var favoriteBook by remember { mutableStateOf(false) }

    var enabledView by rememberSaveable { mutableStateOf(true) }

    var imageBitmap by rememberSaveable { mutableStateOf<Bitmap?>(null)}

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageBitmap = null
            imageUri = uri
        }
    getBitmapImageCamera = {
        imageUri = null
        imageBitmap = it
    }

    Dialog(
        onDismissRequest = { },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Card(
            shape = MaterialTheme.shapes.small,
            backgroundColor = Color.White,
            elevation = dimensionResource(id = R.dimen.elevation_8)
        ) {
            Column(
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_16)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.add_book),
                    style = MaterialTheme.typography.h5
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.height_14)))
                Card(
                    modifier = Modifier
                        .clip(RoundedCornerShape(dimensionResource(id = R.dimen.rounded_6)))
                        .width(dimensionResource(id = R.dimen.width_180))
                        .height(dimensionResource(id = R.dimen.height_240))
                        .padding(dimensionResource(id = R.dimen.padding_6)),
                    backgroundColor = Gray50,
                    elevation = if (!enabledView) 6.dp else 0.dp
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        imageUri?.let { uri ->
                            enabledView = false
                            Image(
                                painter = setCoilImagePainter(image = uri.toString(), 200),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                        imageBitmap?.let {
                            enabledView = false
                            Image(bitmap = it.asImageBitmap(), contentDescription = null)
                        }
                        FavoriteControl(
                            enabledView = enabledView,
                            isFavoriteBook = { favoriteBook = it },
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .size(dimensionResource(id = R.dimen.size_40))
                        )
                        if (enabledView) {
                            Text(
                                text = stringResource(id = R.string.add_an_image),
                                modifier = Modifier.align(Alignment.Center),
                                style = MaterialTheme.typography.caption
                            )
                        }
                    }
                }
                ImageControls { launcher.launch(ROUTE_GALLERY) }
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.height_14)))
                DialogControls(
                    enabledView = enabledView,
                    showDialog = showDialog,
                ) {
                    val photo = outputDirectory?.let { getPhotoFile(outputDirectory = it) }
                    if (photo != null) {
                        saveImage(ctx, photo, imageUri, imageBitmap) { uri, fileName ->
                            val book = Book(0, uri.toString(), 0, favoriteBook, fileName)
                            booksViewModel.addBook(book)
                        }
                    }
                }
            }
        }
    }

    showCameraView = { showCameraView ->
        shouldShowCamera = showCameraView
        if (shouldShowCamera) {
            transparentNavBar(true)
            navigateToCameraView()
        }
    }

    if (outputDirectory == null) {
        outputDirectory = getOutputDirectory(IREPApp.INSTANCE)
    }
}

@Composable
fun FavoriteControl(
    enabledView: Boolean,
    isFavoriteBook: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    var favoriteBook by remember { mutableStateOf(false) }

    IconToggleButton(
        checked = favoriteBook,
        onCheckedChange = {
            favoriteBook = it
            isFavoriteBook(favoriteBook)
            showMessageIsAddedFavorite(favoriteBook)
        },
        modifier = modifier,
        enabled = !enabledView
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_fsborite_tgl),
            contentDescription = null,
            tint = if (favoriteBook) PrimaryLight2 else Color.Gray
        )
    }
}

private fun showMessageIsAddedFavorite(favorite: Boolean) {
    val msg = if (favorite)
        R.string.added_to_your_favorite_books else R.string.removed_from_your_favorite_books
    showToast(IREPApp.INSTANCE.getString(msg))
}

@Composable
fun ImageControls(
    launcher: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = dimensionResource(id = R.dimen.padding_6)),
        horizontalArrangement = Arrangement.Center
    ) {
        IconButton(onClick = launcher) {
            Image(
                painter = painterResource(id = R.drawable.ic_gallery_b),
                contentDescription = null,
                modifier = Modifier.size(dimensionResource(id = R.dimen.size_50))
            )
        }
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.width_10)))
        IconButton(onClick = {
            askPermissions()
        }) {
            Image(
                painter = painterResource(id = R.drawable.ic_camera),
                contentDescription = null,
                modifier = Modifier.size(dimensionResource(id = R.dimen.size_50))
            )
        }
    }
}

@Composable
fun DialogControls(
    enabledView: Boolean,
    showDialog: (Boolean) -> Unit,
    onclickSave: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedButton(
            onClick = { showDialog(false) },
            modifier = Modifier.weight(1f)
        ) {
            Text(text = stringResource(id = R.string.cancel))
        }
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.width_20)))
        OutlinedButton(
            onClick = {
                showDialog(false)
                onclickSave()
            },
            modifier = Modifier.weight(1f),
            enabled = !enabledView
        ) {
            Text(text = stringResource(id = R.string.add))
        }
    }
}
