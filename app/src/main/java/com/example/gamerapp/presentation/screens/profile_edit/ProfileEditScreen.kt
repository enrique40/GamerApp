package com.example.gamerapp.presentation.screens.profile_edit

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.gamerapp.presentation.components.DefaultTopBar
import com.example.gamerapp.presentation.screens.Register.components.Register
import com.example.gamerapp.presentation.screens.login.components.RegisterContent
import com.example.gamerapp.presentation.screens.profile_edit.components.ProfileEditContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileEditScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Nuevo usuario",
                upAvailable = true,
                navController = navController
            )
        },
        content = {
            ProfileEditContent(navController)
        },
        bottomBar = {}
    )
}