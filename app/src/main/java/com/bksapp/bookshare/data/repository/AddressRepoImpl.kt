package com.bksapp.bookshare.data.repository

import com.bksapp.bookshare.domain.repository.AddressRepo
import com.bksapp.bookshare.domain.repository.inMemoryAddress
import com.bksapp.bookshare.ui.address.Address
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddressRepoImpl @Inject constructor() : AddressRepo {
    private val _addressStateFlow = MutableStateFlow(inMemoryAddress.toList())
    fun getNewAddress() = _addressStateFlow.asStateFlow()


    override suspend fun setAddress(newAddress: Address) {
        withContext(Dispatchers.IO) {
            inMemoryAddress.add(newAddress)
           _addressStateFlow.update { currentList ->
                currentList + newAddress
            }
        }
    }

}