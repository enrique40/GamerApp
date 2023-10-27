package com.example.gamerapp.presentation.screens.login

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamerapp.domain.model.Response
import com.example.gamerapp.domain.use_cases.auth.AuthUseCases
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCase: AuthUseCases): ViewModel() {


    //STATE FORM
    var state by mutableStateOf(LoginState())
        private set


    var passwordVisibility by  mutableStateOf(false)
    var isEmailValid by mutableStateOf(false)
    var emailErrMsg by mutableStateOf("")
    var isPasswordValid by mutableStateOf(false)
    var passwordErrMsg by mutableStateOf("")
    var isEnableLoginButton = false

    //LOGIN STATE
    var loginResponse by mutableStateOf<Response<FirebaseUser>?>(null)


    init {
        Log.e("TAG", " loginViewModel")
        if (authUseCase.getCurrentUser() != null){ //SESION INICIADA
            loginResponse = Response.Sucess(authUseCase.getCurrentUser()!!)
            Log.e("TAG", " loginViewModel 1 --${loginResponse}")
        }
    }

    fun onEmailInput(email: String) {
        state = state.copy(email = email)
    }

    fun onPasswordInput(password: String) {
        state = state.copy(password = password)
    }
    fun login() = viewModelScope.launch {
        loginResponse = Response.Loading
        val result = authUseCase.login(state.email, state.password)
        loginResponse = result
    }
    fun enabledLoginButton(){
        isEnableLoginButton = isEmailValid && isPasswordValid

    }

    fun validateEmail(){
        //Es un email valido
        if (Patterns.EMAIL_ADDRESS.matcher(state.email).matches()){
            isEmailValid = true
            emailErrMsg = ""
        }else {
            isEmailValid = false
            emailErrMsg = "El email no es valido"
        }
        enabledLoginButton()
    }

    fun validatePassword(){
        if (state.password.length >= 6){
            isPasswordValid = true
            passwordErrMsg = ""
        }else {
            isPasswordValid = false
            passwordErrMsg = "Al menos 6 caracteres"
        }
        enabledLoginButton()
    }
}