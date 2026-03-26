package com.bksapp.bookshare.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bksapp.bookshare.ui.bookdetail.BookDetails
import com.bksapp.bookshare.ui.cart.CartScreen
import com.bksapp.bookshare.ui.dashboard.HomeScreen
import com.bksapp.bookshare.ui.login.LoginEvent
import com.bksapp.bookshare.ui.login.LoginScreen
import com.bksapp.bookshare.ui.signup.SignUpScreen
import com.bksapp.bookshare.ui.splash.SplashScreen
import com.bksapp.bookshare.ui.theme.AppTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AppNavigation(){
    val animationTime = 300
    val navController = rememberNavController()
    AppTheme {
        Scaffold{ innerpading ->
            NavHost(navController, startDestination = AppRoutes.Splash.getRoute(),
                modifier = Modifier.padding(innerpading),
                enterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(animationTime)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(animationTime)
                    )
                },
                popEnterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(animationTime)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(animationTime)
                    )
                }
                )
            {

                composable(route = AppRoutes.Splash.getRoute()){
                    SplashScreen {
                        navController.navigate(AppRoutes.Home.getRoute()){
                            popUpTo(AppRoutes.Splash.getRoute()){
                                inclusive = true
                            }

                            launchSingleTop = true
                        }
                    }

                }

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
                        navController.popBackStack()
                    }
                }

                composable(
                    route = AppRoutes.Home.getRoute(),
                ){
                    HomeScreen(
                        {id->
                        navController.navigate(AppRoutes.BookDetails.getRoute(id))
                    },
                    {} )
                }

                composable(
                    route = AppRoutes.BookDetails.getRoute(AppRoutes.BookDetails.BOOK_ID),
                    arguments = listOf(
                        navArgument(AppRoutes.BookDetails.BOOK_ID) {
                            type = NavType.IntType
                        }
                    )
                ){ backStackEntry->
                    val id = backStackEntry.arguments?.getInt(AppRoutes.BookDetails.BOOK_ID)?:0
                    BookDetails(id,{
                        navController.navigate(AppRoutes.Cart.getRoute()){
                            launchSingleTop = true
                        }
                    }){
                        navController.popBackStack()
                    }
                }

                composable(route = AppRoutes.Cart.getRoute()) {
                    CartScreen{
                        navController.popBackStack()
                    }
                }



            }
        }
    }

}

