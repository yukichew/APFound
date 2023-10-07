package com.example.apfound

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apfound.screens.Login
import com.example.apfound.screens.SplashScreen
import com.example.apfound.ui.theme.APFoundTheme
import com.example.apfound.utils.NavigationRoutes

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      APFoundTheme {
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colorScheme.background
        ) {
//          Navigation()
          Login(
            navBack = {}
          )
        }
      }
    }
  }
}

@Composable
fun APFound() {
}

@Composable
fun Navigation() {
  val navController = rememberNavController()

  NavHost(navController = navController, startDestination = NavigationRoutes.splashScreen) {
//    Splash Screen
    composable(route = NavigationRoutes.splashScreen) {
      SplashScreen(navController = navController)
    }

//    Login
    composable(route = NavigationRoutes.login) {
      Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
      ) {
        Text(text = "Login", color = Color.White)
      }
    }
  }
}
