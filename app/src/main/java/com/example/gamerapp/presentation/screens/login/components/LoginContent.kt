package com.example.gamerapp.presentation.screens.login.components

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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gamerapp.R
import androidx.compose.material3.Icon
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.gamerapp.presentation.components.DefaultButtom
import com.example.gamerapp.presentation.components.DefaultTextField
import com.example.gamerapp.presentation.screens.login.LoginViewModel
import com.example.gamerapp.presentation.screens.profile.ProfileViewModel
import com.example.gamerapp.presentation.ui.theme.Dargray500
import com.example.gamerapp.presentation.ui.theme.Red500



@Composable
fun LoginContent(navController: NavHostController, viewModel: LoginViewModel = hiltViewModel(), viewModelP: ProfileViewModel = hiltViewModel()) {

    val scrollState = rememberScrollState()
    val state = viewModel.state
    var newData by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = true) {
        viewModelP.dataFromDataStore.collect { data ->
            // Haz algo con los datos aquí
            newData = data
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .background(Red500)
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.height(130.dp),
                    painter = painterResource(id = R.drawable.control),
                    contentDescription = "Control  de xbox 360"
                )
                Text(
                    text = "FIREBASE MVVM"
                )
            }
        }

        Card(
            Modifier.padding(start = 30.dp, end = 30.dp, top = 200.dp),
            colors = CardDefaults.cardColors(containerColor = Dargray500)
        ) {
            Column(
                modifier = Modifier.verticalScroll(scrollState).padding(horizontal = 20.dp)
            ) {
                Text(
                    modifier = Modifier.padding(top = 40.dp),
                    text = "LOGIN",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Por favor inicia seccion para continuar",
                    fontSize = 12.sp,
                    color = Color.Gray
                )

                DefaultTextField(
                    modifier = Modifier.fillMaxWidth().padding(top = 25.dp),
                    value = state.email,
                    onValueChange = { viewModel.onEmailInput(it)},
                    label = "Correo electronico",
                    icon = Icons.Default.Email,
                    keyboardType = KeyboardType.Email,
                    errorMsg = viewModel.emailErrMsg,
                    validateField = {
                        viewModel.validateEmail()
                    }
                )
                DefaultTextField(
                    modifier = Modifier.fillMaxWidth().padding(top = 0.dp),
                    value = state.password,
                    onValueChange = { viewModel.onPasswordInput(it)},
                    label = "Password",
                    icon = Icons.Default.Lock,
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                viewModel.passwordVisibility = !viewModel.passwordVisibility
                            }
                        ) {
                            Icon(
                                painter = if (viewModel.passwordVisibility) painterResource(id = R.drawable.visibility_fill0) else painterResource(id = R.drawable.visibility_off),
                                contentDescription = "Toggle Password Icon",
                                tint = if (newData) Color.LightGray else Color.White
                            )
                        }
                    },
                    visualTransformation = if (viewModel.passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                    errorMsg = viewModel.passwordErrMsg,
                    validateField = {
                        viewModel.validatePassword()
                    }
                )
                DefaultButtom(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    text = "INICIAR SESION",
                    onClick = {
                              viewModel.login()
                    },
                    enable = viewModel.isEnableLoginButton
                )

                LoginBottomBar(navController)
            }
        }
    }

}
