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
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.gamerapp.presentation.components.DefaultButtom
import com.example.gamerapp.presentation.components.DefaultTextField
import com.example.gamerapp.presentation.ui.theme.Dargray500
import com.example.gamerapp.presentation.ui.theme.Red500



@Preview(showBackground = true)
@Composable
fun RegisterContent() {
    Box(
        modifier = Modifier
            .fillMaxWidth(),

        ) {
        RegisterBoxHeader()
        RegisterCardForm()
    }
}

@Composable
fun RegisterBoxHeader() {
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
                modifier = Modifier.height(130.dp).padding(top = 20.dp),
                painter = painterResource(id = R.drawable.user),
                contentDescription = "imagen de usuario"
            )
        }

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterCardForm() {

    var email by remember {
        mutableStateOf("")
    }

    var user by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var confirmPassword by remember {
        mutableStateOf("")
    }

    var passwordVisibility by remember { mutableStateOf(false) }
    var confirmPasswordVisibility by remember { mutableStateOf(false) }

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
                value = user,
                onValueChange = { user = it},
                label = "Nombre de usuario",
                icon = Icons.Default.Person,
            )

            DefaultTextField(
                modifier = Modifier.padding(top = 5.dp),
                value = email,
                onValueChange = { email = it},
                label = "Correo electronico",
                icon = Icons.Default.Email,
                keyboardType = KeyboardType.Email
            )
            DefaultTextField(
                modifier = Modifier.padding(top = 5.dp),
                value = password,
                onValueChange = { password = it},
                label = "Password",
                icon = Icons.Default.Lock,
                trailingIcon = {
                    IconButton(
                        onClick = {
                            passwordVisibility = !passwordVisibility
                        }
                    ) {
                        Icon(
                            painter = if (passwordVisibility) painterResource(id = R.drawable.visibility_fill0) else painterResource(id = R.drawable.visibility_off),
                            contentDescription = "Toggle Password Icon"
                        )
                    }
                },
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()
            )

            DefaultTextField(
                modifier = Modifier.padding(top = 5.dp),
                value = confirmPassword,
                onValueChange = { confirmPassword = it},
                label = "Confirmar Password",
                icon = Icons.Default.Lock,
                trailingIcon = {
                    IconButton(
                        onClick = {
                            confirmPasswordVisibility = !confirmPasswordVisibility
                        }
                    ) {
                        Icon(
                            painter = if (confirmPasswordVisibility) painterResource(id = R.drawable.visibility_fill0) else painterResource(id = R.drawable.visibility_off),
                            contentDescription = "Toggle Password Icon"
                        )
                    }
                },
                visualTransformation = if (confirmPasswordVisibility) VisualTransformation.None else PasswordVisualTransformation()
            )

            DefaultButtom(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp),
                text = "REGISTRARSE",
                onClick = {  },
            )
        }
    }
}