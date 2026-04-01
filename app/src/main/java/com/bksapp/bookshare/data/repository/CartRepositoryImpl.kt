package com.bksapp.bookshare.data.repository

import android.util.Log
import com.bksapp.bookshare.data.local.entity.Book
import com.bksapp.bookshare.domain.repository.CartRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRepositoryImpl @Inject constructor():CartRepository {

    private val _cartItemState = MutableStateFlow<List<Book>>(emptyList())
    override  suspend fun cartItems() = _cartItemState.asStateFlow()


    override suspend fun addItemToCart(book: Book, quantity: Int) {
        withContext(Dispatchers.IO){
            if((book.cartQuantity == 1 && quantity == -1).not()) {
                _cartItemState.update { currentList ->
                  if(currentList.isEmpty() || currentList.any { it.id == book.id }.not()){
                      currentList + book.copy(cartQuantity = book.cartQuantity + quantity)
                  }

                   else{
                       currentList.map { item->
                        if(item.id == book.id){
                            item.copy(cartQuantity = book.cartQuantity + quantity)
                        }
                        else{
                            item
                        }
                    }
                      }


                }
            }
        }

    }

    override suspend fun deleteItemFromCart(bookId: Int) {
        _cartItemState.update {currentList->
            val list = currentList.filterNot { it.id == bookId }
            list.toMutableList()
        }
    }

    override suspend fun isExistInCart(bookId: Int): Boolean {
        return _cartItemState.value.any { it.id == bookId }
    }
}