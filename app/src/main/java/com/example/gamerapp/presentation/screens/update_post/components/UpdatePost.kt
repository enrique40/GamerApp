package com.example.gamerapp.presentation.screens.update_post.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gamerapp.domain.model.Response
import com.example.gamerapp.presentation.components.ProgressBar
import com.example.gamerapp.presentation.screens.new_post.NewPostViewModel
import com.example.gamerapp.presentation.screens.update_post.UpdatePostViewModel

@Composable
fun UpdatePost(viewModel: UpdatePostViewModel = hiltViewModel()) {
    when (val resonse = viewModel.updatePostResponse) {
        // MOSTRAR QUE SE ESTA REALIZANDO LA PETICION Y TODAVIA ESTA EN PROCESO
        Response.Loading -> {
            ProgressBar()
        }
        is Response.Sucess -> {
            viewModel.clearForm()
            Toast.makeText(
                LocalContext.current,
                "La publicacion se ha actulizado correctamente",
                Toast.LENGTH_LONG
            ).show()
        }
        is Response.Failure -> {
            Toast.makeText(
                LocalContext.current,
                resonse.exception?.message ?: "Error desconido",
                Toast.LENGTH_LONG
            ).show()
        }
        else -> {}
    }
}
