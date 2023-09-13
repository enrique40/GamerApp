package com.example.gamerapp.presentation.screens.Register

import androidx.compose.runtime.Composable

data class RegisterState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",

)