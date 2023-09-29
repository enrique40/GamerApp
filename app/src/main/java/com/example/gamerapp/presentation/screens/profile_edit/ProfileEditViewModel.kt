package com.example.gamerapp.presentation.screens.profile_edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileEditViewModel @Inject constructor() : ViewModel() {

    //STATE FORM
    var state by mutableStateOf(ProfileEditState())
        private set

    var userNameErrorMsg: String by mutableStateOf("")
        private set
    fun onUserNameInput(username: String) {
        state = state.copy(username = username)
    }

    fun validateUserName(){
        if (state.username.length >= 5){
            userNameErrorMsg = ""
        }else{
            userNameErrorMsg = "Al menos 5 caracteres"
        }
    }
}