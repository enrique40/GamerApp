package com.example.gamerapp.presentation.screens.profile

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.gamerapp.presentation.components.DefaultButtom
import com.example.gamerapp.presentation.navigation.AppScreen

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(navController: NavHostController, viewModel: ProfileViewModel = hiltViewModel()) {

    Scaffold(
        topBar = {},
        content = {
                  DefaultButtom(
                      modifier = Modifier,
                      text = "Cerrrar Seccion",
                      onClick = {
                          viewModel.logout()
                          navController.navigate(route = AppScreen.Login.route) {
                              popUpTo(AppScreen.Profile.route) { inclusive = true }
                          }
                      }
                  )
        },
        bottomBar = {}
    )
}