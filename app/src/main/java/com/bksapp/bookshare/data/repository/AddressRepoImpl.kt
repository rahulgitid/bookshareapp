package com.bksapp.bookshare.data.repository

import android.util.Log
import com.bksapp.bookshare.domain.repository.AddressRepo
import com.bksapp.bookshare.domain.repository.inMemoryAddress
import com.bksapp.bookshare.ui.address.Address
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
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