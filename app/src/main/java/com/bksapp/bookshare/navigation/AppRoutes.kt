package com.bksapp.bookshare.navigation

import android.util.Log

private const val SPLASH_ROUTE = "splash"
private const val LOGIN_ROUTE = "login"
private const val SIGNUP_ROUTE = "signup"
private const val HOME_ROUTE = "home"
private const val BOOK_DETAIL = "bookDetail"

private const val CART_ROUTE = "cartScreen"

private const val CONFIRM_ORDER = "confirmOrder"

enum class TitleName {
    Splash,
    Login,
    Home,
    Detail,
    Cart,
    ConfirmOrder
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

    object Cart: AppRoutes(){
        fun getRoute(): String{
            return CART_ROUTE
        }
    }

    object ConfirmOrder : AppRoutes(){
        fun getRoute(): String{
            return CONFIRM_ORDER
        }
    }
}


fun getTitleForRoute(route: String): String{

    var currentRoute = route

    if(route.contains("/")){
        currentRoute = route.split("/")[0]
    }
    return when(currentRoute){
        SPLASH_ROUTE-> TitleName.Splash.name
        LOGIN_ROUTE-> TitleName.Login.name
        HOME_ROUTE-> TitleName.Home.name
        BOOK_DETAIL -> TitleName.Detail.name
        CART_ROUTE-> TitleName.Cart.name
        CONFIRM_ORDER-> TitleName.ConfirmOrder.name
        else -> ""

    }
}