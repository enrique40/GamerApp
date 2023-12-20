package com.example.gamerapp.presentation.screens.posts.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.gamerapp.R
import com.example.gamerapp.domain.model.Post
import com.example.gamerapp.presentation.navigation.DetailsScreen
import com.example.gamerapp.presentation.screens.posts.PostsViewModel

@Composable
fun PostsCard(navController: NavHostController, post: Post, viewModel: PostsViewModel = hiltViewModel(), darkMode: Boolean) {
    Card(
        modifier = Modifier
            .padding(top = 15.dp, bottom = 15.dp)
            .clickable {
                navController.navigate(route = DetailsScreen.DetailPost.passPost(post.toJson()))
            },
        elevation = 4.dp,
        shape = RoundedCornerShape(20.dp),
        backgroundColor = if (darkMode) {
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
            Text(
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp),
                text = post.name,
                fontWeight = FontWeight.Bold,
                color = Color.Black
                )
            Text(
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
                text = post.user?.username ?: "",
                fontSize = 12.sp,
                color = Color.Black
            )
            Text(
                modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 0.dp, bottom = 10.dp),
                text = post.description,
                fontSize = 13.sp,
                maxLines = 2,
                color = Color.Black
            )
            Row(
                modifier = Modifier.padding(start = 15.dp, bottom = 15.dp)
            ) {
                if (post.likes.contains(viewModel.currentUser?.uid)) {
                    Image(
                        modifier = Modifier
                            .size(25.dp)
                            .clickable { viewModel.deleteLike(post.id, viewModel.currentUser?.uid ?: "") },
                        painter = painterResource(id = R.drawable.baseline_favorite_24),
                        contentDescription = ""
                    )
                }else {
                    Image(
                        modifier = Modifier
                            .size(25.dp)
                            .clickable { viewModel.like(post.id, viewModel.currentUser?.uid ?: "") },
                        painter = painterResource(id = R.drawable.baseline_favorite_border_24),
                        contentDescription = ""
                    )
                }
                Text(
                    modifier = Modifier.padding(start = 5.dp),
                    text = post.likes.size.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    color = Color.Black
                )
            }
        }
    }
}