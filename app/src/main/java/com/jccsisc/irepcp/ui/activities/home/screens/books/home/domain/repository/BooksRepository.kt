package com.jccsisc.irepcp.ui.activities.home.screens.books.home.domain.repository

import com.jccsisc.irepcp.ui.activities.home.screens.books.home.domain.model.Book
import kotlinx.coroutines.flow.Flow

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.dashboard.home.domain.repository
 * Created by Julio Cesar Camacho Silva on 01/12/22
 */
typealias Books = List<Book>
interface BooksRepository {
    fun getBooksFromRoom(): Flow<Books>
    fun getBookFromRoom(id: Int): Book
    fun addBookToRoom(book: Book)
    fun updateBookFromRoom(book: Book)
    fun deleteBookFromRoom(book: Book)
}