package com.example.gamerapp.presentation.screens.login

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {

    var email: MutableState<String> =  mutableStateOf("")
    var password: MutableState<String> =  mutableStateOf("")
    var passwordVisibility: MutableState<Boolean> =  mutableStateOf(false)
    val isEmailValid: MutableState<Boolean> = mutableStateOf(false)
    val emailErrMsg: MutableState<String> = mutableStateOf("")
    val isPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    val passwordErrMsg: MutableState<String> = mutableStateOf("")
    var isEnableLoginButton = false

    fun enabledLoginButton(){
        isEnableLoginButton = isEmailValid.value && isPasswordValid.value

    }

    fun validateEmail(){
        //Es un email valido
        if (Patterns.EMAIL_ADDRESS.matcher(email.value).matches()){
            isEmailValid.value = true
            emailErrMsg.value = ""
        }else {
            isEmailValid.value = false
            emailErrMsg.value = "El email no es valido"
        }
        enabledLoginButton()
    }

    fun validatePassword(){
        if (password.value.length >= 6){
            isPasswordValid.value = true
            passwordErrMsg.value = ""
        }else {
            isPasswordValid.value = false
            passwordErrMsg.value = "Al menos 6 caracteres"
        }
        enabledLoginButton()
    }
}