package com.example.apfound.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.apfound.ui.widgets.CustomHeader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(
  navBack: () -> Unit,
) {
  Scaffold(
    topBar = {
      CustomHeader(title = "Login", navBack = navBack)
    }
  ) {
      innerPadding ->
    Column(
      modifier = Modifier
        .padding(innerPadding),
      verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
      Text(
        modifier = Modifier.padding(8.dp),
        text = "This is an example of a scaffold. It uses the Scaffold"
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
  Login(
    navBack = {}
  )
}
