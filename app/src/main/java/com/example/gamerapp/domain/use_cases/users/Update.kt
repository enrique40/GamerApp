package com.example.gamerapp.domain.use_cases.users

import com.example.gamerapp.domain.model.User
import com.example.gamerapp.domain.repository.UsersRepository
import javax.inject.Inject

class Update @Inject constructor(private val repository: UsersRepository) {
    suspend operator fun invoke(user: User) = repository.update(user)
}