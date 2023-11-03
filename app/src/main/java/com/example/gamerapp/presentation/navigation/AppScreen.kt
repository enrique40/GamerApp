package com.example.gamerapp.presentation.navigation

sealed class AppScreen(val route: String) {

    object Login: AppScreen(route = "login")
    object Register: AppScreen(route = "register")
    object Profile: AppScreen(route = "profile")
    object ProfileUpdate: AppScreen(route = "profile/update/{user}") {
        fun passUser(user: String) = "profile/update/$user"
    }
}
