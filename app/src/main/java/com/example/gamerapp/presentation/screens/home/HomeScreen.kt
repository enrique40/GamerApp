package com.example.gamerapp.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gamerapp.presentation.navigation.HomeBottomBarScreen
import com.example.gamerapp.presentation.navigation.HomeBottonBarNavGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = {}
    ){
        HomeBottonBarNavGraph(navController = navController)
    }
}

@Composable
fun RowScope.AddItem(
    screen: HomeBottomBarScreen,
    currenDestination: NavDestination?,
    navController: NavHostController

) {
}