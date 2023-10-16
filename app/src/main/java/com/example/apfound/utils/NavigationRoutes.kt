package com.example.apfound.utils

sealed class NavigationRoutes(val route: String) {
  object splashScreen : NavigationRoutes("splashscreen")
  object onboarding : NavigationRoutes("onboarding")
  object login : NavigationRoutes("login")
  object signUp : NavigationRoutes("signUp")
  object home : NavigationRoutes("home")
  object profile : NavigationRoutes("profile")
  object editProfile : NavigationRoutes("editProfile")
  object search : NavigationRoutes("search")
  object addPost : NavigationRoutes("addPost")
  object history : NavigationRoutes("history")
}
