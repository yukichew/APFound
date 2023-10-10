package com.example.apfound.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apfound.R

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomHeaderHome() {
  CenterAlignedTopAppBar(
    title = {
      Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = stringResource(id = R.string.logo),
        contentScale = ContentScale.Fit,
        modifier = Modifier
          .padding(10.dp)
      )
    }
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomHeaderBasic(
  navBack: () -> Unit
) {
  TopAppBar(
    title = {
      Box(
        modifier = Modifier
          .fillMaxSize(),
        contentAlignment = Alignment.CenterEnd
      ) {
        Image(
          painter = painterResource(id = R.drawable.logo),
          contentDescription = stringResource(id = R.string.logo),
          contentScale = ContentScale.Fit,
          modifier = Modifier
            .padding(12.dp)
        )
      }
    },
    navigationIcon = {
      CustomIconButton(
        onClick = navBack,
        icon = Icons.Filled.ArrowBack
      )
    },
  )
}

@Preview(showBackground = true)
@Composable
fun CustomHeaderPreview() {
  CustomHeaderBasic(){}
}
