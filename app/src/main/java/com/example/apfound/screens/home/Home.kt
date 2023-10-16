package com.example.apfound.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.apfound.R
import com.example.apfound.ui.theme.PoppinsFamily
import com.example.apfound.ui.theme.galleryColor
import com.example.apfound.ui.theme.primaryColor
import com.example.apfound.ui.theme.whiteColor
import com.example.apfound.ui.widgets.CustomBottomBar
import com.example.apfound.ui.widgets.CustomButton
import com.example.apfound.ui.widgets.CustomHeaderHome
import com.example.apfound.ui.widgets.CustomOutlinedButton
import com.example.apfound.ui.widgets.CustomTextIconButton

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
      Box(
        modifier = Modifier
          .background(galleryColor)
      ) {
        Column(
          horizontalAlignment = Alignment.CenterHorizontally,
          modifier = Modifier
            .padding(start = 30.dp, end = 30.dp, bottom = 30.dp, top = 25.dp)
        ) {
          Text(
            text = stringResource(id = R.string.welcome_message),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 48.sp,
            lineHeight = 50.sp,
            letterSpacing = 0.24.sp,
            fontFamily = PoppinsFamily
          )
          Text(
            text = stringResource(id = R.string.slogan),
            fontSize = 15.sp,
            lineHeight = 18.sp,
            fontWeight = FontWeight(300),
          )
        }
      }

      Column(
        modifier = Modifier
          .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
      ) {
        CustomTextIconButton(
          onClick = { /*TODO*/ },
          text = stringResource(id = R.string.lost_item),
          icon = Icons.Default.Check,
          backgroundColor = primaryColor,
          textColor = whiteColor,
          iconColor = whiteColor
        )

        CustomTextIconButton(
          onClick = { /*TODO*/ },
          text = stringResource(id = R.string.found_item),
          icon = Icons.Default.Check,
        )
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
  val navController = rememberNavController()
  Home(navController = navController)
}
