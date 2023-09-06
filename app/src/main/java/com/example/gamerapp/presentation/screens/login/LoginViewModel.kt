package com.example.gamerapp.presentation.screens.login

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamerapp.domain.model.Response
import com.example.gamerapp.domain.use_cases.auth.AuthUseCases
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCase: AuthUseCases): ViewModel() {

    var email: MutableState<String> =  mutableStateOf("")
    var password: MutableState<String> =  mutableStateOf("")
    var passwordVisibility: MutableState<Boolean> =  mutableStateOf(false)
    val isEmailValid: MutableState<Boolean> = mutableStateOf(false)
    val emailErrMsg: MutableState<String> = mutableStateOf("")
    val isPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    val passwordErrMsg: MutableState<String> = mutableStateOf("")
    var isEnableLoginButton = false

    private val _loginFlow = MutableStateFlow<Response<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Response<FirebaseUser>?> = _loginFlow

    init {
        if (authUseCase.getCurrentUser() != null){ //SESION INICIADA
            _loginFlow.value = Response.Sucess(authUseCase.getCurrentUser()!!)
        }
    }
    fun login() = viewModelScope.launch {
        _loginFlow.value = Response.Loadin
        val result = authUseCase.login(email.value, password.value)
        _loginFlow.value = result
    }
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