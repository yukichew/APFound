package com.example.apfound.screens.history

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.apfound.R
import com.example.apfound.ui.theme.PoppinsFamily
import com.example.apfound.ui.widgets.CustomBottomBar
import com.example.apfound.ui.widgets.CustomHeaderBasic
import com.example.apfound.ui.widgets.CustomHorizontalCard
import kotlin.reflect.KProperty

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun History(
  navController: NavController,
  vm: MyPostViewModel = viewModel()
) {

  val items by vm.itemList.collectAsState()

  Scaffold(
    topBar = {
      CustomHeaderBasic(navController = navController)
    },

    bottomBar = {
      CustomBottomBar(navController = navController)
    }
  ) { innerPadding ->
    LazyColumn(
      modifier = Modifier
        .padding(innerPadding),
      verticalArrangement = Arrangement.spacedBy(space = 8.dp),
      contentPadding = PaddingValues(all = 25.dp)
    ) {
      item {
        Text(
          text = stringResource(id = R.string.my_posts),
          fontSize = 32.sp,
          fontWeight = FontWeight.Bold,
          letterSpacing = 0.16.sp,
          fontFamily = PoppinsFamily,
          modifier = Modifier.padding(start = 5.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))
      }

      items.forEach { item ->
        item {
          CustomHorizontalCard(
            image = Uri.parse(item.image),
            title = item.name,
            onEditClick = {
              // Handle edit click
            }
          ) {
            // Handle delete click
          }
        }
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun HistoryPreview() {
  val navController = rememberNavController()
  History(navController = navController)
}
