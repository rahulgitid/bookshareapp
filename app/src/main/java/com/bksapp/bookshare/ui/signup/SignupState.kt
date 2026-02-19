package com.bksapp.bookshare.ui.signup

data class SignupState(
    val email : String = "",
    val name : String = "",
    val phone : String = "",
    val dob : String = "",
    val isValidEmail : Boolean = false,
    val isValidName : Boolean = false,
    val isValidPhone : Boolean = false,
    val isValidDOB : Boolean = false,
    val isValid : Boolean = false
)