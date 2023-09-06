package com.example.gamerapp.domain.repository

import com.example.gamerapp.domain.model.Response
import com.google.firebase.auth.FirebaseUser

interface AuthRespository {

    val currentUser: FirebaseUser?
   suspend fun login(email: String, password: String): Response<FirebaseUser>
   fun logout()
}