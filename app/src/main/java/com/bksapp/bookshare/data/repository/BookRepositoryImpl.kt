package com.bksapp.bookshare.data.repository

import com.bksapp.bookshare.data.local.entity.Book
import com.bksapp.bookshare.domain.books
import com.bksapp.bookshare.domain.repository.BookRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(): BookRepository {
    override suspend fun getBooks(): List<Book> {
       delay(1500)
        return getAllBooks()
    }

    override suspend fun getBook(id: Int): Book {
        delay(1500)
        return getBookById(id)
    }

    fun getAllBooks() : List<Book> = books
    fun getBookById(id : Int) : Book {
        return books.find { it.id == id }?:Book(0,"No Book",0,"")
    }
}


