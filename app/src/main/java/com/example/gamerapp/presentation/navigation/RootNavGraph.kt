package com.example.gamerapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gamerapp.presentation.screens.home.HomeScreen

@Composable
fun RootNavGraph(navController: NavHostController) {
    //Nota: para llamar a un NavHost dentro de otro se tiene que llamar dentro de un composable
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ){
        authNavGraph(navController = navController)
        composable(route = Graph.HOME) {
            HomeScreen()
        }
    }
}

