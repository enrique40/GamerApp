package com.example.gamerapp.domain.use_cases.posts

import com.example.gamerapp.domain.repository.PostsRepository
import javax.inject.Inject

class GetPosts @Inject constructor(
    private val repository: PostsRepository
) {
    operator fun invoke() = repository.getPosts()
}