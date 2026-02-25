package com.bksapp.bookshare.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bksapp.bookshare.ui.dashboard.HomeScreen
import com.bksapp.bookshare.ui.login.LoginEvent
import com.bksapp.bookshare.ui.login.LoginScreen
import com.bksapp.bookshare.ui.signup.SignUpScreen

@Composable
fun AppNavigation(padding: PaddingValues){

    val navController = rememberNavController()

    NavHost(navController, startDestination = AppRoutes.Home.getRoute(),
        modifier = Modifier.padding(padding)) {

        composable(
            route = AppRoutes.Login.getRoute()
        ) {
            val loginEvent = LoginEvent(navController)
            LoginScreen(
                onLogin = loginEvent::loginSuccess,
                onSignup = loginEvent::signupTextClicked
            )
        }

        composable(
            route = AppRoutes.Signup.getRoute()
        ) {
            SignUpScreen{
                navController.navigate(AppRoutes.Login.getRoute()){
                    navController.popBackStack()
                }
            }
        }

        composable(
            route = AppRoutes.Home.getRoute()
        ){
            HomeScreen{book->

            }
        }

    }
}

