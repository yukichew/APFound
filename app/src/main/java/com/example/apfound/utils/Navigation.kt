package com.example.apfound.utils

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apfound.screens.Login
import com.example.apfound.screens.Onboarding
import com.example.apfound.screens.SplashScreen

@Composable
fun Navigation() {
  val navController = rememberNavController()

  NavHost(navController = navController, startDestination = NavigationRoutes.splashScreen.route) {

    // Splash Screen
    composable(NavigationRoutes.splashScreen.route) {
      SplashScreen(navController = navController)
    }

    // Onboarding Page
    composable(NavigationRoutes.onboarding.route) {
      Onboarding()
    }

    // Login Page
    composable(NavigationRoutes.login.route) {
      Login {
      }
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
