package com.example.gamerapp.presentation.screens.my_posts.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.gamerapp.domain.model.Post
import com.example.gamerapp.presentation.navigation.DetailsScreen
import com.example.gamerapp.presentation.screens.my_posts.MyPostsViewModel

@Composable
fun MyPostsCard(navController: NavHostController, post: Post, viewModel: MyPostsViewModel = hiltViewModel()) {
    Card(
        modifier = Modifier
            .padding(top = 15.dp, bottom = 15.dp)
            .clickable {
                navController.navigate(route = DetailsScreen.DetailPost.passPost(post.toJson()))
            },
        elevation = 4.dp,
        shape = RoundedCornerShape(20.dp),
        backgroundColor = Color.LightGray
    ) {
        Column() {
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
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 7.dp),
                text = post.description,
                fontSize = 13.sp,
                maxLines = 2,
                color = Color.Black
            )
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

    /*ElevatedCard(
        modifier = Modifier.padding(top = 15.dp, bottom = 15.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column() {
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
                fontWeight = FontWeight.Bold
                )
            Text(
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 7.dp),
                text = post.idUser,
                fontSize = 12.sp
            )
            Text(
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 7.dp),
                text = post.description,
                fontSize = 13.sp,
                maxLines = 2,
                color = Color.Gray
            )
        }
    }*/
}