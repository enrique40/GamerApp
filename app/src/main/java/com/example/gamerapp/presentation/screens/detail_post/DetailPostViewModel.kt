package com.example.gamerapp.presentation.screens.detail_post

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.gamerapp.domain.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailPostViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    // TODO para obtener el post que se esta enviando de la pantalla anterior
    val data = savedStateHandle.get<String>("post")
    val post = Post.fromJson(data ?: "")


}