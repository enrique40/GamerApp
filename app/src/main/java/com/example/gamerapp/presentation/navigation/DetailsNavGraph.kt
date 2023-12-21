package com.example.gamerapp.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.gamerapp.presentation.screens.detail_post.DetailPostScreen
import com.example.gamerapp.presentation.screens.new_post.NewPostScreen
import com.example.gamerapp.presentation.screens.profile_update.ProfileUpdateScreen
import com.example.gamerapp.presentation.screens.update_post.UpdatePostScreen

fun NavGraphBuilder.detailsNavGraph (navController: NavHostController) {

    navigation(
        route = Graph.DETAILS,
        startDestination = DetailsScreen.ProfileUpdate.route
    ) {

        composable(route = DetailsScreen.NewPost.route) {
            NewPostScreen(navController = navController)
        }
        composable(
            route = DetailsScreen.ProfileUpdate.route,
            arguments = listOf(navArgument("user"){
                type = NavType.StringType
            })
        ){
            it.arguments?.getString("user")?.let { _ ->
                ProfileUpdateScreen(navController)
            }
        }
        composable(
            route = DetailsScreen.DetailPost.route,
            arguments = listOf(navArgument("post"){
                type = NavType.StringType
            })
        ){
            it.arguments?.getString("post")?.let { _ ->
                DetailPostScreen(navController =  navController)
            }
        }
        composable(
            route = DetailsScreen.UpdatePost.route,
            arguments = listOf(navArgument("post"){
                type = NavType.StringType
            })
        ){
            it.arguments?.getString("post")?.let { _ ->
                UpdatePostScreen(navController =  navController)
            }
        }

    }
}


sealed class DetailsScreen(val route: String) {
    object NewPost: DetailsScreen(route = "posts/new")
    object ProfileUpdate: DetailsScreen(route = "profile/update/{user}") {
        fun passUser(user: String) = "profile/update/$user"
    }
    object DetailPost: DetailsScreen( "posts/detail/{post}") {
        fun passPost(post: String) = "posts/detail/$post"
    }
    object UpdatePost: DetailsScreen( "posts/update/{post}") {
        fun passPost(post: String) = "posts/update/$post"
    }
}