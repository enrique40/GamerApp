package com.example.gamerapp.presentation.screens.profile_edit.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.gamerapp.R
import com.example.gamerapp.presentation.components.DefaultButtom
import com.example.gamerapp.presentation.components.DefaultTextField
import com.example.gamerapp.presentation.screens.profile_edit.ProfileEditViewModel
import com.example.gamerapp.presentation.ui.theme.Dargray500
import com.example.gamerapp.presentation.ui.theme.Red500

@Composable
fun ProfileEditContent(navController: NavHostController, viewModel: ProfileEditViewModel = hiltViewModel()) {

    val state = viewModel.state
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxWidth(),

        ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .background(Red500)
        ) {

            Column(
                modifier = Modifier.fillMaxWidth().padding(top = 80.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .height(130.dp)
                        .padding(top = 20.dp),
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "imagen de usuario"
                )
            }

        }

        Card(
            Modifier.padding(start = 30.dp, end = 30.dp, top = 220.dp),
            colors = CardDefaults.cardColors(containerColor = Dargray500)
        ) {
            Column(
                modifier = Modifier.verticalScroll(scrollState).padding(horizontal = 20.dp)
            ) {
                Text(
                    modifier = Modifier.padding(top = 15.dp),
                    text = "ACTUALIZACION",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Por favor ingrese estos datos para continuar",
                    fontSize = 12.sp,
                    color = Color.Gray
                )

                DefaultTextField(
                    modifier = Modifier.fillMaxWidth().padding(top = 15.dp),
                    value = state.username,
                    onValueChange = { viewModel.onUserNameInput(it)},
                    label = "Nombre de usuario",
                    icon = Icons.Default.Person,
                    errorMsg = viewModel.userNameErrorMsg,
                    validateField = {
                        viewModel.validateUserName()
                    }
                )

                DefaultButtom(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    text = "ACTUALIZAR DATOS",
                    onClick = {
                        viewModel.onUpdate()
                    },
                )
            }
        }
    }

}