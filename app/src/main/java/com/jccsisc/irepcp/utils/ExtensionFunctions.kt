package com.jccsisc.irepcp.utils

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jccsisc.irepcp.IREPApp
import com.jccsisc.irepcp.R
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
 * -------------------- Componentes Composable
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
 * -------------------- Funciones Kotlin Globales
 *****************************************--------------------------------------------------------*/
fun showToast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(IREPApp.INSTANCE.baseContext, msg, duration).show()
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
    val nowDate =  System.currentTimeMillis()
    val lastDate = this

    val seconds = ( nowDate - lastDate ) / 1000
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
            ctx.getString(R.string.str_yesterday, time(lastDate))
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