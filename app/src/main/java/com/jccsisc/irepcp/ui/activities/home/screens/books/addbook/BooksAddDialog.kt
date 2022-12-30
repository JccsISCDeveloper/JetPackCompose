package com.jccsisc.irepcp.ui.activities.home.screens.books.addbook

import android.Manifest
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.jccsisc.irepcp.IREPApp
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.core.constants.Constants
import com.jccsisc.irepcp.core.constants.Constants.NO_VALUE
import com.jccsisc.irepcp.ui.activities.home.screens.books.home.domain.model.Book
import com.jccsisc.irepcp.ui.theme.Gray50
import com.jccsisc.irepcp.ui.theme.PrimaryLight2
import com.jccsisc.irepcp.utils.*
import com.jccsisc.irepcp.utils.GlobalData.askPermissions
import com.jccsisc.irepcp.utils.GlobalData.showCameraView
import java.io.File

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.activities.home.screens.mascotas.tumascota
 * Created by Julio Cesar Camacho Silva on 20/12/22
 */
@Composable
fun BooksDialog(
    showDialog: (Boolean) -> Unit,
//    onCheckBoxSelected: (selected: Boolean) -> Unit,
    addBook: (book: Book) -> Unit,
    navigateToCameraView: () -> Unit
) {

    val ctx = LocalContext.current
    var outputDirectory by remember { mutableStateOf(File(NO_VALUE)) }
    var shouldShowCamera by remember { mutableStateOf(false) }

    var imageUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    var bitmap = remember { mutableStateOf<Bitmap?>(null) }

    var imageBook by remember { mutableStateOf(Constants.NO_VALUE) }
    var favoriteBook by remember { mutableStateOf(false) }

    var realPath by remember { mutableStateOf(NO_VALUE) }

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri = uri
//            realPath = getRealpathFromUri(uri)
        }

 /*   imageUri?.let {
        if (Build.VERSION.SDK_INT < 28) {
            bitmap.value = MediaStore.Images.Media.getBitmap(IREPApp.INSTANCE.contentResolver, it)
        } else {
            val source = ImageDecoder.createSource(IREPApp.INSTANCE.contentResolver, it)
            bitmap.value = ImageDecoder.decodeBitmap(source)
        }
    }*/

    GlobalData.getUriImageCamera = { imageCamera ->
        imageUri = imageCamera
    }

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
                        .clip(RoundedCornerShape(6.dp))
                        .background(Gray50)
                        .width(180.dp)
                        .height(230.dp)
                ) {
                    imageUri?.let { uri ->
                        Image(
                            painter = setCoilImagePainter(image = uri.toString(), 200),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.Center),
                            contentScale = ContentScale.Crop
                        )
                    }

                    IconToggleButton(
                        checked = favoriteBook,
                        onCheckedChange = {
                            favoriteBook = it
//                            onCheckBoxSelected(favoriteBook)
                        },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .size(40.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_fsborite_tgl),
                            contentDescription = null,
                            tint = if (favoriteBook) PrimaryLight2 else Color.Gray
                        )
                    }
                    Text(
                        text = "Agrega una imagen",
                        modifier = Modifier.align(Alignment.Center),
                        style = MaterialTheme.typography.caption
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimensionResource(id = R.dimen.padding_6)),
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconButton(onClick = { launcher.launch("image/*") }) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_gallery_b),
                            contentDescription = null,
                            modifier = Modifier.size(50.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    IconButton(onClick = {
                        askPermissions()
                    }) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_camera),
                            contentDescription = null,
                            modifier = Modifier.size(50.dp)
                        )
                    }
                }
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

                            val photo = getPhotoFile(outputDirectory = outputDirectory)
                            imageUri?.let {
                                saveImage(ctx, photo, it) { uri, fileName ->
                                    val book = Book(0, uri.toString(), false, favoriteBook, fileName)
                                    addBook(book)
                                }
                            }
                        },
                        modifier = Modifier.width(140.dp)
                    ) {
                        Text(text = "Agregar")
                    }
                }
            }
        }
    }

    showCameraView = { showCameraView ->
        shouldShowCamera = showCameraView
        if (shouldShowCamera) {
            navigateToCameraView()
        }
    }

    outputDirectory = getOutputDirectory(IREPApp.INSTANCE)
}

private fun getPermissionsCamera(): List<String> = if (Build.VERSION.SDK_INT <= 28) {
    listOf(
        Manifest.permission.CAMERA,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
} else {
    listOf(Manifest.permission.CAMERA)
}