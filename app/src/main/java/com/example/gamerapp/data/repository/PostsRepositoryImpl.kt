package com.example.gamerapp.data.repository

import android.net.Uri
import com.example.gamerapp.core.Constants.POSTS
import com.example.gamerapp.domain.model.Post
import com.example.gamerapp.domain.model.Response
import com.example.gamerapp.domain.repository.PostsRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import javax.inject.Named

class PostsRepositoryImpl @Inject constructor(
    @Named(POSTS) private val postsRef: CollectionReference,
    @Named(POSTS) private val storagePostRef: StorageReference,
): PostsRepository {
    override suspend fun create(post: Post, file: File): Response<Boolean> {
       return try {
            //IMAGE
            val fromFile = Uri.fromFile(file)
            val ref = storagePostRef.child(file.name)
            val uploadTask = ref.putFile(fromFile).await()
            val url = ref.downloadUrl.await()

            //DATA
            post.image = url.toString()
            postsRef.add(post).await()
            Response.Sucess(true)
        }catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }
}