package com.example.apfound.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.apfound.R
import com.example.apfound.ui.theme.PoppinsFamily
import com.example.apfound.ui.theme.whiteColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomHeader(
  title: String,
  navController: NavController
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
        onClick = { navController.navigateUp() },
        icon = Icons.Filled.ArrowBack
      )
    }
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomHeaderBasic(
  navController: NavController
) {
  TopAppBar(
    colors = TopAppBarDefaults.largeTopAppBarColors(
      containerColor = whiteColor
    ),
    title = {
      Box(
        modifier = Modifier
          .fillMaxSize(),
        contentAlignment = Alignment.CenterEnd
      ) {
        Image(
          painter = painterResource(id = R.drawable.logo),
          contentDescription = stringResource(id = R.string.logo),
          modifier = Modifier
            .padding(12.dp)
            .height(35.dp)
        )
      }
    },
    navigationIcon = {
      CustomIconButton(
        onClick = { navController.navigateUp() },
        icon = Icons.Filled.ArrowBack
      )
    },
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomHeaderCommon(
  title: String
) {
  TopAppBar(
    colors = TopAppBarDefaults.largeTopAppBarColors(
      containerColor = whiteColor
    ),
    title = {
      Box(
        modifier = Modifier
          .fillMaxSize(),
        contentAlignment = Alignment.CenterEnd
      ) {
        Text(
          text = title,
          style = TextStyle(
            fontSize = 15.sp,
            letterSpacing = 0.1.sp,
            fontFamily = PoppinsFamily,
          )
        )
      }
    },
    navigationIcon = {
      Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = stringResource(id = R.string.logo),
        modifier = Modifier
          .padding(12.dp)
          .height(35.dp)
      )
    },
  )
}

@Preview(showBackground = true)
@Composable
fun CustomHeaderPreview() {
  CustomHeaderCommon("Yuki")
}
