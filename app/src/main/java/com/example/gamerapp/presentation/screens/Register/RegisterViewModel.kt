package com.example.gamerapp.presentation.screens.Register

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamerapp.domain.model.Response
import com.example.gamerapp.domain.model.User
import com.example.gamerapp.domain.use_cases.auth.AuthUseCases
import com.example.gamerapp.domain.use_cases.users.UsersUseCase
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val authUseCases: AuthUseCases, private val usersUseCase: UsersUseCase) : ViewModel() {

    //STATE FORM
    var state by mutableStateOf(RegisterState())
    // USERNAME
    private var isUserNameValid by mutableStateOf(false)

    var userNameErrorMsg: String by mutableStateOf("")
    // EMAIL
    private var isEmailValid by mutableStateOf(false)

    var emailErrMsg by mutableStateOf("")


    // PASSWORD
    private var isPasswordValid by mutableStateOf(false)

    var passwordErrMsg by mutableStateOf("")

    // ENABLE BUTTON
    var isEnableLoginButton = false

    // CONFIRMAR CONTRASEÑA
    var passwordVisibility by mutableStateOf(false)

    var confirmPasswordVisibility by  mutableStateOf(false)

    private var isConfirmPassWord by mutableStateOf(false)

    var confirmPasswordErrorMsg by mutableStateOf("")

     var registerResponse  by mutableStateOf<Response<FirebaseUser>?>(null)

    var user = User()

    fun onEmailInput(email: String) {
        state = state.copy(email = email)
    }
    fun onUserNameInput(username: String) {
        state = state.copy(username = username)
    }
    fun onPasswordInput(password: String) {
        state = state.copy(password = password)
    }
    fun onConfirmPasswordInput(confirmPassword: String) {
        state = state.copy(confirmPassword = confirmPassword)
    }

    fun onSignup() {
        user.username = state.username
        user.email = state.email
        user.password = state.password

        signup(user)
    }

    private fun signup(user: User) = viewModelScope.launch {
        registerResponse = Response.Loadin
        val result = authUseCases.signup(user)
        registerResponse = result
    }

    fun createUser() = viewModelScope.launch {
        user.id = authUseCases.getCurrentUser()!!.uid
        usersUseCase.create(user)
    }

    private fun enabledLoginButton(){
        isEnableLoginButton = isEmailValid
                && isPasswordValid &&
                isUserNameValid &&
                isConfirmPassWord

    }

    fun validateConfirmPassword() {
        if (state.password == state.confirmPassword){
            isConfirmPassWord = true
            confirmPasswordErrorMsg = ""
        }else {
            isConfirmPassWord = false
            confirmPasswordErrorMsg = "Las contraseñas no coinciden"
        }
        enabledLoginButton()
    }

    fun validateUserName(){
        if (state.username.length >= 5){
            isUserNameValid = true
            userNameErrorMsg = ""
        }else{
            isUserNameValid = false
            userNameErrorMsg = "Al menos 5 caracteres"
        }
        enabledLoginButton()
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