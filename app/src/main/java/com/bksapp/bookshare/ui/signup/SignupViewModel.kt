package com.bksapp.bookshare.ui.signup

import androidx.compose.ui.unit.TextUnit
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


data class UserData(
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


class SignupViewModel : ViewModel()
{
   private val _signupState = MutableStateFlow<UserData>(UserData())
           val signupState : StateFlow<UserData> = _signupState.asStateFlow()

    fun updateEmail(email : String)
    {
        val flag = email.isNotBlank()
        _signupState.update { it.copy(email=email, isValidEmail = flag) }
        isValid()
    }

    fun updateName(name : String)
    {
        val flag = name.isNotBlank()
        _signupState.update { it.copy(name = name, isValidName = flag) }
        isValid()
    }

    fun updatePhone(phone : String)
    {
        val flag = phone.isNotBlank() && phone.length==10
        _signupState.update { it.copy(phone=phone, isValidPhone = flag) }
        isValid()
    }

    fun updateDOB(dob : String)
    {
        val flag = dob.isNotBlank()
        _signupState.update { it.copy(dob = dob,isValidDOB = flag) }
        isValid()
    }

    fun isValid()
    {
        val valid = _signupState.value.isValidEmail
                && _signupState.value.isValidName
                && _signupState.value.isValidPhone
                && _signupState.value.isValidDOB
         _signupState.update { it.copy(isValid = valid) }
    }
}