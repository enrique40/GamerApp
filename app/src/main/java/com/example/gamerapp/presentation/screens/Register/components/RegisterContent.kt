package com.example.gamerapp.presentation.screens.login.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.gamerapp.domain.model.Response
import com.example.gamerapp.presentation.components.DefaultButtom
import com.example.gamerapp.presentation.components.DefaultTextField
import com.example.gamerapp.presentation.navigation.AppScreen
import com.example.gamerapp.presentation.screens.Register.RegisterViewModel
import com.example.gamerapp.presentation.screens.Register.components.RegisterBottomBar
import com.example.gamerapp.presentation.ui.theme.Dargray500
import com.example.gamerapp.presentation.ui.theme.Red500




@Composable
fun RegisterContent(navController: NavHostController, registerViewModel: RegisterViewModel = hiltViewModel()) {

    //para saber en que estado se encuentra nustra peticion
    val signupFlow = registerViewModel.signupFlow.collectAsState()
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
                    modifier = Modifier.fillMaxWidth().padding(top = 0.dp),
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
                    modifier = Modifier.fillMaxWidth().padding(top = 0.dp),
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
                    modifier = Modifier.fillMaxWidth().padding(top = 0.dp),
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

    signupFlow.value.let {
        when(it){
            Response.Loadin -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator()
                }
            }
            is Response.Sucess -> {
                LaunchedEffect(Unit) {
                    navController.navigate(route = AppScreen.Profile.route){
                        popUpTo(AppScreen.Register.route){ inclusive = true }
                    }
                }
            }
            is Response.Failure -> {
                Toast.makeText(LocalContext.current, it.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
            }
            else -> {}
        }
    }
}
