package com.bksapp.bookshare.ui.login

import android.util.Log
import androidx.navigation.NavController
import com.bksapp.bookshare.navigation.AppRoutes

class LoginEvent(val navController: NavController) {
     fun loginSuccess(){
         Log.i("aefsd35r4456467","loginSuccess")
        navController.navigate(AppRoutes.Home.getRoute()) {
            navController.popBackStack()
        }
    }

    fun signupTextClicked(){
        navController.navigate(AppRoutes.Signup.getRoute())
    }
}