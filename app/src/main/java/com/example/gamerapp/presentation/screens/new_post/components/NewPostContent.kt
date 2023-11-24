package com.example.gamerapp.presentation.screens.new_post.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gamerapp.R
import com.example.gamerapp.presentation.components.DefaultTextField
import com.example.gamerapp.presentation.ui.theme.Red500

data class CategoryRadioButtom(
    var category: String,
    var image: Int
)
@Composable
fun NewPostContent() {

    val radioOptions = listOf(
        CategoryRadioButtom("PC", R.drawable.icon_pc),
        CategoryRadioButtom("PS4", R.drawable.icon_ps4),
        CategoryRadioButtom("XBOX", R.drawable.icon_xbox),
        CategoryRadioButtom("NINTENDO", R.drawable.icon_nintendo),
        CategoryRadioButtom("MOBIL", R.drawable.icon_mobile)
    )
    Box(
        modifier = Modifier
            .fillMaxWidth(),
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .background(Red500)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 100.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier.height(120.dp),
                        painter = painterResource(id = R.drawable.add_image),
                        contentDescription = "Control  de xbox 360"
                    )
                    Text(
                        text = "SELECCIONA UNA IMAGEN",
                        color = Color.White,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            DefaultTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp, start = 20.dp, end = 20.dp),
                value = "",
                onValueChange = { },
                label = "Nombre del juego",
                icon = Icons.Default.Email,
                errorMsg = "",
                validateField = {
                }
            )

            DefaultTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, start = 20.dp, end = 20.dp),
                value = "",
                onValueChange = { },
                label = "Descripcion",
                icon = Icons.Default.List,
                errorMsg = "",
                validateField = {
                }
            )
            
            Text(
                modifier = Modifier.padding(vertical = 15.dp),
                text = "CATEGORIAS",
                fontSize = 17.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            radioOptions.forEach { option ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 35.dp)
                        .selectable(
                            selected = false,
                            onClick = {}
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = false,
                        onClick = {  }
                    )
                    Row {
                        Text(
                            modifier = Modifier
                                .width(110.dp)
                                .padding(12.dp),
                            text = option.category,
                            color = Color.White,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Image(
                            modifier = Modifier
                                .height(50.dp)
                                .padding(8.dp),
                            painter = painterResource(id = option.image),
                            contentDescription = ""
                        )
                    }
                }
            }
        }
    }
}