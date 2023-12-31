package com.example.gamerapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.example.gamerapp.presentation.utils.UserPreferencesRepository
import com.example.gamerapp.presentation.ui.theme.GamerAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    @Inject
    lateinit var userPreferencesRepository: UserPreferencesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var darkMode by remember { mutableStateOf(false) }
            LaunchedEffect(Unit) {
                userPreferencesRepository.dataStore.data.map { settings ->
                    settings[booleanPreferencesKey("themaa")] ?: false
                }.collect { dataFromDataStore ->
                    darkMode = dataFromDataStore
                }
            }

            GamerAppTheme(darkTheme = darkMode) {
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



