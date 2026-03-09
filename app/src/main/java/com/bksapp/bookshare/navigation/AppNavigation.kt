package com.bksapp.bookshare.navigation

import android.util.Log
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocal
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bksapp.bookshare.ui.bookdetail.BookDetails
import com.bksapp.bookshare.ui.dashboard.HomeScreen
import com.bksapp.bookshare.ui.login.LoginEvent
import com.bksapp.bookshare.ui.login.LoginScreen
import com.bksapp.bookshare.ui.signup.SignUpScreen
import com.bksapp.bookshare.ui.theme.AppTheme
import com.bksapp.bookshare.ui.theme.Primary

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AppNavigation(){

    val navController = rememberNavController()
    AppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {Text("Login")},
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.popBackStack()
                        }) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* Handle action */ }) {
                            Icon(Icons.Filled.Search, contentDescription = "Search")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Primary
                    )
                )
            }
        ) { innerpading ->
            NavHost(navController, startDestination = AppRoutes.Home.getRoute(),
                modifier = Modifier.padding(innerpading),
                enterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                },
                popEnterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                }
                )
            {

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
                    HomeScreen{book->
                        navController.navigate(AppRoutes.BookDetails.getRoute(book.id))
                    }
                }

                composable(
                    route = AppRoutes.BookDetails.getRoute(AppRoutes.BookDetails.BOOK_ID),
                    arguments = listOf(navArgument(AppRoutes.BookDetails.BOOK_ID,{type = NavType.IntType}))
                ){ backStackEntry->
                    val id = backStackEntry.arguments?.getInt(AppRoutes.BookDetails.BOOK_ID)?:0
                    if(id==0){
                        navController.popBackStack()
                    }
                    else{
                        BookDetails(id)
                    }
                }

            }
        }
    }

}

