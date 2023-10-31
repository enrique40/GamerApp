package com.example.gamerapp.presentation.screens.Register

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.gamerapp.presentation.screens.Register.components.Register
import com.example.gamerapp.presentation.screens.Register.components.RegisterContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterSreen(navController: NavHostController) {
    Scaffold(
        topBar = { },
        content = {
            RegisterContent(navController)
        },
         bottomBar = {

         }
    )
    Register(navController = navController)
}