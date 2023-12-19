package com.example.gamerapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gamerapp.presentation.screens.my_posts.MyPostsScreen
import com.example.gamerapp.presentation.screens.posts.PostsScreen
import com.example.gamerapp.presentation.screens.profile.ProfileScreen

@Composable
fun HomeBottonBarNavGraph(navController: NavHostController,  darkMode: MutableState<Boolean>) {
    //startDestination para identificar la ruta inicial
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = HomeBottomBarScreen.Posts.route
    ) {

        composable(route = HomeBottomBarScreen.Posts.route){
            PostsScreen(navController, darkMode)
        }
        composable(route = HomeBottomBarScreen.MyPosts.route){
            MyPostsScreen(navController, darkMode)
        }
        composable(route = HomeBottomBarScreen.Profile.route){
            ProfileScreen(navController, darkMode)
        }

        detailsNavGraph(navController)
    }
}

sealed class HomeBottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Posts: HomeBottomBarScreen(
        route = "posts",
        title = "Posts",
        icon = Icons.Default.List
    )

    object MyPosts: HomeBottomBarScreen(
        route = "my_posts",
        title = "Mis posts",
        icon = Icons.Outlined.List
    )

    object Profile: HomeBottomBarScreen(
        route = "profile",
        title = "Perfil",
        icon = Icons.Default.Person
    )
}