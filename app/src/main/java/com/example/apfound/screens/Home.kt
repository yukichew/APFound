package com.example.apfound.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.apfound.ui.widgets.CustomBottomBar
import com.example.apfound.ui.widgets.CustomHeaderHome

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
  navController: NavController
) {
  Scaffold(
    topBar = {
      CustomHeaderHome()
    },
    bottomBar = {
      CustomBottomBar(navController = navController)
    }
  ) { innerPadding ->
    Column(
      modifier = Modifier
        .padding(innerPadding)
    ) {

    }
  }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
  val navController = rememberNavController()
  Home(navController = navController)
}
