package com.example.apfound.utils

sealed class NavigationRoutes(val route: String) {
  object splashScreen : NavigationRoutes("splashscreen")
  object onboarding : NavigationRoutes("onboarding")
  object login : NavigationRoutes("login")
  object signUp : NavigationRoutes("signUp")
  object home : NavigationRoutes("home")
  object addPost : NavigationRoutes("addPost")
  object history : NavigationRoutes("history")
}
