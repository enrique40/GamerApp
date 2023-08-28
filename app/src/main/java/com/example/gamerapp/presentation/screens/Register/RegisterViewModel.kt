package com.example.gamerapp.presentation.screens.Register

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {

    var userName: MutableState<String> = mutableStateOf("")
    var isUserNameValid: MutableState<Boolean> = mutableStateOf(false)
    var userNameErrorMsg: MutableState<String> = mutableStateOf("")

    var email: MutableState<String> =  mutableStateOf("")
    var password: MutableState<String> =  mutableStateOf("")
    var confirmPassword: MutableState<String> =  mutableStateOf("")
    var passwordVisibility: MutableState<Boolean> =  mutableStateOf(false)
    var confirmPasswordVisibility: MutableState<Boolean> =  mutableStateOf(false)
    val isEmailValid: MutableState<Boolean> = mutableStateOf(false)
    val emailErrMsg: MutableState<String> = mutableStateOf("")
    val isPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    val passwordErrMsg: MutableState<String> = mutableStateOf("")
    var isEnableLoginButton = false

    var isConfirmPassWord: MutableState<Boolean> = mutableStateOf(false)
    var confirmPasswordErrorMsg: MutableState<String> = mutableStateOf("")


    fun enabledLoginButton(){
        isEnableLoginButton = isEmailValid.value
                && isPasswordValid.value &&
                isUserNameValid.value &&
                isConfirmPassWord.value

    }

    fun validateConfirmPassword() {
        if (password.value == confirmPassword.value){
            isConfirmPassWord.value = true
            confirmPasswordErrorMsg.value = ""
        }else {
            isConfirmPassWord.value = false
            confirmPasswordErrorMsg.value = "Las contraseñas no coinciden"
        }
        enabledLoginButton()
    }

    fun validateUserName(){
        if (userName.value.length >= 5){
            isUserNameValid.value = true
            userNameErrorMsg.value = ""
        }else{
            isUserNameValid.value = false
            userNameErrorMsg.value = "Al menos 5 caracteres"
        }
        enabledLoginButton()
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