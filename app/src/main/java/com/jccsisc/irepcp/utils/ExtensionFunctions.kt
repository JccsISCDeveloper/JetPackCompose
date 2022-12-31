package com.jccsisc.irepcp.utils

import android.app.Activity
import android.app.Application
import android.content.Context
import android.graphics.*
import android.media.Image
import android.net.Uri
import android.util.Log
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jccsisc.irepcp.IREPApp
import com.jccsisc.irepcp.R
import java.io.ByteArrayOutputStream
import java.io.File
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.utils
 * Created by Julio Cesar Camacho Silva on 14/11/22
 * En este archivo encontraremos las xtension functions y algunos componentes Composable
 */


/**
 * -------------------- Componentes y funciones Composable
 *****************************************--------------------------------------------------------*/
@Composable
fun SpacerApp(size: Int) {
    Spacer(modifier = Modifier.size(size.dp))
}

/**
 * Manipular el color y texto de la barra de tareas
 * */
@Composable
fun SetNavbarColor(color: Color, useDarkIcons: Boolean = true) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(color = color, darkIcons = useDarkIcons)
    }
}

/**
 * Leemos una imagen con Coil
 * */
@Composable //todo crear un data class de tipo generico
fun setCoilImagePainter(image: String?, duration: Int = 500): AsyncImagePainter =
    rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(image)
            .error(R.drawable.ic_error_image)
            .placeholder(R.drawable.ic_placeholde_image)
            .size(Size.ORIGINAL)
            .crossfade(duration)
            .build()
    )


/**
 * -------------------- Funciones Kotlin Globales
 *****************************************--------------------------------------------------------*/
fun showToast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(IREPApp.INSTANCE.baseContext, msg, duration).show()
}

fun setColor(context: Context, color: Int) = run {
    ContextCompat.getColor(context, color)
}

fun getOutputDirectory(ctx: Application): File {
    val mediaDir = ctx.externalMediaDirs.firstOrNull()?.let {
        File(it, ctx.resources.getString(R.string.app_name)).apply { mkdirs() }
    }

    return if (mediaDir != null && mediaDir.exists()) mediaDir else ctx.filesDir
}

fun getPhotoFile(
    filenameFormat: String = "yyyy-MM-dd-HH-mm-ss-SSS",
    outputDirectory: File,
): File =
    File(
        outputDirectory,
        SimpleDateFormat(filenameFormat, Locale.US).format(System.currentTimeMillis()) + ".jpg"
    )

fun saveImage(
    context: Context,
    file: File,
    uri: Uri?,
    bitmap: Bitmap?,
    uriLmb: (Uri, String) -> Unit,
) {
    if (uri != null) {
        val bytes = context.contentResolver.openInputStream(uri)?.readBytes()!!
        file.writeBytes(bytes)
    } else if (bitmap != null) {
        file.writeBitmap(bitmap = bitmap)
    }
    if (file.exists()) {
        uriLmb(Uri.fromFile(file), file.name)
    }
}

private fun File.writeBitmap(
    bitmap: Bitmap,
    format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
    quality: Int = 85
) {
    outputStream().use { out ->
        bitmap.compress(format, quality, out)
        out.flush()
    }
}

fun Bitmap.getImageUri(): Uri? {
    val bytes = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
    val paht = getOutputDirectory(IREPApp.INSTANCE)
    return Uri.parse(paht.absolutePath)
}

fun deleteImage(
    outputDirectory: File = getOutputDirectory(IREPApp.INSTANCE),
    filename: String
) {
    val file = File(outputDirectory, filename)
    if (file.exists()) {
        file.delete()
        Log.i("file", " $file eliminado...")
    }
}

/**
 * -------------------- Extesion functions
 *****************************************--------------------------------------------------------*/

/**
 * Devuelve la fecha de System.currentTimeMillis() = 9/12/2022 13:44
 * */
fun Long.timeMillisToFormatDate(): String = DateFormat.getInstance().format(Date(this))

/**
 * Devuelve el tiempo que ha transcurrido
 * desde la fecha de creacion.
 * Result:
 * 1.- Hoy, 12:04
 * 2.- Ayer a las 14:22
 * 3.- Miercoles
 * 4.- 07 Diciembre
 * */
