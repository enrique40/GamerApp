package com.example.gamerapp.presentation.screens.profile

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamerapp.domain.model.User
import com.example.gamerapp.domain.use_cases.auth.AuthUseCases
import com.example.gamerapp.domain.use_cases.users.UsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val usersUseCase: UsersUseCase,
    private val userPreferencesRepository: UserPreferencesRepository
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

     suspend fun saveToDataStore(value: Boolean) {
        userPreferencesRepository.dataStore.edit { settings ->
            settings[booleanPreferencesKey("themaa")] = value
        }
    }

    // Flujo para recuperar el valor del DataStore
    val dataFromDataStore: Flow<Boolean> = userPreferencesRepository.dataStore.data.map { settings ->
        settings[booleanPreferencesKey("themaa")] ?: false
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        true
    )

}