package com.example.gamerapp.presentation.screens.posts

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import com.example.gamerapp.presentation.screens.posts.components.DeleteLikePosts
import com.example.gamerapp.presentation.screens.posts.components.GetPosts
import com.example.gamerapp.presentation.screens.posts.components.LikePosts

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PostsScreen(navController: NavHostController,  darkMode: MutableState<Boolean>) {
    Scaffold(
        content = {
            GetPosts(navController, darkMode = darkMode)
        }
    )
    LikePosts()
    DeleteLikePosts()
}