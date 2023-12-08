package com.example.gamerapp.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
fun HomeScreen(navController: NavHostController = rememberNavController(), darkMode: MutableState<Boolean>) {
    Scaffold(
        bottomBar = { BottomBar(navController = navController)}
    ){
        HomeBottonBarNavGraph(navController = navController, darkMode = darkMode)
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

        BottomNavigation (
            modifier = Modifier.clip(RoundedCornerShape(topStartPercent = 28, topEndPercent = 28)),
            backgroundColor = Red500,

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
    BottomNavigationItem(
        label = {
                Text(text = screen.title)
        },
        icon = {
               Icon(
                   imageVector = screen.icon,
                   contentDescription = "Navigation icon"
                )
        },
        selected = currenDestination?.hierarchy?.any {
           it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
                  navController.navigate(screen.route) {
                      popUpTo(navController.graph.findStartDestination().id)
                      launchSingleTop = true
                  }
        }
    )
}