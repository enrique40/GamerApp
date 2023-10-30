package com.example.gamerapp.presentation.screens.profile_update

import android.content.Context
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
import com.example.gamerapp.presentation.utils.ComposeFileProvider
import com.example.gamerapp.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileUpdateViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val usersUSeCase: UsersUseCase,
    @ApplicationContext private val context: Context

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
    var imageUri by mutableStateOf("")

    val resultingActivityHandler = ResultingActivityHandler()
    init {
        state = state.copy(username = user.username)
    }

    fun pickImage() = viewModelScope.launch {
        val result = resultingActivityHandler.getContent("image/*")
        imageUri = result.toString()
    }

    fun takePhoto() = viewModelScope.launch {
        val result = resultingActivityHandler.takePicturePreview()
        imageUri = ComposeFileProvider.getPathFromBitmap(context, result!!)
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