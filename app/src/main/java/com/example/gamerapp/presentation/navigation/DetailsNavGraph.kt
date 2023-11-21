package com.example.gamerapp.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.gamerapp.presentation.screens.profile_update.ProfileUpdateScreen

fun NavGraphBuilder.detailsNavGraph (navController: NavHostController) {

    navigation(
        route = Graph.DETAILS,
        startDestination = DetailsScreen.ProfileUpdate.route
    ) {
        composable(
            route = DetailsScreen.ProfileUpdate.route,
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


sealed class DetailsScreen(val route: String) {

    object ProfileUpdate: DetailsScreen(route = "profile/update/{user}") {
        fun passUser(user: String) = "profile/update/$user"
    }
}