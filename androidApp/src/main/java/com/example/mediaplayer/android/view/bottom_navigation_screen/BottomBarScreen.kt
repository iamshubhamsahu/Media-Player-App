package com.example.mediaplayer.android.view.bottom_navigation_screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Video : BottomBarScreen(
        route = "video",
        title = "VIDEO",
        icon = Icons.Default.Home
    )
    object Audio : BottomBarScreen(
        route = "audio",
        title = "AUDIO",
        icon = Icons.Default.Home
    )
    object Browse : BottomBarScreen(
        route = "browse",
        title = "BROWSE",
        icon = Icons.Default.Home
    )
    object Playlists : BottomBarScreen(
        route = "playlists",
        title = "PLAYLISTS",
        icon = Icons.Default.Home
    )
    object More : BottomBarScreen(
        route = "more",
        title = "MORE",
        icon = Icons.Default.Home
    )
}