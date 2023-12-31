package com.example.gamerapp.domain.repository

import com.example.gamerapp.domain.model.Post
import com.example.gamerapp.domain.model.Response
import kotlinx.coroutines.flow.Flow
import java.io.File

interface PostsRepository {

    //TODO cuando se utiliza flow no se necesita utilizar suspend
    //TODO Listar los posts
    fun getPosts(): Flow<Response<List<Post>>>

    //TODO Listar my posts
    fun getPostsByUserId(idUsuer: String): Flow<Response<List<Post>>>
    suspend fun create(post: Post, file: File): Response<Boolean>
    suspend fun update(post: Post, file: File?): Response<Boolean>
    suspend fun delete(idPost: String): Response<Boolean>
    suspend fun like(idPost: String, idUsuer: String): Response<Boolean>
    suspend fun deleLike(idPost: String, idUsuer: String): Response<Boolean>
}