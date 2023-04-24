package com.example.mediaplayer.android.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.mediaplayer.android.view.bottom_navigation.Audio
import com.example.mediaplayer.android.view.bottom_navigation.Video


fun NavGraphBuilder.BottomBarNav(navController: NavHostController) {
    navigation(startDestination = "video", route = "LetterB") {
        composable(route = "video_screen") {
            Video(navController = navController)
        }
        composable(route = "audio_screen") {
            Audio(navController = navController)
        }
    }
}


