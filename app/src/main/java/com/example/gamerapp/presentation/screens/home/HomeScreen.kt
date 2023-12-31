package com.example.gamerapp.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gamerapp.presentation.navigation.HomeBottomBarScreen
import com.example.gamerapp.presentation.navigation.HomeBottonBarNavGraph
import com.example.gamerapp.presentation.ui.theme.Red500

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ){
        HomeBottonBarNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screen = listOf(
        HomeBottomBarScreen.Posts,
        HomeBottomBarScreen.MyPosts,
        HomeBottomBarScreen.Profile,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = screen.any { it.route == currentDestination?.route }

    if (bottomBarDestination) {

        NavigationBar (
            modifier = Modifier.clip(RoundedCornerShape(topStartPercent = 28, topEndPercent = 28)),
            containerColor = Red500,

        ) {

            screen.forEach { screen ->
                AddItem(
                    screen = screen,
                    currenDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
    
}

@Composable
fun RowScope.AddItem(
    screen: HomeBottomBarScreen,
    currenDestination: NavDestination?,
    navController: NavHostController

) {
    NavigationBarItem(
        label = {
                Text(text = screen.title)
        },
        icon = {
               Icon(
                   imageVector = screen.icon,
                   contentDescription = "Navigation icon"
                )
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = Color.Gray,
            unselectedIconColor = Color.Gray,
            selectedTextColor = Color.Gray,
            unselectedTextColor = Color.Gray,
            indicatorColor = Color.Gray
        ),
        selected = currenDestination?.hierarchy?.any {
           it.route == screen.route
        } == true,
        //unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
                  navController.navigate(screen.route) {
                      popUpTo(navController.graph.findStartDestination().id)
                      launchSingleTop = true
                  }
        }
    )
}