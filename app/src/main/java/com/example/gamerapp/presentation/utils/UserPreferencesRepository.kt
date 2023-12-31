package com.example.gamerapp.presentation.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import javax.inject.Inject

class UserPreferencesRepository @Inject constructor(
    val dataStore: DataStore<Preferences>
)