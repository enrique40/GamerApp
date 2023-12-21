package com.example.gamerapp.presentation.screens.update_post

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamerapp.R
import com.example.gamerapp.domain.model.Post
import com.example.gamerapp.domain.model.Response
import com.example.gamerapp.domain.use_cases.auth.AuthUseCases
import com.example.gamerapp.domain.use_cases.posts.PostsUseCases
import com.example.gamerapp.presentation.utils.ComposeFileProvider
import com.example.gamerapp.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class UpdatePostViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val savedStateHandle: SavedStateHandle,
    private val postsUseCases: PostsUseCases,
    private val authUseCases: AuthUseCases
): ViewModel(){

    var state by mutableStateOf(UpdatePostState())

    //File
    private var file: File? = null

    val resultingActivityHandler = ResultingActivityHandler()

    //ARGUMENTS
    val data = savedStateHandle.get<String>("post")
    val post = Post.fromJson(data?: "")
    //RESPONSE
    var updatePostResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    //USER SESSION
    private val currentUser = authUseCases.getCurrentUser()

    val radioOptions = listOf(
        CategoryRadioButtom("PC", R.drawable.icon_pc),
        CategoryRadioButtom("PS4", R.drawable.icon_ps4),
        CategoryRadioButtom("XBOX", R.drawable.icon_xbox),
        CategoryRadioButtom("NINTENDO", R.drawable.icon_nintendo),
        CategoryRadioButtom("MOBIL", R.drawable.icon_mobile)
    )

    init {
        state= state.copy(
            name = post.name,
            description = post.description,
            image = post.image,
            category = post.category,
        )
    }

    private fun updatePost(post: Post) = viewModelScope.launch {
        updatePostResponse = Response.Loading
        val result = postsUseCases.updatePost(post, file)
        updatePostResponse = result
    }

    fun onUpdatePost() {
        val post = Post(
            id = post.id,
            name = state.name,
            description = state.description,
            category = state.category,
            image = state.image,
            idUser = currentUser?.uid ?: "",
        )
       updatePost(post)
    }
    fun pickImage() = viewModelScope.launch {
        val result = resultingActivityHandler.getContent("image/*")
        if (result != null) {
            file = ComposeFileProvider.createFileFromUri(context, result)
            state = state.copy(image = result.toString())
        }
    }

    fun takePhoto() = viewModelScope.launch {
        val result = resultingActivityHandler.takePicturePreview()
        if (result != null) {
            state = state.copy(image = ComposeFileProvider.getPathFromBitmap(context, result))
            file = File(state.image)
        }
    }

    fun clearForm() {
       // state = state.copy(name = "", category = "", description = "", image = "")
        updatePostResponse = null

    }

    fun onNameInput(name: String) {
        state = state.copy(name = name)
    }
    fun onCategoryInput(category: String) {
        state = state.copy(category = category)
    }
    fun onDescriptionInput(description: String) {
        state = state.copy(description = description)
    }
    fun onImageInput(image: String) {
        state = state.copy(image = image)
    }


}

data class CategoryRadioButtom(
    var category: String,
    var image: Int
)