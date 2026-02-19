package com.bksapp.bookshare.ui.login

data class LoginState (
    val email: String = "",
    val password: String = "",
    val isValid: Boolean = false
)