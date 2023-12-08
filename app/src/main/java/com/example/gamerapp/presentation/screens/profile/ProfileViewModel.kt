package com.example.gamerapp.presentation.screens.profile

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamerapp.domain.model.User
import com.example.gamerapp.domain.use_cases.auth.AuthUseCases
import com.example.gamerapp.domain.use_cases.users.UsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val usersUseCase: UsersUseCase
) : ViewModel() {

    var userData by mutableStateOf(User())
        private set


    init {
        getUserById()
    }

    private fun getUserById() = viewModelScope.launch {
        usersUseCase.getUserById(authUseCases.getCurrentUser()!!.uid).collect { data ->
            Log.e("TAG", "getUserById --${data}")
            userData = data
        }
    }

    fun logout() {
        authUseCases.logout()
    }

}