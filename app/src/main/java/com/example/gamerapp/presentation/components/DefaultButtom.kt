package com.example.gamerapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.gamerapp.presentation.ui.theme.Red500

@Composable
fun DefaultButtom(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit,
    color: Color = Red500,
    icon: ImageVector = Icons.Default.ArrowForward,
    enable: Boolean = true,
    textColor: Color = Color.White,
    colorIcon: Color = Color.White
) {
    Column {
        Button(
            modifier = modifier,
            onClick = { onClick() },
            colors = ButtonDefaults.buttonColors(containerColor = color),
            enabled = enable,

        ) {
            Icon( tint = colorIcon, imageVector = icon, contentDescription = "" )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text =  text, color = textColor)
        }
    }

}