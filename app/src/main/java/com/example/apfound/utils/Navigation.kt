package com.example.apfound.utils

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apfound.screens.Onboarding
import com.example.apfound.screens.SplashScreen
import com.example.apfound.screens.login.Login
import com.google.firebase.auth.FirebaseUser

@Composable
fun Navigation(currentUser: FirebaseUser?) {
  val navController = rememberNavController()
  val startScreen: String =
    if (currentUser == null) NavigationRoutes.onboarding.route else NavigationRoutes.home.route

  NavHost(navController = navController, startDestination = NavigationRoutes.splashScreen.route) {

    // Splash Screen
    composable(NavigationRoutes.splashScreen.route) {
      SplashScreen(navController = navController)
    }

    // Onboarding Page
    composable(NavigationRoutes.onboarding.route) {
      Onboarding(navController = navController)
    }

    // Login Page
    composable(NavigationRoutes.login.route) {
      Login(navController = navController)
    }

    // Home Page
    composable(NavigationRoutes.home.route) {

    }

    // Add Post
    composable(NavigationRoutes.addPost.route) {

    }

    // History Page
    composable(NavigationRoutes.history.route) {

    }
  }
}
