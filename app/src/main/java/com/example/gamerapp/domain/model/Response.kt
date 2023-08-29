package com.example.gamerapp.domain.model

sealed class Response <out T> {
    object Loadin: Response<Nothing>()
    data class Sucess<out T>(val data: T): Response<T>()
    data class Failure<out T>(val exception: Exception?): Response<T>()
}
