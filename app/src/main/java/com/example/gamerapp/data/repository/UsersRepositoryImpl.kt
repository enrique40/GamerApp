package com.example.gamerapp.data.repository

import com.example.gamerapp.domain.model.Response
import com.example.gamerapp.domain.model.User
import com.example.gamerapp.domain.repository.UsersRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(private val usersRef: CollectionReference) : UsersRepository {
    override suspend fun create(user: User): Response<Boolean> {
        return try {
            user.password = ""
            usersRef.document(user.id).set(user).await()
            Response.Sucess(true)
        }catch (e: Exception){
           e.printStackTrace()
           Response.Failure(e)
        }
    }

    override suspend fun update(user: User): Response<Boolean> {
        return try {
            val map: MutableMap<String, Any> = HashMap()
            map["username"] = user.username
            map["image"] = user.image
            usersRef.document(user.id).update(map).await()
            Response.Sucess(true)
        }catch (e: Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override fun getUserById(id: String): Flow<User> = callbackFlow {
        val snapshotListener = usersRef.document(id).addSnapshotListener { snapshot, e ->
            val user = snapshot?.toObject(User::class.java) ?: User()
            trySend(user)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }
}