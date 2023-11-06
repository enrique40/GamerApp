package com.example.gamerapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.gamerapp.presentation.screens.home.HomeScreen
import com.example.gamerapp.presentation.screens.profile_update.ProfileUpdateScreen

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ){

        authNavGraph(navController = navController)
        composable(route = RootScreen.Home.route) {
            HomeScreen()
        }
        composable(
            route = AuthScreen.ProfileUpdate.route,
            arguments = listOf(navArgument("user"){
                type = NavType.StringType
            })
            ){
            it.arguments?.getString("user")?.let { user ->
                ProfileUpdateScreen(navController, user = user)
            }
        }
    }
}

sealed class RootScreen(val route: String) {
    object Home: RootScreen("home")
}
