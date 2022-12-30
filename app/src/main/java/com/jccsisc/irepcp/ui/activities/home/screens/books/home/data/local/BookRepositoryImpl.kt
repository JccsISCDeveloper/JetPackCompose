package com.jccsisc.irepcp.ui.activities.home.screens.books.home.data.local

import com.jccsisc.irepcp.ui.activities.home.screens.books.home.domain.model.Book
import com.jccsisc.irepcp.ui.activities.home.screens.books.home.domain.repository.BooksRepository
import com.jccsisc.irepcp.ui.activities.home.screens.books.home.domain.repository.Books
import kotlinx.coroutines.flow.Flow

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.dashboard.home.data.local
 * Created by Julio Cesar Camacho Silva on 01/12/22
 */
class BookRepositoryImpl(private val booksDao: BooksDao) : BooksRepository {

    override fun getBooksFromRoom(): Flow<Books> = booksDao.getBooks()
    override fun getBookFromRoom(id: Int): Book = booksDao.getBook(id)

    override fun addBookToRoom(book: Book) = booksDao.addBook(book)

    override fun updateBookFromRoom(book: Book) = booksDao.updateBook(book)
    override fun updateFavoriteFromRoom(id: Int, faborite: Boolean) =
        booksDao.updateFavoriteBook(id, faborite)

    override fun deleteBookFromRoom(book: Book) = booksDao.deleteBook(book)

}