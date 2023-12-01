package com.example.gamerapp.presentation.screens.posts.components

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.gamerapp.domain.model.Post

@Composable
fun PostsContent(
    post: List<Post>
) {
   LazyColumn(
       modifier = Modifier.fillMaxWidth()
   ) {
       items(
           items = post
       ) { post->
           Log.e("TAG", "PostsContent ${post.name} " )
            Text(text = post.name, color = Color.White)
       }
   }
}