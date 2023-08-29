package com.example.gamerapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.gamerapp.presentation.navigation.AppScreen

@Composable
fun DefaultTopBar(
    imageVector: ImageVector,
    navController: NavHostController,
    modifier: Modifier = Modifier.width(30.dp).height(30.dp)
) {
    Row(
        modifier = Modifier.padding(top = 10.dp),
    ) {
        Image(
            modifier = modifier.clickable {
                navController.navigate(AppScreen.Login.route)
            },
            imageVector = imageVector,
            contentDescription = "",
            colorFilter = ColorFilter.tint(Color.White)

        )
    }

}