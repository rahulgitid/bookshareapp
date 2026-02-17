package com.bksapp.bookshare.ui.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


data class LoginData(
    val email : String = "",
    val password : String = "",
    val isValid : Boolean = false
)

class LoginViewModel : ViewModel()
{
    private val _loginState = MutableStateFlow<LoginData>(LoginData())
    val loginState = _loginState.asStateFlow()

    fun validEmail(email : String)
    {
        val flag = email.contains("@")
        _loginState.update { data ->  data.copy(email = email, isValid = flag) }
    }

    fun validPass(password : String)
    {
        val flag = password.length>=6
        _loginState.update { data -> data.copy(password = password, isValid = flag) }
    }
}