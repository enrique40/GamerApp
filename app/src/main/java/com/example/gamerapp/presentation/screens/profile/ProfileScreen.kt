package com.example.gamerapp.presentation.screens.profile

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.gamerapp.presentation.screens.profile.components.ProfileContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(navController: NavHostController) {
    Scaffold(
        topBar = {},
        content = {
            ProfileContent(navController)
        },
        bottomBar = {}
    )
}