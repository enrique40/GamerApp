package com.example.gamerapp.core

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

object Constants {

    const val USERS = "Users"
    const val POSTS = "Posts"
    val Context.DataStoree: DataStore<Preferences> by preferencesDataStore(name = "sd")

}