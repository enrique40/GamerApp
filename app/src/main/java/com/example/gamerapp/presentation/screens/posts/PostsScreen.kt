package com.example.gamerapp.presentation.screens.posts

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.gamerapp.presentation.screens.posts.components.DeleteLikePosts
import com.example.gamerapp.presentation.screens.posts.components.GetPosts
import com.example.gamerapp.presentation.screens.posts.components.LikePosts

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PostsScreen(navController: NavHostController) {
    Scaffold(
        content = {
            GetPosts(navController)
        }
    )
    LikePosts()
    DeleteLikePosts()
}