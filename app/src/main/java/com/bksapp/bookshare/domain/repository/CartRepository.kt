package com.bksapp.bookshare.domain.repository

import com.bksapp.bookshare.data.local.entity.Book
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun cartItems() : Flow<List<Book>>
    suspend fun addItemToCart(book: Book, quantity: Int)
    suspend fun deleteItemFromCart(bookId: Int)
    suspend fun isExistInCart(bookId: Int) : Boolean
}