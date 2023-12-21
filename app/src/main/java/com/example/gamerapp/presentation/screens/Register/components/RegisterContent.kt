package com.example.gamerapp.presentation.screens.Register.components

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
import androidx.compose.material.icons.filled.Person
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
import com.example.gamerapp.presentation.screens.Register.RegisterViewModel
import com.example.gamerapp.presentation.screens.profile.ProfileViewModel
import com.example.gamerapp.presentation.ui.theme.Dargray500
import com.example.gamerapp.presentation.ui.theme.Red500

@Composable
fun RegisterContent(navController: NavHostController, registerViewModel: RegisterViewModel = hiltViewModel(),  viewModelP: ProfileViewModel = hiltViewModel()) {

    val state = registerViewModel.state
    val scrollState = rememberScrollState()

    var newData by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = true) {
        viewModelP.dataFromDataStore.collect { data ->
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
                .height(250.dp)
                .background(Red500)
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
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
            Modifier.padding(start = 30.dp, end = 30.dp, top = 140.dp),
            colors = CardDefaults.cardColors(containerColor = Dargray500)
        ) {
            Column(
                modifier = Modifier.verticalScroll(scrollState).padding(horizontal = 20.dp)
            ) {
                Text(
                    modifier = Modifier.padding(top = 15.dp),
                    text = "REGISTRO",
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
                    onValueChange = { registerViewModel.onUserNameInput(it)},
                    label = "Nombre de usuario",
                    icon = Icons.Default.Person,
                    errorMsg = registerViewModel.userNameErrorMsg,
                    validateField = {
                        registerViewModel.validateUserName()
                    }
                )

                DefaultTextField(
                    modifier = Modifier.fillMaxWidth().padding(top = 0.dp),
                    value = state.email,
                    onValueChange = { registerViewModel.onEmailInput(it)},
                    label = "Correo electronico",
                    icon = Icons.Default.Email,
                    keyboardType = KeyboardType.Email,
                    errorMsg = registerViewModel.emailErrMsg,
                    validateField = {
                        registerViewModel.validateEmail()
                    }
                )
                DefaultTextField(
                    modifier = Modifier.fillMaxWidth().padding(top = 0.dp),
                    value = state.password,
                    onValueChange = {  registerViewModel.onPasswordInput(it)},
                    label = "Password",
                    icon = Icons.Default.Lock,
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                registerViewModel.passwordVisibility = ! registerViewModel.passwordVisibility
                            }
                        ) {
                            Icon(
                                painter = if (registerViewModel.passwordVisibility) painterResource(id = R.drawable.visibility_fill0) else painterResource(id = R.drawable.visibility_off),
                                contentDescription = "Toggle Password Icon",
                                tint = if (newData) Color.LightGray else Color.White
                            )
                        }
                    },
                    visualTransformation = if (registerViewModel.passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                    errorMsg = registerViewModel.passwordErrMsg,
                    validateField = {
                        registerViewModel.validatePassword()
                    }
                )

                DefaultTextField(
                    modifier = Modifier.fillMaxWidth().padding(top = 0.dp),
                    value = state.confirmPassword,
                    onValueChange = { registerViewModel.onConfirmPasswordInput(it)},
                    label = "Confirmar Password",
                    icon = Icons.Default.Lock,
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                registerViewModel.confirmPasswordVisibility = !registerViewModel.confirmPasswordVisibility
                            }
                        ) {
                            Icon(
                                painter = if (registerViewModel.confirmPasswordVisibility) painterResource(id = R.drawable.visibility_fill0) else painterResource(id = R.drawable.visibility_off),
                                contentDescription = "Toggle Password Icon",
                                tint = if (newData) Color.LightGray else Color.White

                            )
                        }
                    },
                    visualTransformation = if (registerViewModel.confirmPasswordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                    errorMsg = registerViewModel.confirmPasswordErrorMsg,
                    validateField = {
                        registerViewModel.validateConfirmPassword()
                    }
                )

                DefaultButtom(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    text = "REGISTRARSE",
                    onClick = {
                              registerViewModel.onSignup()
                    },
                    enable = registerViewModel.isEnableLoginButton

                )

                RegisterBottomBar(navController)
            }
        }
    }

}
