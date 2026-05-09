package com.bksapp.bookshare.domain.repository

import com.bksapp.bookshare.ui.address.Address
import kotlinx.coroutines.flow.Flow

// in memory addresses
val inMemoryAddress = ArrayList<Address>()

interface AddressRepo {

    suspend fun setAddress(newAddress: Address)
    fun isAddressAvailable():Boolean
}