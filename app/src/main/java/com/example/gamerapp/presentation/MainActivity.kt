package com.example.gamerapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gamerapp.presentation.navigation.RootNavGraph
import com.example.gamerapp.presentation.screens.profile.ProfileViewModel
import com.example.gamerapp.presentation.screens.profile.UserPreferencesRepository
import com.example.gamerapp.presentation.ui.theme.GamerAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    @Inject
    lateinit var userPreferencesRepository: UserPreferencesRepository
    private val viewModel: ProfileViewModel by viewModels() // Use a shared view model

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var darkMode by remember { mutableStateOf(false) }
            Log.e("TAG", "onCreate 1 --${darkMode}")
            LaunchedEffect(Unit) {
                userPreferencesRepository.dataStore.data.map { settings ->
                    settings[booleanPreferencesKey("themaa")] ?: false
                }.collect { dataFromDataStore ->
                    darkMode = dataFromDataStore
                    // Usa el valor de dataFromDataStore aquí
                    Log.e("TAG", "onCreate -$dataFromDataStore")
                }
            }
            Log.e("TAG", "onCreate 2 --${darkMode}")
            GamerAppTheme(darkTheme = darkMode) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    navController = rememberNavController()
                    RootNavGraph(navController = navController)
                }
            }
        }
    }
}



