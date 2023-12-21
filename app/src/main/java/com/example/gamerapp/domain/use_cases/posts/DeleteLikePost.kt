package com.example.gamerapp.domain.use_cases.posts

import com.example.gamerapp.domain.repository.PostsRepository
import javax.inject.Inject

class DeleteLikePost @Inject constructor(
    private val repository: PostsRepository
) {
    suspend operator fun invoke(idPost: String, idUser: String) = repository.deleLike(idPost, idUser)
}