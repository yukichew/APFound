package com.example.apfound.ui.widgets

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.apfound.data.BottomBarItem
import com.example.apfound.ui.theme.galleryColor

@Composable
fun CustomBottomBar(navController: NavController){
  val items = listOf(
    BottomBarItem.Home,
    BottomBarItem.Search,
    BottomBarItem.AddPost,
    BottomBarItem.History,
    BottomBarItem.Profile
  )

  NavigationBar(
    containerColor = galleryColor
  ) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    items.forEach() { item ->
      NavigationBarItem(
        selected = currentRoute == item.route,
        onClick = {
          navController.navigate(item.route) {
            popUpTo(navController.graph.findStartDestination().id) {
              saveState = true
            }
            launchSingleTop = true
            restoreState = true
          }
        },
        icon = { Icon((item.icon), contentDescription = null) },
      )
    }
  }
}

@Preview
@Composable
fun CustomBottomBarPreview() {
  val navController: NavHostController = rememberNavController()
  CustomBottomBar(navController = navController)
}
