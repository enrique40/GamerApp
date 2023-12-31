package com.example.gamerapp.presentation.screens.login

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gamerapp.presentation.screens.login.components.Login
import com.example.gamerapp.presentation.screens.login.components.LoginContent
import com.example.gamerapp.presentation.ui.theme.GamerAppTheme


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavHostController) {
    Scaffold(
        topBar = {},
        content = {
            LoginContent(navController)
        },
        bottomBar = {

        }
    )
    // Manejar el estado de la peticion del login
    Login(navController = navController)
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    GamerAppTheme(darkTheme = true) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LoginScreen(rememberNavController())
        }
    }
}