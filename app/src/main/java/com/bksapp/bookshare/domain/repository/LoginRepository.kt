package com.bksapp.bookshare.domain.repository

import com.bksapp.bookshare.data.local.entity.User
import com.bksapp.bookshare.data.repository.NetworkStatus

interface LoginRepository {
    suspend fun getUser(email:String,pass:String) : NetworkStatus<User>
}