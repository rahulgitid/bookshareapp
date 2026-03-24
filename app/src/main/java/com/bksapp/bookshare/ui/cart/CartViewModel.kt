package com.bksapp.bookshare.ui.cart

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bksapp.bookshare.data.local.entity.Book
import com.bksapp.bookshare.domain.cartItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@Immutable
data class CartUIState(
    val itemsList: List<Book> = emptyList()
)
@HiltViewModel
class CartViewModel @Inject constructor(): ViewModel() {

    private val _cartState = MutableStateFlow(CartUIState())
    val cartState = _cartState.asStateFlow()
    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                _cartState.update { it.copy(itemsList = cartItems.toMutableList()) }
            }
        }
    }

     fun removeCartItem(bookId: Int){
         viewModelScope.launch {
             withContext(Dispatchers.IO){
                 cartItems.removeIf { it.id == bookId }
                 _cartState.update {state-> state.copy(itemsList = cartItems.toMutableList()) }
             }
         }


    }

}