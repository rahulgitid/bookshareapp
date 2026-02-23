package com.bksapp.bookshare.domain.repository

import com.bksapp.bookshare.data.local.entity.Book

interface BookRepository {
    suspend fun getBooks() : List<Book>
}