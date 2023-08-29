package com.example.gamerapp.presentation.screens.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gamerapp.R
import androidx.compose.material3.Icon
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gamerapp.presentation.components.DefaultButtom
import com.example.gamerapp.presentation.components.DefaultTextField
import com.example.gamerapp.presentation.screens.Register.RegisterViewModel
import com.example.gamerapp.presentation.ui.theme.Dargray500
import com.example.gamerapp.presentation.ui.theme.Red500




@Composable
fun RegisterContent(registerViewModel: RegisterViewModel = hiltViewModel()) {
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
                    modifier = Modifier
                        .height(130.dp)
                        .padding(top = 20.dp),
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "imagen de usuario"
                )
            }

        }

        Card(
            Modifier.padding(start = 30.dp, end = 30.dp, top = 155.dp),
            colors = CardDefaults.cardColors(containerColor = Dargray500)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                Text(
                    modifier = Modifier.padding(top = 40.dp),
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
                    modifier = Modifier.padding(top = 25.dp),
                    value = registerViewModel.userName.value,
                    onValueChange = { registerViewModel.userName.value = it},
                    label = "Nombre de usuario",
                    icon = Icons.Default.Person,
                    errorMsg = registerViewModel.userNameErrorMsg.value,
                    validateField = {
                        registerViewModel.validateUserName()
                    }
                )

                DefaultTextField(
                    modifier = Modifier.padding(top = 0.dp),
                    value = registerViewModel.email.value,
                    onValueChange = { registerViewModel.email.value = it},
                    label = "Correo electronico",
                    icon = Icons.Default.Email,
                    keyboardType = KeyboardType.Email,
                    errorMsg = registerViewModel.emailErrMsg.value,
                    validateField = {
                        registerViewModel.validateEmail()
                    }
                )
                DefaultTextField(
                    modifier = Modifier.padding(top = 0.dp),
                    value = registerViewModel.password.value,
                    onValueChange = {  registerViewModel.password.value = it},
                    label = "Password",
                    icon = Icons.Default.Lock,
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                registerViewModel.passwordVisibility.value = ! registerViewModel.passwordVisibility.value
                            }
                        ) {
                            Icon(
                                painter = if (registerViewModel.passwordVisibility.value) painterResource(id = R.drawable.visibility_fill0) else painterResource(id = R.drawable.visibility_off),
                                contentDescription = "Toggle Password Icon"
                            )
                        }
                    },
                    visualTransformation = if (registerViewModel.passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
                    errorMsg = registerViewModel.passwordErrMsg.value,
                    validateField = {
                        registerViewModel.validatePassword()
                    }
                )

                DefaultTextField(
                    modifier = Modifier.padding(top = 0.dp),
                    value = registerViewModel.confirmPassword.value,
                    onValueChange = { registerViewModel.confirmPassword.value = it},
                    label = "Confirmar Password",
                    icon = Icons.Default.Lock,
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                registerViewModel.confirmPasswordVisibility.value = !registerViewModel.confirmPasswordVisibility.value
                            }
                        ) {
                            Icon(
                                painter = if (registerViewModel.confirmPasswordVisibility.value) painterResource(id = R.drawable.visibility_fill0) else painterResource(id = R.drawable.visibility_off),
                                contentDescription = "Toggle Password Icon"
                            )
                        }
                    },
                    visualTransformation = if (registerViewModel.confirmPasswordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
                    errorMsg = registerViewModel.confirmPasswordErrorMsg.value,
                    validateField = {
                        registerViewModel.validateConfirmPassword()
                    }
                )

                DefaultButtom(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 15.dp),
                    text = "REGISTRARSE",
                    onClick = {  },
                    enable = registerViewModel.isEnableLoginButton

                )
            }
        }
    }
}
