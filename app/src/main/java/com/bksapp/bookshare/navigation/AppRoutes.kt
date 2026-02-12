package com.bksapp.bookshare.navigation


sealed class AppRoutes{
    object Signup : AppRoutes(){
        fun getRoute() : String
        {
            return "signup"
        }
    }
    object Login : AppRoutes(){
        fun getRoute():String
        {
            return "login"
        }
    }
    object Home : AppRoutes()
}