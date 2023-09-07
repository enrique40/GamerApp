package com.example.gamerapp.data.repository

import com.example.gamerapp.domain.model.Response
import com.example.gamerapp.domain.model.User
import com.example.gamerapp.domain.repository.UsersRepository

class UsersRepositoryImpl: UsersRepository {
    override suspend fun create(user: User): Response<Boolean> {

    }
}