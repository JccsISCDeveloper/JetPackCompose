package com.jccsisc.irepcp.ui.activities.home.screens.books.home.data.local

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import com.jccsisc.irepcp.core.constants.ConstantsRoom.TBL_BOOKS
import com.jccsisc.irepcp.ui.activities.home.screens.books.home.domain.model.Book
import com.jccsisc.irepcp.ui.activities.home.screens.books.home.domain.repository.Books
import kotlinx.coroutines.flow.Flow

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.dashboard.home.data.local
 * Created by Julio Cesar Camacho Silva on 01/12/22
 */
@Dao
interface BooksDao {
    @Query("SELECT * FROM $TBL_BOOKS ORDER BY id ASC")
    fun getBooks(): Flow<Books>

    @Query("SELECT * FROM $TBL_BOOKS WHERE id = :id")
    fun getBook(id: Int): Book

    @Insert(onConflict = IGNORE)
    fun addBook(book: Book)

    @Update
    fun updateBook(book: Book)

    @Query("UPDATE $TBL_BOOKS SET favorite = :favorite WHERE id = :id")
    fun updateFavoriteBook(id: Int, favorite: Boolean)

    @Delete
    fun deleteBook(book: Book)
}