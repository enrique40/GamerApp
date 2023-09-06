package com.example.gamerapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gamerapp.presentation.screens.Register.RegisterSreen
import com.example.gamerapp.presentation.screens.login.LoginScreen
import com.example.gamerapp.presentation.screens.profile.ProfileScreen

@Composable
fun AppNavigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = AppScreen.Login.route
    ){

        composable(route =  AppScreen.Login.route) {
            LoginScreen(navController)
        }

        composable(route = AppScreen.Register.route){
            RegisterSreen(navController)
        }

        composable(route = AppScreen.Profile.route){
            ProfileScreen(navController)
        }
    }
}