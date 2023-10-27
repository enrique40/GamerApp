package com.example.gamerapp.domain.repository

import com.example.gamerapp.domain.model.Response
import com.example.gamerapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    suspend fun create(user: User): Response<Boolean>
    suspend fun update(user: User): Response<Boolean>
    fun getUserById(id: String): Flow<User>
}