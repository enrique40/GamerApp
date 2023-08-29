package com.example.gamerapp.presentation.screens.Register

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.gamerapp.presentation.screens.Register.components.RegisterBottomBar
import com.example.gamerapp.presentation.screens.login.components.RegisterContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterSreen(navController: NavHostController) {
    Scaffold(
        topBar = { },
        content = {
            RegisterContent()
        },
         bottomBar = {
             RegisterBottomBar(navController)
         }
    )
}