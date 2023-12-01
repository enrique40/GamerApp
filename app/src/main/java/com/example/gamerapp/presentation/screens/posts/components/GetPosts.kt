package com.example.gamerapp.presentation.screens.posts.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gamerapp.domain.model.Response
import com.example.gamerapp.presentation.components.ProgressBar
import com.example.gamerapp.presentation.screens.posts.PostsViewModel

@Composable
fun GetPosts(viewModel: PostsViewModel = hiltViewModel()) {
    when (val response = viewModel.postsResponse) {
        // MOSTRAR QUE SE ESTA REALIZANDO LA PETICION Y TODAVIA ESTA EN PROCESO
        Response.Loading -> {
            ProgressBar()
        }
        is Response.Sucess -> {
            PostsContent(post = response.data)
        }
        is Response.Failure -> {
            Toast.makeText(
                LocalContext.current,
                response.exception?.message ?: "Error desconido",
                Toast.LENGTH_LONG
            ).show()
        }
        else -> {}
    }
}