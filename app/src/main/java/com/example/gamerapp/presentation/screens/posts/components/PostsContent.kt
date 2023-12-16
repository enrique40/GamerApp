package com.example.gamerapp.presentation.screens.posts.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.gamerapp.domain.model.Post

@Composable
fun PostsContent(
    navController: NavHostController,
    post: List<Post>
) {
   LazyColumn(
       modifier = Modifier.fillMaxWidth()
           .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 80.dp)
   ) {
       items(
           items = post
       ) { post->
           PostsCard(navController =  navController, post = post)
       }
   }
}