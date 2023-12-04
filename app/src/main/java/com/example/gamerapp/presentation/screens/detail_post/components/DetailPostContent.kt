package com.example.gamerapp.presentation.screens.detail_post.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.example.gamerapp.R
import com.example.gamerapp.presentation.navigation.RootNavGraph
import com.example.gamerapp.presentation.screens.detail_post.DetailPostViewModel
import com.example.gamerapp.presentation.ui.theme.GamerAppTheme
import com.example.gamerapp.presentation.ui.theme.Red500

@Composable
fun DetailPostContent(navController: NavHostController, viewModel: DetailPostViewModel = hiltViewModel()) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(Color.Black)
        .verticalScroll(rememberScrollState())) {
        Box() {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                model = viewModel.post.image,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            IconButton(onClick = { navController?.popBackStack() }) {
                Icon(
                    modifier = Modifier.size(35.dp),
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    tint = Color.White
                )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp, horizontal = 20.dp)
            ,
            elevation = 4.dp,
            shape = RoundedCornerShape(10.dp),
        ) {
            Row(modifier = Modifier.padding(vertical = 15.dp, horizontal = 15.dp)) {
                AsyncImage(
                    modifier = Modifier
                        .size(55.dp)
                        .clip(CircleShape),
                    model = viewModel.post.user?.image ?: "",
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
                Column(modifier = Modifier.padding(top = 7.dp, start = 20.dp)) {
                    Text(
                        text = viewModel.post.user?.username ?: "",
                        fontSize = 13.sp
                    )
                    Text(
                        text = viewModel.post.user?.email ?: "",
                        fontSize = 11.sp
                    )
                }
            }
        }
        Text(
            modifier = Modifier.padding(start = 20.dp, bottom = 15.dp),
            text = viewModel.post.name,
            fontSize = 13.sp,
            color = Red500,
            fontWeight = FontWeight.Bold

        )
        Card(
            modifier = Modifier.padding(start = 13.dp, bottom = 15.dp),
            elevation = 4.dp,
            shape = RoundedCornerShape(20.dp),
            backgroundColor = Red500
        ) {
            Row(
                modifier = Modifier.padding(vertical = 7.dp, horizontal = 20.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier.size(25.dp),
                    painter = painterResource(
                        id = if (viewModel.post.category == "PC") R.drawable.icon_pc
                        else if (viewModel.post.category == "PS4") R.drawable.icon_ps4
                        else if (viewModel.post.category == "XBOX") R.drawable.icon_xbox
                        else if (viewModel.post.category == "NINTENDO") R.drawable.icon_nintendo
                        else  R.drawable.icon_mobile

                    ),
                    contentDescription = "")
                Spacer(modifier = Modifier.width(7.dp))
                Text(
                    text = viewModel.post.category,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp

                )
            }
        }
        Divider(
            modifier = Modifier.padding(end = 20.dp, top = 10.dp, bottom = 10.dp),
            startIndent = 20.dp,
            thickness = 1.dp,
            color = Color.DarkGray
        )
        Text(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
            text = "DESCRIPCION",
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp,
            color = Color.White

        )

        Text(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp),
            text = viewModel.post.description,
            fontSize = 14.sp,
            color = Color.White

        )
    }
}