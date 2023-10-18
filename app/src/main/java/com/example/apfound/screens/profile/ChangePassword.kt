package com.example.apfound.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
import com.example.apfound.ui.widgets.CustomHeaderCommon
import com.example.apfound.ui.widgets.CustomIconButton
import com.example.apfound.ui.widgets.CustomTextField
import com.example.apfound.utils.NavigationRoutes
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePassword(
  navController: NavController,
  vm: ProfileViewModel = viewModel()
) {
  var showPassword by remember { mutableStateOf(false) }
  var showFailedDialog by remember { mutableStateOf(false) }
  var showSuccessDialog by remember { mutableStateOf(false) }
  var passwordError by rememberSaveable { mutableStateOf(false) }
  var confirmError by rememberSaveable { mutableStateOf(false) }
  var isLoading by remember { mutableStateOf(false) }
  val scope = rememberCoroutineScope()
  val passwordErrorMsg =
    "Password should be at least 8 characters long with a combination of uppercase letters, lowercase letters and numbers."

  Scaffold(
    topBar = {
      CustomHeaderCommon(vm.name)
    },

    bottomBar = {
      CustomBottomBar(navController = navController)
    }

  ) { innerPadding ->
    Column(
      modifier = Modifier
        .padding(innerPadding)
        .padding(25.dp),
      verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
      Text(
        text = stringResource(id = R.string.change_password),
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.16.sp,
        fontFamily = PoppinsFamily,
        modifier = Modifier.padding(start = 5.dp)
      )

      CustomTextField(
        value = vm.password,
        label = stringResource(id = R.string.password_field),
        placeholder = stringResource(id = R.string.password_field),
        onValueChange = {
          vm.password = it
          if (passwordError) {
            passwordError = false
          }
        },
        isError = passwordError,
        errorMsg = passwordErrorMsg,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
          CustomIconButton(
            onClick = { showPassword = !showPassword },
            icon = if (showPassword) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
            contentDescription = if (showPassword) "Hide password" else "Show password"
          )
        },
      )

      CustomTextField(
        value = vm.confirmPassword,
        label = stringResource(id = R.string.confirm_password_field),
        placeholder = stringResource(id = R.string.confirm_password_field),
        onValueChange = {
          vm.confirmPassword = it
          if (confirmError) {
            confirmError = false
          }
        },
        isError = confirmError,
        errorMsg = "Password does not match.",
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
          CustomIconButton(
            onClick = { showPassword = !showPassword },
            icon = if (showPassword) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
            contentDescription = if (showPassword) "Hide password" else "Show password"
          )
        },
      )

      CustomButton(
        text = stringResource(id = R.string.save_btn),
        onClick = {
          if (!isValidPassword(vm.password)) {
            passwordError = true
          }

          if (vm.password != vm.confirmPassword) {
            confirmError = true
          }

          if (!passwordError && !confirmError) {
            isLoading = true
            scope.launch {
              val changePasswordSuccessful = vm.changePassword()
              isLoading = false

              if (changePasswordSuccessful != null) {
                showSuccessDialog = true

              } else {
                showFailedDialog = true
              }
            }
            isLoading = false
          }
        },
        modifier = Modifier.height(66.dp)
      )
    }
  }

  if (showSuccessDialog) {
    CustomDialog(
      title = stringResource(id = R.string.congratulation),
      body = stringResource(id = R.string.change_password_success_desc),
      onDismissRequest = { /*TODO*/ },
      onClose = {
        showSuccessDialog = false
        navController.navigate(NavigationRoutes.profile.route)
      }
    )
  }

  if (showFailedDialog) {
    CustomDialog(
      title = stringResource(id = R.string.change_password_error_header),
      body = stringResource(id = R.string.change_password_error_desc),
      onDismissRequest = { showFailedDialog = false },
      onClose = { showFailedDialog = false }
    )
  }
}

@Preview(showBackground = true)
@Composable
fun ChangePasswordPreview() {
  val navController = rememberNavController()
  ChangePassword(navController)
}

