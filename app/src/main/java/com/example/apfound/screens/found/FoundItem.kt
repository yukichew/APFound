package com.example.apfound.screens.found

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.apfound.ui.theme.whiteColor
import com.example.apfound.ui.widgets.CustomBottomBar
import com.example.apfound.ui.widgets.CustomCard
import com.example.apfound.ui.widgets.CustomHeaderBasic

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoundItem(
  navController: NavController
) {
  Scaffold(
    topBar = {
      CustomHeaderBasic(navController = navController)
    },
    bottomBar = {
      CustomBottomBar(navController = navController)
    },
    containerColor = whiteColor
  ) { innerPadding ->
    Column(
      modifier = Modifier
        .padding(innerPadding)
        .padding(30.dp),
    ) {
      Text(
        text = stringResource(id = R.string.found_item),
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.16.sp,
        fontFamily = PoppinsFamily,
        modifier = Modifier.padding(start = 5.dp)
      )

      Spacer(modifier = Modifier.height(10.dp))

      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp),
      ) {
        CustomCard(
          image = R.drawable.logo,
          title = "MacBook",
        )
        CustomCard(
          image = R.drawable.logo,
          title = "MacBook",
        )
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun FoundItemPreview() {
  val navController = rememberNavController()
  FoundItem(navController)
}
