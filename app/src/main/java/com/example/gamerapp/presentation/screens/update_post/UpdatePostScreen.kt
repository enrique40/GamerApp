package com.example.gamerapp.presentation.screens.update_post

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.gamerapp.presentation.components.DefaultButtom
import com.example.gamerapp.presentation.components.DefaultTopBar
import com.example.gamerapp.presentation.screens.new_post.components.NewPost
import com.example.gamerapp.presentation.screens.update_post.components.UpdatePostContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UpdatePostScreen(navController: NavHostController, post: String, viewModel: UpdatePostViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Editar Post",
                upAvailable = true,
                navController = navController,
            )
        },
        content = {
            UpdatePostContent()
        },
        bottomBar = {
            DefaultButtom(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 7.dp, end = 7.dp),
                text = "Actualizar",
                onClick = {
                    viewModel.onNewPost()
                }
            )
        }
    )

    NewPost()
}