package com.bksapp.bookshare.navigation


const val LOGIN_ROUTE = "login"
const val SIGNUP_ROUTE = "signup"
const val HOME_ROUTE = "home"
sealed class AppRoutes{
   object Signup : AppRoutes(){
        fun getRoute() : String
        {
            return SIGNUP_ROUTE
        }
    }
    object Login : AppRoutes(){
        fun getRoute() : String
        {
            return LOGIN_ROUTE
        }
    }

    object Home : AppRoutes(){
        fun getRoute() : String
        {
            return HOME_ROUTE
        }
    }
}