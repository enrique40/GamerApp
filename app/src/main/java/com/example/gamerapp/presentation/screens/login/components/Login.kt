package com.example.gamerapp.presentation.screens.login.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.gamerapp.domain.model.Response
import com.example.gamerapp.presentation.components.ProgressBar
import com.example.gamerapp.presentation.navigation.Graph
import com.example.gamerapp.presentation.navigation.RootScreen
import com.example.gamerapp.presentation.screens.login.LoginViewModel

@Composable
fun Login(navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()) {
    when (val loginResponse = viewModel.loginResponse) {
        // MOSTRAR QUE SE ESTA REALIZANDO LA PETICION Y TODAVIA ESTA EN PROCESO
        Response.Loading -> {
            ProgressBar()
        }
        is Response.Sucess -> {
            LaunchedEffect(Unit) {
                navController.navigate(route = RootScreen.Home.route) {
                    popUpTo(Graph.AUTHENTICATION) { inclusive = true }
                }
            }
        }
        is Response.Failure -> {
            Toast.makeText(
                LocalContext.current,
                loginResponse.exception?.message ?: "Error desconido",
                Toast.LENGTH_LONG
            ).show()
        }
        else -> {}
    }
}
