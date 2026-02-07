package com.bksapp.bookshare.navigation


sealed class AppRoutes{
    object Login : AppRoutes(){
        fun getRoute():String
        {
            return "login"
        }
    }
    object Home : AppRoutes()
}