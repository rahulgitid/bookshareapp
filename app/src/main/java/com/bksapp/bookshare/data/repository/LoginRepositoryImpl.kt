package com.bksapp.bookshare.data.repository

import android.util.Log
import com.bksapp.bookshare.data.local.entity.User
import com.bksapp.bookshare.domain.repository.LoginRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

sealed class NetworkStatus<out T>{
    object Idle : NetworkStatus<Nothing>()
    object Loading : NetworkStatus<Nothing>()
    data class Success<out T>(val data : T) : NetworkStatus<T>()
    data class Error(val error : String) : NetworkStatus<Nothing>()
}
class LoginRepositoryImpl @Inject constructor() : LoginRepository {
    override suspend fun getUser(email: String, pass: String): NetworkStatus<User> {
        val result =  callUser(email,pass)
        return if(result.email.isBlank()) {
            NetworkStatus.Error("No User Found")
        } else {
            NetworkStatus.Success(result)
        }
    }
}

private suspend fun callUser(email : String, password : String) : User
{
    val userList = listOf(
        User("Rahul","rahul@mail.com","12345678"),
        User("Parth","parth@mail.com","12345678")
    )
    delay(2000)
    Log.i("LoginRepositoryImpl","$email $password")
    return userList.find { user -> user.email.equals(email) && user.password.equals(password) }?:User(name="",email="",password="")
}