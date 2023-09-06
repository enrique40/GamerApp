package com.example.gamerapp.presentation.screens.profile

import androidx.lifecycle.ViewModel
import com.example.gamerapp.domain.use_cases.auth.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val authUseCases: AuthUseCases) : ViewModel() {

    fun logout() {
        authUseCases.logout()
    }

}