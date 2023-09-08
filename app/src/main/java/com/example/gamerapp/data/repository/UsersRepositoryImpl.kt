package com.example.gamerapp.data.repository

import com.example.gamerapp.domain.model.Response
import com.example.gamerapp.domain.model.User
import com.example.gamerapp.domain.repository.UsersRepository
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(private val usersRef: CollectionReference) : UsersRepository {
    override suspend fun create(user: User): Response<Boolean> {
        return try {
            user.password = ""
            usersRef.document(user.id).set(user).await()
            Response.Sucess(true)
        }catch (e: Exception){
           e.printStackTrace()
           Response.Failure(e)
        }
    }
}