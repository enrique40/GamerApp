package com.example.gamerapp.presentation.screens.my_posts.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.gamerapp.domain.model.Post
import com.example.gamerapp.presentation.screens.posts.PostsViewModel

@Composable
fun MyPostsContent(
    navController: NavHostController,
    post: List<Post>,
    viewModel: PostsViewModel = hiltViewModel()
) {
    val listState = rememberLazyListState()
   LazyColumn(
       modifier = Modifier
           .fillMaxWidth()
           .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 55.dp),
       state = listState
   ) {
       items(
           items = post
       ) { data->
           viewModel.isScrollInProgress(listState.isScrollInProgress)
           MyPostsCard(navController =  navController, post = data)
       }
   }
}