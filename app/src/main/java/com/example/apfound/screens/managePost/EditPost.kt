package com.example.apfound.screens.managePost

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.example.apfound.ui.widgets.CustomButton
import com.example.apfound.ui.widgets.CustomDialog
import com.example.apfound.ui.widgets.CustomHeaderBasic
import com.example.apfound.ui.widgets.CustomOutlinedButton
import com.example.apfound.ui.widgets.CustomRadio
import com.example.apfound.ui.widgets.CustomTextField
import com.example.apfound.utils.NavigationRoutes
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPost(
  navController: NavController,
  vm: EditPostViewModel = viewModel(),
) {
  val itemCategoryOptions =
    listOf(stringResource(id = R.string.lost_item), stringResource(id = R.string.found_item))
  var nameError by rememberSaveable { mutableStateOf(false) }
  var descError by rememberSaveable { mutableStateOf(false) }
  var categoryError by rememberSaveable { mutableStateOf(false) }

  var showFailedDialog by remember { mutableStateOf(false) }
  var showSuccessDialog by remember { mutableStateOf(false) }

  var isLoading by remember { mutableStateOf(false) }
  val scope = rememberCoroutineScope()

  val imagePicker = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.GetContent()
  ) { uri: Uri? ->
    vm.image = uri
  }

  Scaffold(
    topBar = {
      CustomHeaderBasic(navController = navController)
    },
    bottomBar = {
      CustomBottomBar(navController = navController)
    }
  ) { innerPadding ->
    Column(
      modifier = Modifier
        .padding(innerPadding)
        .padding(20.dp),
      verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {

      Text(
        text = stringResource(id = R.string.add_item),
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.16.sp,
        fontFamily = PoppinsFamily,
        modifier = Modifier.padding(start = 5.dp)
      )

      CustomTextField(
        value = vm.name,
        label = stringResource(id = R.string.item_name),
        placeholder = stringResource(id = R.string.item_name),
        onValueChange = {
          vm.name = it
          if (nameError) {
            nameError = false
          }
        },
        isError = nameError,
      )

      CustomTextField(
        value = vm.desc,
        label = stringResource(id = R.string.item_desc),
        placeholder = stringResource(id = R.string.item_desc),
        onValueChange = {
          vm.desc = it
          if (descError) {
            descError = false
          }
        },
        isError = descError,
      )

      Column() {
        Text(
          text = stringResource(id = R.string.item_picture),
          modifier = Modifier.padding(start = 5.dp, bottom = 5.dp)
        )

        CustomOutlinedButton(
          text = stringResource(id = R.string.upload_image),
          onClick = {
            imagePicker.launch("image/*")
          }
        )
      }

      CustomRadio(
        label = stringResource(id = R.string.item_category),
        selectedItem = vm.category,
        items = itemCategoryOptions,
        onItemSelected = { selectedCategory ->
          vm.category = selectedCategory
          categoryError = false
        },
        isError = categoryError
      )

      Spacer(modifier = Modifier.height(5.dp))

      CustomButton(
        text = stringResource(id = R.string.save_btn),
        onClick = {
          if (vm.name.isBlank()) {
            nameError = true
          }
          if (vm.desc.isBlank()) {
            descError = true
          }
          if (vm.category.isBlank()) {
            categoryError = true
          }

          if (!nameError && !descError && !categoryError) {
            isLoading = true
            scope.launch {
              val addPostSuccessful = vm.updateItemDetail()
              isLoading = false
              if (addPostSuccessful) {
                showSuccessDialog = true

              } else {
                showFailedDialog = true
              }
            }
            isLoading = false
          }
        }
      )
    }
  }

  if (showSuccessDialog) {
    CustomDialog(
      title = stringResource(id = R.string.congratulation),
      body = stringResource(id = R.string.edit_post_success_desc),
      onDismissRequest = { /*TODO*/ },
      onClose = {
        showSuccessDialog = false
        navController.navigate(NavigationRoutes.home.route)
      }
    )
  }

  if (showFailedDialog) {
    CustomDialog(
      title = stringResource(id = R.string.edit_post_error_header),
      body = stringResource(id = R.string.edit_post_error_desc),
      onDismissRequest = { showFailedDialog = false },
      onClose = { showFailedDialog = false }
    )
  }
}


@Preview(showBackground = true)
@Composable
fun EditPostPreview() {
  val navController = rememberNavController()
  EditPost(navController = navController)
}
