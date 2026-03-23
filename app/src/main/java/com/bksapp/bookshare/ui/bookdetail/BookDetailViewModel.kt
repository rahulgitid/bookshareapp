package com.bksapp.bookshare.ui.bookdetail

import android.util.Log
import androidx.compose.runtime.MutableIntState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bksapp.bookshare.data.local.entity.Book
import com.bksapp.bookshare.data.repository.NetworkStatus
import com.bksapp.bookshare.domain.cartItems
import com.bksapp.bookshare.domain.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    private val bookRepo : BookRepository
): ViewModel() {

    private val _bookState = MutableStateFlow<NetworkStatus<Book>>(NetworkStatus.Idle)
           val bookState = _bookState.asStateFlow()


    private val _cartUpdate = MutableStateFlow<Int>(0)
    val cartUpdate = _cartUpdate.asStateFlow()

    private val _isInCart = MutableStateFlow(false)
    val isInCart = _isInCart.asStateFlow()

    fun getBook(id: Int){
        _bookState.value = NetworkStatus.Loading
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _cartUpdate.update { cartItems.size }
                _isInCart.update { cartItems.any{it.id == id}}
                val book = bookRepo.getBook(id)
                _bookState.value = NetworkStatus.Success(book)
            }
        }
    }

    fun addToCart(book:Book){
        cartItems.add(book)
        _cartUpdate.update { cartItems.size }
        _isInCart.update { cartItems.any{it.id == book.id}}
    }
}