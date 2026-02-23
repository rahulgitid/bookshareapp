package com.bksapp.bookshare.data.repository

import com.bksapp.bookshare.data.local.entity.Book
import com.bksapp.bookshare.domain.repository.BookRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(): BookRepository {
    override suspend fun getBooks(): List<Book> {
       delay(1500)
        return getAllBooks()
    }
}


fun getAllBooks() : List<Book>{
    return listOf(
        Book(1,"EkiGai",100,""),
        Book(2,"How to talk",250,""),
        Book(3,"How get best",180,""),
        Book(4,"Learn Android by Steps",599,""),
        Book(5,"Get More",500,""),
        Book(6,"EkiGai",100,""),
        Book(7,"How to talk",250,""),
        Book(8,"How get best",180,""),
        Book(9,"Learn Android by Steps",599,""),
        Book(10,"Get More",500,""),
    )
}