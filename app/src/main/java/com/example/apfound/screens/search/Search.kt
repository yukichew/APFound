package com.example.apfound.screens.search

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.apfound.R
import com.example.apfound.screens.post.ItemDetailViewModel
import com.example.apfound.ui.theme.PoppinsFamily
import com.example.apfound.ui.theme.whiteColor
import com.example.apfound.ui.widgets.CustomBottomBar
import com.example.apfound.ui.widgets.CustomCard
import com.example.apfound.ui.widgets.CustomHeaderBasic
import com.example.apfound.ui.widgets.CustomHeaderCommon
import com.example.apfound.ui.widgets.CustomOutlinedButton
import com.example.apfound.ui.widgets.CustomTextField
import com.example.apfound.utils.NavigationRoutes
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(
  navController: NavController,
  vm: SearchViewModel = viewModel(),
  initialFilter: String? = null
) {
  val searchResults by vm.searchResult.collectAsState()

  var isLoading by remember { mutableStateOf(false) }
  val scope = rememberCoroutineScope()

  var lost = stringResource(id = R.string.lost_item)
  var found = stringResource(id = R.string.found_item)

  var selectedFilter = initialFilter ?: lost

  val lostSelected = selectedFilter == lost
  val foundSelected = selectedFilter == found

  Scaffold(
    topBar = {
      CustomHeaderCommon(vm.name)
    },
    bottomBar = {
      CustomBottomBar(navController = navController)
    },
    containerColor = whiteColor
  ) { innerPadding ->
    LazyColumn(
      modifier = Modifier
        .padding(innerPadding),
      verticalArrangement = Arrangement.spacedBy(space = 8.dp),
      contentPadding = PaddingValues(start = 25.dp, end = 25.dp, bottom = 25.dp)
    ) {
      item {
        Column(
          verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          CustomTextField(
            value = vm.keyword,
            placeholder = stringResource(id = R.string.search),
            onValueChange = {
              vm.keyword = it
            },
            leadingIcon = {
              IconButton(
                onClick = {
                  isLoading = true
                  scope.launch {
                    vm.performSearchbyKeyword()
                    isLoading = false
                  }
                }
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
            CustomOutlinedButton(
              text = lost,
              onClick = {
                isLoading = true
                scope.launch {
                  vm.performSearch(lost)
                  isLoading = false
                }
                isLoading = false
                selectedFilter = lost
              },
              modifier = Modifier.weight(1f),
              selected = lostSelected
            )

            CustomOutlinedButton(
              text = found,
              onClick = {
                scope.launch {
                  isLoading = true
                  vm.performSearch(found)
                  isLoading = false
                }
                selectedFilter = found
              },
              modifier = Modifier.weight(1f),
              selected = foundSelected
            )
          }

          Text(
            text = when (selectedFilter) {
              lost -> lost
              found -> found
              else -> "Search Result"
            },
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.16.sp,
            fontFamily = PoppinsFamily,
            modifier = Modifier.padding(start = 5.dp)
          )
        }

        searchResults.forEach { item ->
          CustomCard(
            image = Uri.parse(item.image),
            title = item.name,
            onClick = {
              navController.navigate(NavigationRoutes.itemDetail.route + "/${item.itemId}")
            }
          )
        }

      }
    }
  }
}


@Preview(showBackground = true)
@Composable
fun SearchPreview() {
}
