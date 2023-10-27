package com.example.gamerapp.presentation.screens.profile_edit

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.gamerapp.presentation.components.DefaultTopBar
import com.example.gamerapp.presentation.screens.profile_edit.components.ProfileEditContent
import com.example.gamerapp.presentation.screens.profile_edit.components.Update

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileEditScreen(
    navController: NavHostController
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
            ProfileEditContent(navController)
        },
        bottomBar = {}
    )
    Update()
}