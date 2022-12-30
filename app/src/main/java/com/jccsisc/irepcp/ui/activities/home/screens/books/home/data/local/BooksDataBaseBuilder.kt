package com.jccsisc.irepcp.ui.activities.home.screens.books.home.data.local

import android.database.sqlite.SQLiteException
import android.util.Log
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.jccsisc.irepcp.core.constants.ConstantsRoom.TBL_BOOKS

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.activities.home.screens.books.home.data.local
 * Created by Julio Cesar Camacho Silva on 21/12/22
 */
object BooksDataBaseBuilder {

    val BOOKS_MIGRATION_2_3 = object : Migration(2, 3) {
        override fun migrate(database: SupportSQLiteDatabase) {
            try {
                /**
                 * Creamos una tabla temporal
                 * */
                database.execSQL(
                    "CREATE TABLE books_temporal (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                            "image TEXT NOT NULL, " +
                            "read INTEGER NOT NULL, " +
                            "favorite INTEGER NOT NULL, " +
                            "image_name TEXT NOT NULL DEFAULT ''" +
                            ")"
                )

                /**
                 * Copiamos los datos de TBL_BOOKS a books_temporal
                 * */
                database.execSQL(
                    "INSERT INTO books_temporal" +
                            "(id, image, read, favorite) " +
                            "SELECT id, image, read, favorite FROM $TBL_BOOKS"
                )

                /**
                 * Eliminamos la tabla TBL_BOOKS
                 * */
                database.execSQL("DROP TABLE $TBL_BOOKS")

                /**
                 * Renombramos nuestra tabla temporal
                 * */
                database.execSQL("ALTER TABLE books_temporal RENAME TO $TBL_BOOKS")

            } catch (sqlEx: SQLiteException) {
                sqlEx.message?.let { Log.e("ERROR_SQL ", it) }
            }
        }
    }
}