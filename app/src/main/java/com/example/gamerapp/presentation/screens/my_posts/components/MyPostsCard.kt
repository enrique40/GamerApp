package com.example.gamerapp.presentation.screens.my_posts.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.gamerapp.domain.model.Post
import com.example.gamerapp.presentation.navigation.DetailsScreen
import com.example.gamerapp.presentation.screens.my_posts.MyPostsViewModel
import com.example.gamerapp.presentation.screens.profile.ProfileViewModel

@Composable
fun MyPostsCard(
    navController: NavHostController,
    post: Post,
    viewModel: MyPostsViewModel = hiltViewModel(),
    viewModelP: ProfileViewModel = hiltViewModel(),
) {
    var newData by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = true) {
        viewModelP.dataFromDataStore.collect { data ->
            newData = data
        }
    }
    Card(
        modifier = Modifier
            .padding(top = 15.dp, bottom = 15.dp)
            .clickable {
                navController.navigate(route = DetailsScreen.DetailPost.passPost(post.toJson()))
            },
        elevation = 4.dp,
        shape = RoundedCornerShape(20.dp),
        backgroundColor = if (newData) {
            Color.LightGray
        } else {
            Color.White
        },
    ) {
        Column {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp),

                model = post.image,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
                ) {
                Column(modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp, top = 10.dp, end = 5.dp)
                ) {
                    Text(
                        text = post.name,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Black
                    )
                    Text(
                        modifier = Modifier.padding(top = 10.dp),
                        text = post.description,
                        fontSize = 13.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Black

                    )
                }

                Column(modifier = Modifier.padding(end = 10.dp)) {
                    IconButton(
                        onClick = {
                            navController.navigate(
                                route = DetailsScreen.UpdatePost.passPost(
                                    post.toJson()
                                )
                            )
                        }) {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            imageVector = Icons.Default.Edit,
                            contentDescription = "",
                            tint = Color.Black
                        )
                    }
                    IconButton(onClick = { viewModel.delete(post.id) }) {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            imageVector = Icons.Default.Delete,
                            contentDescription = "",
                            tint = Color.Black
                        )
                    }
                }
            }
        }
    }
}