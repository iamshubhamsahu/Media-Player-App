package com.example.mediaplayer.android.navigation.nav_graph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mediaplayer.android.MainScreen
import com.example.mediaplayer.android.navigation.BottomBarNav
import com.example.mediaplayer.android.navigation.SignUpAndLoginNav
import com.example.mediaplayer.android.view.signup_and_login.SignUp

@Composable
fun SetUpNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main_screen") {
        composable(route = "main_screen") {
            MainScreen(navController = navController)
        }
        SignUpAndLoginNav(navController)
        BottomBarNav(navController)


    }
}