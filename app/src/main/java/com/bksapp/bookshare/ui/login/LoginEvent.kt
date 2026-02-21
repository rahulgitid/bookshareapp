package com.bksapp.bookshare.ui.login

import androidx.navigation.NavController
import com.bksapp.bookshare.navigation.AppRoutes

class LoginEvent(val navController: NavController) {
     fun loginSuccess(){
        navController.navigate(AppRoutes.Home.getRoute()) {
            navController.popBackStack()
        }
    }

    fun signupTextClicked(){
        navController.navigate(AppRoutes.Signup.getRoute())
    }
}