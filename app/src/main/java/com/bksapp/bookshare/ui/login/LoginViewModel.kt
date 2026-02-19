package com.bksapp.bookshare.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bksapp.bookshare.data.repository.LoginRepositoryImpl
import com.bksapp.bookshare.data.repository.NetworkStatus
import com.bksapp.bookshare.domain.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject




@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepo: LoginRepository
) : ViewModel()
{
    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()

    private val _loginStatus = MutableStateFlow<NetworkStatus>(NetworkStatus.Idle)
    val loginStatus = _loginStatus.asStateFlow()


    fun callLogin(){
        _loginStatus.value = NetworkStatus.Loading
        viewModelScope.launch {
            withContext(Dispatchers.IO)
            {
                val status =  loginRepo.getUser(_loginState.value.email,_loginState.value.password)
                _loginStatus.value = status
            }

        }
    }



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