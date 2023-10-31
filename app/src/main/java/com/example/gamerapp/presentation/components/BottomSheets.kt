package com.example.gamerapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gamerapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheets(
    status: MutableState<Boolean>,
    takePhoto: () -> Unit,
    pickImage: () -> Unit,
) {
    if (status.value) {
        ModalBottomSheet(
            onDismissRequest = {
                status.value = false
            },
            containerColor = Color.White,
            sheetState = rememberModalBottomSheetState(),
        ) {
            Text(
                text = "Seleccione una opcion",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 20.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                .padding(horizontal = 70.dp),
            horizontalArrangement = Arrangement.Start
            ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(0.5f)
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Image(
                        modifier = Modifier
                            .height(80.dp)
                            .width(80.dp)
                            .clickable {
                                status.value = false
                                pickImage()
                            },
                        painter = painterResource(id = R.drawable.baseline_image_24),
                        contentDescription = "Gallery")
                    Text(text = "Galeria", color = Color.Black)
                    Spacer(modifier = Modifier.height(30.dp))
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(0.5f)
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Image(
                        modifier = Modifier
                            .height(80.dp)
                            .width(80.dp)
                            .clickable {
                                status.value = false
                                takePhoto()
                            },
                        painter =  painterResource(id = R.drawable.baseline_camera_alt_24),
                        contentDescription = "Camare")
                    Text(text = "Camara", color = Color.Black)
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        }
    }
}