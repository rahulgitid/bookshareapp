package com.bksapp.bookshare.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bksapp.bookshare.ui.login.LoginScreen

@Composable
fun AppNavigation(){

    val navController = rememberNavController()

    NavHost(navController, startDestination = AppRoutes.Login.getRoute()) {

        composable(
            route = AppRoutes.Login.getRoute()
        ) {
            LoginScreen()
        }
    }
}

