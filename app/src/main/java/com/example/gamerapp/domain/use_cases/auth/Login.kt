package com.example.gamerapp.domain.use_cases.auth

import com.example.gamerapp.data.repository.AuthRepositoryImpl
import com.example.gamerapp.domain.repository.AuthRespository
import javax.inject.Inject

class Login @Inject constructor(private val repository: AuthRespository) {

    suspend operator fun invoke(email: String, password: String) = repository.login(email, password)
}