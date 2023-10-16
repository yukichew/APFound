package com.example.apfound.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.apfound.R
import com.example.apfound.model.User
import com.example.apfound.ui.widgets.CustomButton
import com.example.apfound.ui.widgets.CustomDialog
import com.example.apfound.ui.widgets.CustomHeader
import com.example.apfound.ui.widgets.CustomIconButton
import com.example.apfound.ui.widgets.CustomTextField
import com.example.apfound.utils.NavigationRoutes
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(
  navController: NavController,
  vm: LoginViewModel = viewModel(),
) {
  var showFailedDialog by remember { mutableStateOf(false) }
  var showSuccessDialog by remember { mutableStateOf(false) }
  var showPassword by remember { mutableStateOf(false) }
  var isLoading by remember { mutableStateOf(false) }
  val scope = rememberCoroutineScope()

  Scaffold(
    topBar = {
      CustomHeader(title = stringResource(id = R.string.login), navController = navController)
    },
  ) { innerPadding ->
    Column(
      modifier = Modifier
        .padding(innerPadding)
        .padding(20.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
      CustomTextField(
        value = vm.email,
        label = stringResource(id = R.string.email_field),
        placeholder = stringResource(id = R.string.email_field),
        onValueChange = { vm.email = it }
      )

      CustomTextField(
        value = vm.password,
        label = stringResource(id = R.string.password_field),
        placeholder = stringResource(id = R.string.password_field),
        onValueChange = { vm.password = it },
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

      Spacer(modifier = Modifier.height(10.dp))

      CustomButton(
        text = stringResource(id = R.string.login),
        onClick = {
          scope.launch {
            isLoading = true
            val data = vm.logInWithEmail()

            if (data != null) {
              showSuccessDialog = true

            } else {
              showFailedDialog = true
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
      body = stringResource(id = R.string.login_success_desc),
      onDismissRequest = { /*TODO*/ },
      onClose = {
        showSuccessDialog = false
        User.updateLoginUser()
        navController.navigate(NavigationRoutes.home.route)
      }
    )
  }

  if (showFailedDialog) {
    CustomDialog(
      title = stringResource(id = R.string.login_error_header),
      body = stringResource(id = R.string.login_error_desc),
      onDismissRequest = { showFailedDialog = false },
      onClose = { showFailedDialog = false }
    )
  }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
}

