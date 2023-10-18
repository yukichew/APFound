package com.example.apfound.utils

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.apfound.screens.home.Home
import com.example.apfound.screens.Onboarding
import com.example.apfound.screens.SplashScreen
import com.example.apfound.screens.history.History
import com.example.apfound.screens.login.Login
import com.example.apfound.screens.addPost.AddPost
import com.example.apfound.screens.managePost.EditPost
import com.example.apfound.screens.post.ItemDetail
import com.example.apfound.screens.profile.ChangePassword
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

    // Change Password Page
    composable(NavigationRoutes.changePassword.route) {
      ChangePassword(navController = navController)
    }

    // Search Page
    composable(NavigationRoutes.search.route) {
      Search(navController = navController)
    }

    // Search Page
    composable(
      route = NavigationRoutes.search.route + "?filter={category}",
      arguments = listOf(navArgument("category") { type = NavType.StringType }
      )) { backStackEntry ->
      backStackEntry.arguments?.getString("category")?.let {
        Search(navController = navController, initialFilter = it)
      }
    }

    // Add Post
    composable(NavigationRoutes.addPost.route) {
      AddPost(navController = navController)
    }

    // Edit Post
    composable(
      route = NavigationRoutes.editPost.route + "/{itemId}",
      arguments = listOf(navArgument("itemId") { type = NavType.StringType }
      )) { backStackEntry ->
      backStackEntry.arguments?.getString("itemId")?.let {
        EditPost(navController = navController)
      }
    }

    // History Page
    composable(NavigationRoutes.history.route) {
      History(navController = navController)
    }

    // Item Detail Page
    composable(
      route = NavigationRoutes.itemDetail.route + "/{itemId}",
      arguments = listOf(navArgument("itemId") { type = NavType.StringType }
      )) { backStackEntry ->
      backStackEntry.arguments?.getString("itemId")?.let {
        ItemDetail(navController = navController)
      }
    }

  }
}

