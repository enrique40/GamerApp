package com.example.gamerapp.presentation.screens.Register

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamerapp.domain.model.Response
import com.example.gamerapp.domain.model.User
import com.example.gamerapp.domain.use_cases.auth.AuthUseCases
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val authUseCases: AuthUseCases) : ViewModel() {

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

    private val _signupFlow = MutableStateFlow<Response<FirebaseUser>?>(null)
    val signupFlow:  StateFlow<Response<FirebaseUser>?> = _signupFlow

    fun onSignup() {
        val user = User(
            username = userName.value,
            email = email.value,
            password = password.value
        )
        signup(user)
    }
    fun signup(user: User) = viewModelScope.launch {
        _signupFlow.value = Response.Loadin
        val result = authUseCases.signup(user)
        _signupFlow.value = result
    }
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