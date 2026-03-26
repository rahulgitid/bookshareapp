package com.bksapp.bookshare.ui.bookdetail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bksapp.bookshare.data.local.entity.Book
import com.bksapp.bookshare.data.repository.CartRepositoryImpl
import com.bksapp.bookshare.data.repository.NetworkStatus
import com.bksapp.bookshare.domain.repository.BookRepository
import com.bksapp.bookshare.navigation.AppRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    private val bookRepo : BookRepository,
    savedStateHandle: SavedStateHandle,
    private val cartRepo : CartRepositoryImpl
): ViewModel() {

    private var bookId: Int = checkNotNull(savedStateHandle[AppRoutes.BookDetails.BOOK_ID])
    private val _bookState = MutableStateFlow<NetworkStatus<Book>>(NetworkStatus.Idle)
    val bookState = _bookState.asStateFlow()

    private val triggerStart = MutableSharedFlow<Unit>(1)

    init {
        viewModelScope.launch {
            _bookState.value = NetworkStatus.Loading
            withContext(Dispatchers.IO) {
                getBook(bookId)
                triggerStart.tryEmit(Unit)

            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val cartCount = triggerStart.flatMapLatest {
        cartRepo.cartItems()
            .map {
                it.size
            }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(100),
        0
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    val isInCart = triggerStart.flatMapLatest {
        cartRepo.cartItems()
            .map {
                it.any { book -> book.id == bookId }
            }
    } .stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(100),
        false
    )




    fun getBook(id: Int){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val book = bookRepo.getBook(id)
                _bookState.value = NetworkStatus.Success(book)
            }
        }
    }

    fun addToCart(book:Book){
        viewModelScope.launch {
            cartRepo.addItemToCart(book, 1)
        }
    }
}