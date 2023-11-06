package com.example.gamerapp.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.gamerapp.presentation.screens.Register.RegisterSreen
import com.example.gamerapp.presentation.screens.login.LoginScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ) {
        composable(route =  AuthScreen.Login.route) {
            LoginScreen(navController)
        }

        composable(route = AuthScreen.Register.route){
            RegisterSreen(navController)
        }
    }
}

sealed class AuthScreen(val route: String) {

    object Login: AuthScreen(route = "login")
    object Register: AuthScreen(route = "register")
    object Profile: AuthScreen(route = "profile")
    object ProfileUpdate: AuthScreen(route = "profile/update/{user}") {
        fun passUser(user: String) = "profile/update/$user"
    }
}