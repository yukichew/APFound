package com.example.apfound.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.apfound.ui.widgets.CustomButton
import com.example.apfound.ui.widgets.CustomCard
import com.example.apfound.ui.widgets.CustomHeaderBasic
import com.example.apfound.ui.widgets.CustomOutlinedButton
import com.example.apfound.ui.widgets.CustomTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(
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
        .padding(start = 30.dp, end = 30.dp)
    ) {
      Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        CustomTextField(
          value = "",
          placeholder = stringResource(id = R.string.search),
          onValueChange = {},
          leadingIcon = {
            IconButton(
              onClick = {}
            ) {
              Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search"
              )
            }
          },
        )

        Text(
          text = "Filter by:",
          fontSize = 12.sp,
          letterSpacing = 0.16.sp,
          modifier = Modifier.padding(start = 5.dp)
        )

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(15.dp),
        ) {
          CustomButton(
            text = stringResource(id = R.string.lost_item),
            onClick = { /*TODO*/ },
            modifier = Modifier.weight(1f)
          )

          CustomOutlinedButton(
            text = stringResource(id = R.string.found_item),
            onClick = { /*TODO*/ },
            modifier = Modifier.weight(1f)
          )
        }

        Text(
          text = stringResource(id = R.string.lost_item),
          fontSize = 32.sp,
          fontWeight = FontWeight.Bold,
          letterSpacing = 0.16.sp,
          fontFamily = PoppinsFamily,
          modifier = Modifier.padding(start = 5.dp)
        )

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
}


@Preview(showBackground = true)
@Composable
fun SearchPreview() {
  val navController = rememberNavController()
  Search(navController)
}
