package com.example.gamerapp.domain.use_cases.auth

import com.example.gamerapp.domain.model.User
import com.example.gamerapp.domain.repository.AuthRespository
import javax.inject.Inject

class Signup @Inject constructor(private val respository: AuthRespository) {

    suspend operator fun invoke(user: User) = respository.signUp(user)
}