package com.example.mediaplayer.android.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.mediaplayer.android.view.signup_and_login.LogIn
import com.example.mediaplayer.android.view.signup_and_login.SignUp

fun NavGraphBuilder.SignUpAndLoginNav(navController: NavController) {
    navigation(startDestination = "signup_screen", route = "LetterA") {
        composable(route = "signup_screen") {
            SignUp(navController = navController)
        }
        composable(route = "login_screen"){
            LogIn(navController = navController)
        }
    }
}