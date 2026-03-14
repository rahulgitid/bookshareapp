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

    override suspend fun getBooksCategories(): List<String> {
        return getCategories()
    }

    fun getAllBooks() : List<Book> = books
    fun getBookById(id : Int) : Book {
        return books.find { it.id == id }?:
        Book(0,"Atomic Habits","James Clear",399,799,4.8,12543,"https://covers.openlibrary.org/b/id/10523338-L.jpg","Self Help","Avery",320,"2018","English")
    }

    fun getCategories(): List<String>{
        return books.map{ it.category }.distinct()
    }
}


