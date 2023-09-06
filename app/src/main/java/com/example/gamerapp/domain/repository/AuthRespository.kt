package com.example.gamerapp.domain.repository

import com.example.gamerapp.domain.model.Response
import com.example.gamerapp.domain.model.User
import com.google.firebase.auth.FirebaseUser

interface AuthRespository {

    val currentUser: FirebaseUser?
   suspend fun login(email: String, password: String): Response<FirebaseUser>
   suspend fun signUp(user: User): Response<FirebaseUser>
   fun logout()
}