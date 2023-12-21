package com.example.gamerapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gamerapp.presentation.ui.theme.Red700

@Composable
fun DefaultTextField(
    modifier: Modifier,
    value: String,
    onValueChange: (value: String) -> Unit,
    validateField: () -> Unit = {},
    label: String,
    icon: ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    errorMsg: String = ""

) {
    Column {
        OutlinedTextField(
            modifier = modifier,
            value = value,
            onValueChange = {
                onValueChange(it)
                validateField()
            },
            textStyle = TextStyle(
                color = Color.White,
            ),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            label = {
                Text(text = label)
            },
            leadingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = "",
                    tint = Color.White
                )
            },
            trailingIcon = trailingIcon,
            visualTransformation = visualTransformation
        )
        Text(
            modifier = Modifier.padding(2.dp),
            text = errorMsg,
            fontSize = 11.sp,
            color = Red700
        )
    }
}