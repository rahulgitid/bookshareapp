package com.bksapp.bookshare.navigation

import android.util.Log

private const val SPLASH_ROUTE = "splash"
private const val LOGIN_ROUTE = "login"
private const val SIGNUP_ROUTE = "signup"
private const val HOME_ROUTE = "home"
private const val BOOK_DETAIL = "bookdetail"

enum class TitleName {
    Splash,
    Login,
    Home,
    Detail
}
sealed class AppRoutes{

    object Splash : AppRoutes(){
        fun getRoute(): String{
            return SPLASH_ROUTE
        }
    }
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

    object BookDetails : AppRoutes(){

        const val BOOK_ID = "bookid"
        fun getRoute(param : String) : String
        {
            return "$BOOK_DETAIL/{$param}"
        }

        fun getRoute(id : Int) : String
        {
            return "$BOOK_DETAIL/$id"
        }

        fun getRoute(): String{
            return BOOK_DETAIL
        }
    }
}


fun getTitleForRoute(route: String): String{
    Log.i("routeadfdsgfghfd","$route")

    var currentRoute = route

    if(route.contains("/")){
        currentRoute = route.split("/")[0]
    }
    return when(currentRoute){
        SPLASH_ROUTE-> TitleName.Splash.name
        LOGIN_ROUTE-> TitleName.Login.name
        HOME_ROUTE-> TitleName.Home.name
        BOOK_DETAIL -> TitleName.Detail.name
        else -> ""

    }
}