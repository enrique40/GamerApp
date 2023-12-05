package com.example.gamerapp.data.repository

import android.net.Uri
import com.example.gamerapp.core.Constants.POSTS
import com.example.gamerapp.core.Constants.USERS
import com.example.gamerapp.domain.model.Post
import com.example.gamerapp.domain.model.Response
import com.example.gamerapp.domain.model.User
import com.example.gamerapp.domain.repository.PostsRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import javax.inject.Named

class PostsRepositoryImpl @Inject constructor(
    @Named(POSTS) private val postsRef: CollectionReference,
    @Named(USERS) private val usersRef: CollectionReference,
    @Named(POSTS) private val storagePostRef: StorageReference,
): PostsRepository {
    override fun getPosts(): Flow<Response<List<Post>>> = callbackFlow{
        val snapshotListener = postsRef.addSnapshotListener { snapshot, e ->
            GlobalScope.launch(IO) {
                val postResponse = if (snapshot != null){
                    val post = snapshot.toObjects(Post::class.java)
                    val idUserArray = ArrayList<String>()

                    post.forEach { post->
                        idUserArray.add(post.idUser)
                    }

                    val idUserList = idUserArray.toSet().toList() //ELEMENTOS SIN REPETIR

                    idUserList.map { id->
                        async {
                            val user = usersRef.document(id).get().await().toObject(User::class.java)
                            post.forEach { post ->
                                if (post.idUser == id){
                                    post.user = user
                                }
                            }
                        }
                    }.forEach {data ->
                        data.await()
                    }
                    Response.Sucess(post)

                }
                else {
                    Response.Failure(e)
                }
                trySend(postResponse)
            }
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun getPostsByUserId(idUsuer: String): Flow<Response<List<Post>>> = callbackFlow {
        val snapshotListener = postsRef.whereEqualTo("idUser", idUsuer).addSnapshotListener { snapshot, e ->

            val postResponse = if (snapshot != null){
                val post = snapshot.toObjects(Post::class.java)

                Response.Sucess(post)

            }
            else {
                Response.Failure(e)
            }
            trySend(postResponse)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

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