fun Long.lastModifiedTime(): String {
    val ctx: Context = IREPApp.INSTANCE
    val nowDate = System.currentTimeMillis()
    val lastDate = this

    val seconds = (nowDate - lastDate) / 1000
    val minutes = seconds / 60
    val hours = minutes / 60
    val days = hours / 24

    fun time(date: Long): String {
        val sdf = SimpleDateFormat("HH:mm a", Locale.forLanguageTag("es-MX"))
        return sdf.format(date)
    }

    fun day(date: Long): String {
        val sdf = SimpleDateFormat("EEEE dd", Locale.forLanguageTag("es-MX"))
        return sdf.format(date)
    }

    fun isToday(lastDate: Long, date: Long): String {
        val sdf = SimpleDateFormat("u", Locale.forLanguageTag("ex-MX"))
        val today = sdf.format(lastDate)
        val yesterday = sdf.format(nowDate)

        return if (today == yesterday) {
            ctx.getString(R.string.str_today, time(lastDate))
        } else {
            ctx.getString(R.string.str_yesterday, time(date))
        }
    }

    fun dayMonth(date: Long): String {
        val sdf = SimpleDateFormat("dd MMMM", Locale.forLanguageTag("es-MX"))
        return sdf.format(date)
    }

    return if (days > 1) {
        if (days == 2L) {
            day(lastDate)
        } else {
            dayMonth(lastDate)
        }
    } else if (days == 1L) {
        ctx.getString(R.string.str_yesterday_at, time(lastDate))
    } else {
        isToday(lastDate, nowDate)
    }
}


/**
 * Cambia de color el navBar De un Acitivity**
 * Pasar parametro:
 * 1.-Color blanco con texto negro: Color.WHITE //el de android
 * 2.-Color transparente texto blanco: Color.TRANSPARENT //el de android
 * 3.-Cualquier otro color: R.color.MyColor //muestra la barra de dicho color y el texto blanco
 * 4.-Color del texto: 0 = White, 1 = Black
 * */
/**
 * Color del texto en la barra de notificaciones
 * */
const val WHITE_COLOR_TEXT = 0
const val BLACK_COLOR_TEXT = 1
const val BLACK_TEXT_COLOR_NAV_BAR = SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
const val WHITE_TEXT_COLOR_NAV_BAR = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
fun Activity.setColorNavBar(
    colorStatusBar: Int,
    fullScreen: Boolean = false,
    colorTextWhiteBlack: Int = 0
) {
    this.apply {
        window.apply {
            when (colorStatusBar) {
                android.graphics.Color.WHITE -> {
                    statusBarColor = colorStatusBar
                    decorView.systemUiVisibility = SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }
                android.graphics.Color.BLACK -> {
                    statusBarColor = colorStatusBar
                    decorView.systemUiVisibility = SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }
                android.graphics.Color.BLUE -> {
                    statusBarColor = colorStatusBar
                    decorView.systemUiVisibility = SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }
                android.graphics.Color.RED -> {
                    statusBarColor = colorStatusBar
                    decorView.systemUiVisibility = SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }
                android.graphics.Color.GREEN -> {
                    statusBarColor = colorStatusBar
                    decorView.systemUiVisibility = SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }
                android.graphics.Color.GRAY -> {
                    statusBarColor = colorStatusBar
                    decorView.systemUiVisibility = SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }
                android.graphics.Color.TRANSPARENT -> {
                    clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                    addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                    decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    statusBarColor = colorStatusBar
                }
                else -> {
                    try {
                        if (fullScreen) {
                            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            statusBarColor = setColor(context, colorStatusBar)
                        } else {
                            when (colorTextWhiteBlack) {
                                WHITE_COLOR_TEXT -> {
                                    //texto blanco
                                    clearFlags(FLAG_FULLSCREEN)
                                    addFlags(FLAG_FORCE_NOT_FULLSCREEN)
                                    statusBarColor = setColor(context, colorStatusBar)
                                    decorView.systemUiVisibility = WHITE_TEXT_COLOR_NAV_BAR
                                }
                                BLACK_COLOR_TEXT -> {
                                    //texto negro
                                    clearFlags(FLAG_FULLSCREEN)
                                    addFlags(FLAG_FORCE_NOT_FULLSCREEN)
                                    statusBarColor = setColor(context, colorStatusBar)
                                    decorView.systemUiVisibility = BLACK_TEXT_COLOR_NAV_BAR
                                }
                            }
                        }
                    } catch (e: Exception) {
                        e
                        Log.e(
                            "error",
                            "El color es nativo, no está mapeado a la lista de colores nativos, favor de agregar, de lo contrario se espera un color en Hexadecimal"
                        )
                    }
                }
            }
        }
    }
}