package com.example.mediaplayer.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mediaplayer.android.view.bottom_navigation_screen.Audio
import com.example.mediaplayer.android.view.bottom_navigation_screen.BottomBarScreen
import com.example.mediaplayer.android.view.bottom_navigation_screen.Browse
import com.example.mediaplayer.android.view.bottom_navigation_screen.More
import com.example.mediaplayer.android.view.bottom_navigation_screen.Playlists
import com.example.mediaplayer.android.view.bottom_navigation_screen.Video

@Composable
fun BottomNav(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomBarScreen.Video.route) {
        composable(route = BottomBarScreen.Video.route) {
            Video()
        }
        composable(route = BottomBarScreen.Audio.route) {
            Audio()
        }
        composable(route = BottomBarScreen.Browse.route) {
            Browse()
        }
        composable(route = BottomBarScreen.Playlists.route) {
            Playlists()
        }
        composable(route = BottomBarScreen.More.route) {
            More()
        }
    }
}