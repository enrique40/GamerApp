package com.example.gamerapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.gamerapp.presentation.screens.Register.RegisterSreen
import com.example.gamerapp.presentation.screens.login.LoginScreen
import com.example.gamerapp.presentation.screens.profile.ProfileScreen
import com.example.gamerapp.presentation.screens.profile_update.ProfileUpdateScreen

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

        composable(
            route = AppScreen.ProfileUpdate.route,
            arguments = listOf(navArgument("user"){
                type = NavType.StringType
            })
            ){
            it.arguments?.getString("user")?.let {
                ProfileUpdateScreen(navController, user = it)
            }
        }
    }
}