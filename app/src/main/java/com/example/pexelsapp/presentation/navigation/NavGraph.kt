package com.example.pexelsapp.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.pexelsapp.presentation.bookmarks.BookmarksScreen
import com.example.pexelsapp.presentation.details.DetailsScreen
import com.example.pexelsapp.presentation.details.DetailsViewModel
import com.example.pexelsapp.presentation.home.HomeScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph() {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomBar = currentRoute in listOf(
        "home",
        "bookmarks"
    )
    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavigationBar(navController)
            }
        }
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(padding),
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it / 3 },
                    animationSpec = tween(300)
                ) + fadeIn()
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it / 3 },
                    animationSpec = tween(300)
                ) + fadeOut()
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -it / 3 },
                    animationSpec = tween(300)
                ) + fadeIn()
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { it / 3 },
                    animationSpec = tween(300)
                ) + fadeOut()
            }
        ) {

            composable("home") {
                HomeScreen(
                    onPhotoClick = { id ->
                        navController.navigate("details/$id")
                    }
                )
            }

            composable("bookmarks") {
                BookmarksScreen(
                    onPhotoClick = { id ->
                        navController.navigate("details/$id")
                    },
                    navController = navController
                )
            }

            composable(
                route = "details/{id}",
                arguments = listOf(
                    navArgument("id") { type = NavType.IntType }
                ),
                enterTransition = {
                    slideInVertically(
                        initialOffsetY = { it / 5},
                        animationSpec = tween(400)
                    ) + fadeIn()
                },
                exitTransition = {
                    slideOutVertically(
                        targetOffsetY = { it / 5 },
                        animationSpec = tween(400)
                    ) + fadeOut()
                }
            )
             { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("id") ?: 0
                val viewModel: DetailsViewModel = hiltViewModel()
                LaunchedEffect(id) { viewModel.loadPhoto(id) }

                DetailsScreen(
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() },
                    onDownload = { photo ->
                        // скачивание
                    }
                )
            }
        }
    }
}

