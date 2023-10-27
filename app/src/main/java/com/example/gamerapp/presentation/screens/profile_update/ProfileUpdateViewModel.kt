package com.example.gamerapp.presentation.screens.profile_update

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamerapp.domain.model.Response
import com.example.gamerapp.domain.model.User
import com.example.gamerapp.domain.use_cases.users.UsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileUpdateViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val usersUSeCase: UsersUseCase

) : ViewModel() {

    //STATE FORM
    var state by mutableStateOf(ProfileUpdateState())
        private set

    var userNameErrorMsg: String by mutableStateOf("")
        private set

    val data = savedStateHandle.get<String>("user")

    val user = User.fromJson(data!!)

    //RESPONSE
    var updateResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    //IMAGE
    var imageUri by mutableStateOf<Uri?>(null)
    var hasImage by mutableStateOf(false)

    init {
        state = state.copy(username = user.username)
    }

    fun onCameraResult(result: Boolean){
        hasImage = result
    }
    fun onGalleryResult(uri: Uri) {
        imageUri = uri
    }
    fun onUpdate() {
        val myUser = User(
            id = user.id,
            username = state.username,
            image = ""
        )
        update(myUser)
    }
    fun update(user: User) = viewModelScope.launch {
        updateResponse = Response.Loading
        val result = usersUSeCase.update(user)
        updateResponse = result

    }

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