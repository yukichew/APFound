package com.example.apfound.screens.signup

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
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.navigation.compose.rememberNavController
import com.example.apfound.R
import com.example.apfound.ui.widgets.CustomButton
import com.example.apfound.ui.widgets.CustomDialog
import com.example.apfound.ui.widgets.CustomHeader
import com.example.apfound.ui.widgets.CustomIconButton
import com.example.apfound.ui.widgets.CustomRadio
import com.example.apfound.ui.widgets.CustomTextField
import com.example.apfound.utils.NavigationRoutes
import com.google.i18n.phonenumbers.PhoneNumberUtil
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp(
  navController: NavController,
  vm: SignUpViewModel = viewModel()
) {
  val genderOptions =
    listOf(stringResource(id = R.string.male), stringResource(id = R.string.female))

  // error state
  var emailError by rememberSaveable { mutableStateOf(false) }
  var passwordError by rememberSaveable { mutableStateOf(false) }
  var nameError by rememberSaveable { mutableStateOf(false) }
  var contactError by rememberSaveable { mutableStateOf(false) }
  var genderError by rememberSaveable { mutableStateOf(false) }

  var showPassword by remember { mutableStateOf(false) }
  var showFailedDialog by remember { mutableStateOf(false) }
  var showSuccessDialog by remember { mutableStateOf(false) }
  var isLoading by remember { mutableStateOf(false) }
  val scope = rememberCoroutineScope()

  val passwordErrorMsg =
    "Password should be at least 8 characters long with a combination of uppercase letters, lowercase letters and numbers."
  val emailErrorMsg = "Please enter a valid APU email address. (TPXXXXX@mail.apu.edu.my)"
  val contactErrorMsg = "Please enter a valid contact number. (+60123456789)"

  Scaffold(
    topBar = {
      CustomHeader(title = stringResource(id = R.string.register), navController = navController)
    }
  ) { innerPadding ->
    Column(
      modifier = Modifier
        .padding(innerPadding)
        .padding(20.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
      CustomTextField(
        value = vm.name,
        label = stringResource(id = R.string.user_name_full_field),
        placeholder = stringResource(id = R.string.user_name_full_field),
        onValueChange = {
          vm.name = it
          if (nameError) {
            nameError = false
          }
        },
        isError = nameError,
      )

      CustomTextField(
        value = vm.email,
        label = stringResource(id = R.string.email_field),
        placeholder = stringResource(id = R.string.email_field),
        onValueChange = {
          vm.email = it
          if (emailError) {
            emailError = false
          }
        },
        isError = emailError,
        errorMsg = emailErrorMsg
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
        value = vm.contactNumber,
        label = stringResource(id = R.string.contact_field),
        placeholder = stringResource(id = R.string.contact_field),
        onValueChange = {
          vm.contactNumber = it
          if (contactError) {
            contactError = false
          }
        },
        isError = contactError,
        errorMsg = contactErrorMsg,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
      )

      CustomRadio(
        label = stringResource(id = R.string.gender_field),
        selectedItem = vm.gender,
        items = genderOptions,
        onItemSelected = { selectedGender ->
          vm.gender = selectedGender
          genderError = false
        },
        isError = genderError
      )

      Spacer(modifier = Modifier.height(10.dp))

      CustomButton(
        text = stringResource(id = R.string.register),
        onClick = {
          if (vm.name.isBlank()) {
            nameError = true
          }
          if (!isValidEmail(vm.email)) {
            emailError = true
          }
          if (!isValidContactNumber(vm.contactNumber)) {
            contactError = true
          }
          if (!isValidPassword(vm.password)) {
            passwordError = true
          }
          if (vm.gender.isBlank()) {
            genderError = true
          }

          // Check error flags before registration
          if (!nameError && !emailError && !contactError && !passwordError && !genderError) {
            isLoading = true
            scope.launch {
              val registrationSuccessful = vm.registerUser()
              isLoading = false

              if (registrationSuccessful != null) {
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
      body = stringResource(id = R.string.sign_up_success_desc),
      onDismissRequest = { /*TODO*/ },
      onClose = {
        showSuccessDialog = false
        navController.navigate(NavigationRoutes.home.route)
      }
    )
  }

  if (showFailedDialog) {
    CustomDialog(
      title = stringResource(id = R.string.sign_up_error_header),
      body = stringResource(id = R.string.sign_up_error_desc),
      onDismissRequest = { showFailedDialog = false },
      onClose = { showFailedDialog = false }
    )
  }
}

// Validate email format
fun isValidEmail(email: String): Boolean {
  val emailPattern = "^[A-Za-z0-9+_.-]+@(apu\\.edu\\.my|mail\\.apu\\.edu\\.my)$"
  return email.matches(emailPattern.toRegex())
}

// Validate password format
fun isValidPassword(password: String): Boolean {
  val passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}\$"
  return password.matches(passwordPattern.toRegex())
}

// Validate contact number
fun isValidContactNumber(contactNumber: String): Boolean {
  val phoneNumberUtil = PhoneNumberUtil.getInstance()
  return try {
    val parsedNumber = phoneNumberUtil.parse(contactNumber, null)
    phoneNumberUtil.isValidNumber(parsedNumber)
  } catch (e: Exception) {
    false
  }
}

@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
  val navController = rememberNavController()
  SignUp(navController)
}
