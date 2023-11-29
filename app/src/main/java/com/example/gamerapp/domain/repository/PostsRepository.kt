package com.example.gamerapp.domain.repository

import com.example.gamerapp.domain.model.Post
import com.example.gamerapp.domain.model.Response
import java.io.File

interface PostsRepository {
    suspend fun create(post: Post, file: File): Response<Boolean>
}