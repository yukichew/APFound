package com.example.apfound.ui.widgets

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomHeader(
  title: String,
  navBack: () -> Unit
) {
  CenterAlignedTopAppBar(
    title = {
      Text(
        text = title,
        style = TextStyle(
          fontSize = 22.sp,
          fontWeight = FontWeight(700)
        )
      )
    },
    navigationIcon = {
      CustomIconButton(
        onClick = navBack,
        icon = Icons.Filled.ArrowBack
      )
    }
  )
}

@Preview(showBackground = true)
@Composable
fun CustomHeaderPreview() {
  CustomHeader(title = "Login") {

  }
}
