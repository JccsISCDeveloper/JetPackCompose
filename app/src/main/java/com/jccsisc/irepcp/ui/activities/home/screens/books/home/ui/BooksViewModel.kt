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
    val mascotas = repo.getBooksFromRoom()
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
    fun selectedRead(read: Boolean) {
        book = book.copy(read = read)
    }
    fun selectedFavorite(favorite: Boolean) {
        book = book.copy(favorite = favorite)
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