package com.example.gamerapp.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.gamerapp.presentation.screens.Register.RegisterSreen
import com.example.gamerapp.presentation.screens.login.LoginScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    //startDestination para definir la ruta inicial osea que vista se ava a ejecutar primero
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
}