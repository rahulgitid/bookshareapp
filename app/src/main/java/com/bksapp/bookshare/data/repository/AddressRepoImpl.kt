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
import kotlinx.coroutines.flow.map
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
            val aID = idGenerator()
            if (newAddress.isDefault) {
                val resetList = inMemoryAddress.map { it.copy(isDefault = false) }
                inMemoryAddress.clear()
                inMemoryAddress.addAll(resetList)
            }

            val updatedAddress = newAddress.copy(id = aID)
            inMemoryAddress.add(updatedAddress)
            _addressStateFlow.update { inMemoryAddress.toList() }
        }
    }

    suspend fun setCurrentAddress(address: Address) {
        withContext(Dispatchers.IO) {

            inMemoryAddress.apply {
                remove(address)
                add(0,address)
                _addressStateFlow.update { inMemoryAddress.toList() }
            }
        }
    }


    fun makeCurrentAddress():Flow<Address> = _addressStateFlow.map{list->
        list.firstOrNull()?: Address()
    }


private fun idGenerator():Int{
    val address  = inMemoryAddress.maxByOrNull { it.id }?: Address()
    return address.id+1
}



}