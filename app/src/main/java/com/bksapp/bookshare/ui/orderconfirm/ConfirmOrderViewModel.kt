package com.bksapp.bookshare.ui.orderconfirm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bksapp.bookshare.data.repository.AddressRepoImpl
import com.bksapp.bookshare.data.repository.CartRepositoryImpl
import com.bksapp.bookshare.ui.address.Address
import com.bksapp.bookshare.ui.cart.CartUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ConfirmOrderViewModel @Inject constructor(
    private val addressRepo : AddressRepoImpl,
    private val cartRepo : CartRepositoryImpl
): ViewModel() {

    private val triggerStart = MutableSharedFlow<Unit>(1)


    init {
        triggerStart.tryEmit(Unit)
    }
    @OptIn(ExperimentalCoroutinesApi::class)
    val addressState = addressRepo.getNewAddress()
        .map{
               if(it.isNotEmpty())it[0] else Address()
           }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(100),
        Address()
    )

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


}