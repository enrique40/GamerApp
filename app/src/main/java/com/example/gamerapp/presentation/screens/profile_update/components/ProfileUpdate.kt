package com.example.gamerapp.presentation.screens.profile_update.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gamerapp.domain.model.Response
import com.example.gamerapp.presentation.components.ProgressBar
import com.example.gamerapp.presentation.screens.profile_update.ProfileUpdateViewModel


@Composable
fun ProfileUpdate(viewModel: ProfileUpdateViewModel = hiltViewModel()) {
    when(val updateResponse = viewModel.updateResponse) {
        Response.Loading -> {
            ProgressBar()
        }
        is Response.Sucess -> {
            Toast.makeText(LocalContext.current, "Datos actualizado correctamente", Toast.LENGTH_LONG).show()
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, updateResponse.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
        }
        else -> {}
    }
}