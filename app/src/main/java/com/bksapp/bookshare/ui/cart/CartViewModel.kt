package com.bksapp.bookshare.ui.cart

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bksapp.bookshare.data.local.entity.Book
import com.bksapp.bookshare.domain.cartItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@Immutable
data class CartUIState(
    val itemsList: List<Book> = emptyList(),
    val itemInCarts: Int = 0,
    val total: Int = 0,
    val shipping: Int = 50
)
@HiltViewModel
class CartViewModel @Inject constructor(): ViewModel() {

    private val _cartState = MutableStateFlow(CartUIState())
    val cartState = _cartState.asStateFlow()


    private val _carItemSize = MutableStateFlow(0)
    val cartItemSize = _carItemSize.asStateFlow()

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                var sum = 0
                var itemsCount = 0
                 cartItems.forEach {book ->
                         sum += book.price*book.cartQuantity
                         itemsCount += book.cartQuantity
                 }
                _cartState.update { it.copy(
                    itemsList = cartItems.toMutableList(),
                    itemInCarts = itemsCount,
                    total = sum
                    ) }
            }
        }
    }

     fun removeCartItem(bookId: Int){
         viewModelScope.launch {
             withContext(Dispatchers.IO){
                 cartItems.removeIf { it.id == bookId }
                 var sum = 0
                 var itemsCount = 0
                 cartItems.forEach {book ->
                     sum += book.price*book.cartQuantity
                     itemsCount += book.cartQuantity
                 }
                 _cartState.update {state-> state.copy(
                     itemsList = cartItems.toMutableList(),
                     itemInCarts = itemsCount,
                     total = sum
                 ) }

             }
         }


    }

    fun plusQuantity(book: Book){
        val q = book.cartQuantity+1
        val newBook = book.copy(cartQuantity = q)
        cartItems.removeIf { it.id == book.id }
        cartItems.add(newBook)

        var sum = 0
        var itemsCount = 0
        cartItems.forEach {book ->
            sum += book.price*book.cartQuantity
            itemsCount += book.cartQuantity
        }
        _cartState.update {state-> state.copy(
            itemsList = cartItems.toMutableList(),
            itemInCarts = itemsCount,
            total = sum
        ) }
    }
    fun minusQuantity(book: Book){
        val q = book.cartQuantity-1
        if(q==0) return
        val newBook = book.copy(cartQuantity = q)
        cartItems.removeIf { it.id == book.id }
        cartItems.add(newBook)

        var sum = 0
        var itemsCount = 0
        cartItems.forEach {book ->
            sum += book.price*book.cartQuantity
            itemsCount += book.cartQuantity
        }
        _cartState.update {state-> state.copy(
            itemsList = cartItems.toMutableList(),
            itemInCarts = itemsCount,
            total = sum
        ) }
    }

}