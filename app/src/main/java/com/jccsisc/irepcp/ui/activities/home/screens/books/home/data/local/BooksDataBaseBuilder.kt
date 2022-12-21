package com.jccsisc.irepcp.ui.activities.home.screens.books.home.data.local

import android.database.sqlite.SQLiteException
import android.util.Log
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.activities.home.screens.books.home.data.local
 * Created by Julio Cesar Camacho Silva on 21/12/22
 */
object BooksDataBaseBuilder {

    val BOOKS_MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            try {


            } catch (sqlEx: SQLiteException) {
                sqlEx.message?.let { Log.e("ERROR_SQL ", it) }
            }
        }
    }
}