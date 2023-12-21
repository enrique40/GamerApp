package com.example.gamerapp.presentation.screens.my_posts

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.gamerapp.presentation.navigation.DetailsScreen
import com.example.gamerapp.presentation.screens.my_posts.components.GetPostsByIdUser
import com.example.gamerapp.presentation.ui.theme.Red500

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyPostsScreen(navController: NavHostController) {
    Scaffold(
        content = {
            GetPostsByIdUser(navController = navController)
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(bottom = 80.dp),
                onClick = { navController.navigate(DetailsScreen.NewPost.route) },
                containerColor = Red500
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = ""
                )
            }
        }
    )
}