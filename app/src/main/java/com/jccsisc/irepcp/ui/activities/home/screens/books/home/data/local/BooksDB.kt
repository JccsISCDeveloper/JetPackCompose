package com.jccsisc.irepcp.ui.activities.home.screens.books.home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jccsisc.irepcp.core.constants.ConstantsRoom.DB_BOOKS_VERSION
import com.jccsisc.irepcp.ui.activities.home.screens.books.home.domain.model.Book

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.dashboard.home.data.local
 * Created by Julio Cesar Camacho Silva on 01/12/22
 */
@Database(entities = [Book::class], version = DB_BOOKS_VERSION, exportSchema = false)
abstract class BooksDB: RoomDatabase() {
    abstract fun booksDao(): BooksDao

}