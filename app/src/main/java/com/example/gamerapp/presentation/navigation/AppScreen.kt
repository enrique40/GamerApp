package com.example.gamerapp.presentation.navigation

sealed class AppScreen(val route: String) {

    object Login: AppScreen(route = "login")
    object Register: AppScreen(route = "register")
    object Profile: AppScreen(route = "profile")
    object ProfileEdit: AppScreen(route = "profile/edit/{user}") {
        fun passUser(user: String) = "profile/edit/$user"
    }
}
