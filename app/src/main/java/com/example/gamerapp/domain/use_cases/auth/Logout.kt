package com.example.gamerapp.domain.use_cases.auth

import com.example.gamerapp.domain.repository.AuthRespository
import javax.inject.Inject

class Logout @Inject constructor(private val repository: AuthRespository) {
    operator fun invoke() = repository.logout()
}