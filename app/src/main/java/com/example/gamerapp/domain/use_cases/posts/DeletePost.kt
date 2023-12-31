package com.example.gamerapp.domain.use_cases.posts

import com.example.gamerapp.domain.model.Post
import com.example.gamerapp.domain.repository.PostsRepository
import java.io.File
import javax.inject.Inject

class DeletePost @Inject constructor(
    private val repository: PostsRepository
) {
    suspend operator fun invoke(idPost: String) = repository.delete(idPost)
}