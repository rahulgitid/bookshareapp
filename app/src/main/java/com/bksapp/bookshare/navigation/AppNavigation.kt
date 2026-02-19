package com.bksapp.bookshare.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bksapp.bookshare.ui.login.LoginScreen
import com.bksapp.bookshare.ui.signup.SignUpScreen

@Composable
fun AppNavigation(){

    val navController = rememberNavController()

    NavHost(navController, startDestination = AppRoutes.Login.getRoute()) {

        composable(
            route = AppRoutes.Login.getRoute()
        ) {
            LoginScreen{
                navController.navigate(AppRoutes.Signup.getRoute()){
                    navController.popBackStack()
                }
            }
        }

        composable(
            route = AppRoutes.Signup.getRoute()
        ) {
            SignUpScreen()
        }
    }
}

