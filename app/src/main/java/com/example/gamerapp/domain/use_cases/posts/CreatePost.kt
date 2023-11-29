package com.example.gamerapp.domain.use_cases.posts

import com.example.gamerapp.domain.model.Post
import com.example.gamerapp.domain.repository.PostsRepository
import java.io.File
import javax.inject.Inject

class CreatePost @Inject constructor(
    private val repository: PostsRepository
) {
    suspend operator fun invoke(post: Post, file: File) = repository.create(post, file)
}