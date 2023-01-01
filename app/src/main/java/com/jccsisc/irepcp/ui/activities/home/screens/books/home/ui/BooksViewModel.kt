package com.jccsisc.irepcp.ui.activities.home.screens.books.home.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jccsisc.irepcp.ui.activities.home.screens.books.home.domain.model.Book
import com.jccsisc.irepcp.ui.activities.home.screens.books.home.domain.repository.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.mascotas.mascotashome.ui
 * Created by Julio Cesar Camacho Silva on 01/12/22
 */
@HiltViewModel
class BooksViewModel @Inject constructor(private val repo: BooksRepository) : ViewModel() {
    val books = repo.getBooksFromRoom()
    var book by mutableStateOf(Book())

    fun addBook(book: Book) = viewModelScope.launch(Dispatchers.IO) {
        repo.addBookToRoom(book)
    }

    fun getBook(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        book = repo.getBookFromRoom(id)
    }

    fun updateImage(image: String) {
        book = book.copy(image = image)
    }
    fun selectedRead(read: Int) {
        book = book.copy(read = read)
    }
    fun selectedFavorite(id: Int,favorite: Boolean, saveDB: Boolean = true) = viewModelScope.launch(Dispatchers.IO) {
        if (!saveDB) {
            this.launch(Dispatchers.Main) {
                book = book.copy(favorite = favorite)
            }
        }
        repo.updateFavoriteFromRoom(id, favorite)
    }

    fun updateBook(book: Book) = viewModelScope.launch(Dispatchers.IO) {
        repo.updateBookFromRoom(book)
    }

    fun deleteBook(book: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteBookFromRoom(book)
        }
    }
}