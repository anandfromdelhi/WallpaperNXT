package com.example.wallpapernxt.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.wallpapernxt.screens.TestScreen
import com.example.wallpapernxt.screens.mainScreen.MainScreen
import com.example.wallpapernxt.screens.photoGalleryScreen.PhotoGalleryScreen
import com.example.wallpapernxt.screens.photoGalleryScreen.PhotoGalleryScreenViewModel
import com.example.wallpapernxt.screens.photoViewScreen.PhotoViewScreen
import com.example.wallpapernxt.screens.photoViewScreen.PhotoViewScreenViewModel

@Composable
fun NavigationScreen() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = PhotosAppScreens.MainScreen.name
    ) {

        composable(route = PhotosAppScreens.MainScreen.name) {
            MainScreen(navController)
        }

        val route = PhotosAppScreens.PhotoGalleryScreen.name
        composable(
            route = "$route/{query}",
            arguments = listOf(navArgument(name = "query") {
                type = NavType.StringType
              }
            )
        ) { navBack ->
            navBack.arguments?.getString("query").let { query ->
                val photoGalleryScreenViewModel = hiltViewModel<PhotoGalleryScreenViewModel>()
                PhotoGalleryScreen(
                    navController = navController,
                    photoGalleryScreenViewModel,
                    query = query
                )
            }
        }
        composable(route = PhotosAppScreens.TestScreen.name) {
            TestScreen()
        }
        val photoRoute = PhotosAppScreens.PhotoViewScreen.name
        composable(
            route = "$photoRoute/{path}",
            arguments = listOf(navArgument(name = "path") {
                type = NavType.StringType
            })

        ) { navBack ->
            navBack.arguments?.getString("path").let { path ->
                val photoViewScreenViewModel = hiltViewModel<PhotoViewScreenViewModel>()
                PhotoViewScreen(
                    navController = navController,
                    photoViewScreenViewModel,
                    path = path
                )
            }

        }
    }
}