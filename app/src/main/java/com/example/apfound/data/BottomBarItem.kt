package com.example.apfound.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarItem(
  val route: String,
  val icon: ImageVector
) {
  object Home : BottomBarItem("home", Icons.Outlined.Home)
  object Search : BottomBarItem("search", Icons.Default.Search)
  object AddPost : BottomBarItem("addPost", Icons.Outlined.AddCircle)
  object History : BottomBarItem("history", Icons.Default.FavoriteBorder)
  object Profile : BottomBarItem("profile", Icons.Outlined.Person)
}
