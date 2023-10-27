package com.example.gamerapp.presentation.screens.login.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.gamerapp.domain.model.Response
import com.example.gamerapp.presentation.components.ProgressBar
import com.example.gamerapp.presentation.navigation.AppScreen
import com.example.gamerapp.presentation.screens.login.LoginViewModel

@Composable
fun Login(navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()) {
    when(val loginResponse = viewModel.loginResponse){
        //MOSTRAR AL USUARIO QUE SE ESTA MOSTRANDO LA PETICION Y TODAVIA ESA EN PROCESO
        Response.Loading -> {
            ProgressBar()
        }
        is Response.Sucess -> {
            LaunchedEffect(Unit){
                navController.navigate(route = AppScreen.Profile.route){
                    popUpTo(AppScreen.Login.route) { inclusive = true }
                }
            }
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, loginResponse.exception?.message ?: "Error desconocido" , Toast.LENGTH_LONG).show()
        }
        else -> {}
    }
    
}