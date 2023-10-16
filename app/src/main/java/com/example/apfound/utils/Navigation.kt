package com.example.apfound.utils

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apfound.screens.home.Home
import com.example.apfound.screens.Onboarding
import com.example.apfound.screens.SplashScreen
import com.example.apfound.screens.history.History
import com.example.apfound.screens.login.Login
import com.example.apfound.screens.post.AddPost
import com.example.apfound.screens.profile.EditProfile
import com.example.apfound.screens.profile.Profile
import com.example.apfound.screens.search.Search
import com.example.apfound.screens.signup.SignUp
import com.google.firebase.auth.FirebaseUser

@Composable
fun Navigation(currentUser: FirebaseUser?) {
  val navController = rememberNavController()
  val startScreen: String =
    if (currentUser == null) NavigationRoutes.onboarding.route else NavigationRoutes.home.route

  NavHost(navController = navController, startDestination = NavigationRoutes.splashScreen.route) {

    // Splash Screen
    composable(NavigationRoutes.splashScreen.route) {
      SplashScreen(navController = navController, startUpRoute = startScreen)
    }

    // Onboarding Page
    composable(NavigationRoutes.onboarding.route) {
      Onboarding(navController = navController)
    }

    // Login Page
    composable(NavigationRoutes.login.route) {
      Login(navController = navController)
    }

    // Sign Up Page
    composable(NavigationRoutes.signUp.route) {
      SignUp(navController = navController)
    }

    // Home Page
    composable(NavigationRoutes.home.route) {
      Home(navController = navController)
    }

    // Profile Page
    composable(NavigationRoutes.profile.route) {
      Profile(navController = navController)
    }

    // Edit Profile Page
    composable(NavigationRoutes.editProfile.route) {
      EditProfile(navController = navController)
    }

    // Search Page
    composable(NavigationRoutes.search.route) {
      Search(navController = navController)
    }

    // Add Post
    composable(NavigationRoutes.addPost.route) {
      AddPost(navController = navController)
    }

    // History Page
    composable(NavigationRoutes.history.route) {
      History(navController = navController)
    }
  }
}
