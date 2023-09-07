package com.example.gamerapp.domain.repository

import com.example.gamerapp.domain.model.Response
import com.example.gamerapp.domain.model.User

interface UsersRepository {
    suspend fun create(user: User): Response<Boolean>
}