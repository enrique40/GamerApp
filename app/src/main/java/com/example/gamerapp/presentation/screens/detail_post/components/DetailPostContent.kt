package com.example.gamerapp.presentation.screens.detail_post.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.example.gamerapp.R
import com.example.gamerapp.presentation.navigation.RootNavGraph
import com.example.gamerapp.presentation.ui.theme.GamerAppTheme
import com.example.gamerapp.presentation.ui.theme.Red500

@Composable
fun DetailPostContent() {

    Column(modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            painter = painterResource(id = R.drawable.user),
           // model = "",
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp, horizontal = 20.dp)
            ,
            elevation = 4.dp,
            shape = RoundedCornerShape(10.dp),
        ) {
            Row(modifier = Modifier.padding(vertical = 15.dp, horizontal = 15.dp)) {
                Image(
                    modifier = Modifier
                        .size(55.dp)
                        .clip(CircleShape)
                    ,
                    painter = painterResource(id = R.drawable.user),
                    //model = "",
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
                Column(modifier = Modifier.padding(top = 7.dp, start = 20.dp)) {
                    Text(
                        text = "Nombre user",
                        fontSize = 13.sp
                    )
                    Text(
                        text = "Email",
                        fontSize = 11.sp
                    )
                }
            }
        }
        Text(
            modifier = Modifier.padding(start = 20.dp, bottom = 15.dp),
            text = "Titulo del juego",
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
                    painter = painterResource(id = R.drawable.icon_xbox),
                    contentDescription = "")
                Spacer(modifier = Modifier.width(7.dp))
                Text(
                    text = "Categoria",
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
            fontSize = 17.sp

        )

        Text(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp),
            text = "Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó una galería de textos y los mezcló de tal manera que logró hacer un libro de textos especimen. No sólo sobrevivió 500 años, sino que tambien ingresó como texto de relleno en documentos electrónicos, quedando esencialmente igual al original. Fue popularizado en los 60s con la creación de las hojas \"Letraset\", las cuales contenian pasajes de Lorem Ipsum, y más recientemente con software de autoedición, como por ejemplo Aldus PageMaker, el cual incluye versiones de Lorem Ipsum.",
            fontSize = 14.sp

        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewContent() {
    GamerAppTheme(darkTheme = true) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
          DetailPostContent()
        }
    }
}