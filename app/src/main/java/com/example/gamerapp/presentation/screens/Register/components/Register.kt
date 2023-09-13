package com.example.gamerapp.presentation.screens.Register.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.gamerapp.domain.model.Response
import com.example.gamerapp.presentation.components.ProgressBar
import com.example.gamerapp.presentation.navigation.AppScreen
import com.example.gamerapp.presentation.screens.Register.RegisterViewModel

@Composable
fun Register(navController: NavHostController, viewModel: RegisterViewModel = hiltViewModel()) {
    when(val registerResponse = viewModel.registerResponse){
        Response.Loadin -> {
            ProgressBar()
        }
        is Response.Sucess -> {
            LaunchedEffect(Unit) {
                viewModel.createUser()
                navController.popBackStack(AppScreen.Login.route, true)
                navController.navigate(route = AppScreen.Profile.route)
            }
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, registerResponse.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
        }
        else -> {}
    }
    
}