package com.bksapp.bookshare.ui.cart

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bksapp.bookshare.data.local.entity.Book
import com.bksapp.bookshare.data.repository.AddressRepoImpl
import com.bksapp.bookshare.data.repository.CartRepositoryImpl
import com.bksapp.bookshare.domain.repository.AddressRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@Immutable
data class CartUIState(
    val itemsList: List<Book> = emptyList(),
    val itemInCarts: Int = 0,
    val total: Int = 0,
    val shipping: Int = 50
)
@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepo : CartRepositoryImpl,
    private val addressRepo: AddressRepoImpl
): ViewModel() {

    private val triggerStart = MutableSharedFlow<Unit>(1)


    init {
        triggerStart.tryEmit(Unit)
    }
    @OptIn(ExperimentalCoroutinesApi::class)
    val cartState = triggerStart.flatMapLatest {
        cartRepo.cartItems()
            .map { list ->
                var sum = list.sumOf { it.cartQuantity * it.price }
                var itemsCount = list.sumOf { it.cartQuantity }
                CartUIState(
                    itemsList = list,
                    itemInCarts = itemsCount,
                    total = sum
                )
            }


    } .stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(100),
        CartUIState()
    )

     fun removeCartItem(bookId: Int)= viewModelScope.launch { cartRepo.deleteItemFromCart(bookId) }
     fun plusQuantity(book: Book)= viewModelScope.launch {  cartRepo.addItemToCart(book, 1) }

     fun minusQuantity(book: Book)= viewModelScope.launch { cartRepo.addItemToCart(book,-1) }

    fun isAddressAvailable():Boolean{
        return addressRepo.isAddressAvailable()
    }

}