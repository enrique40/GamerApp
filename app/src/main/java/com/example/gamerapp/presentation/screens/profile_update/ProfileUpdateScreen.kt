package com.example.gamerapp.presentation.screens.profile_update

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.gamerapp.presentation.components.DefaultTopBar
import com.example.gamerapp.presentation.screens.profile_update.components.ProfileUpdateContent
import com.example.gamerapp.presentation.screens.profile_update.components.ProfileUpdate
import com.example.gamerapp.presentation.screens.profile_update.components.SaveImage

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileUpdateScreen(
    navController: NavHostController,
    user: String
    ) {
    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Editar Usuario",
                upAvailable = true,
                navController = navController,
            )
        },
        content = {
            ProfileUpdateContent()
        },
        bottomBar = {}
    )
    SaveImage()
    ProfileUpdate()
}