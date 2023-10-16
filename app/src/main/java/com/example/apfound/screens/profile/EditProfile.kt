package com.example.apfound.screens.profile

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
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.apfound.R
import com.example.apfound.ui.theme.PoppinsFamily
import com.example.apfound.ui.widgets.CustomButton
import com.example.apfound.ui.widgets.CustomDialog
import com.example.apfound.ui.widgets.CustomHeader
import com.example.apfound.ui.widgets.CustomHeaderBasic
import com.example.apfound.ui.widgets.CustomIconButton
import com.example.apfound.ui.widgets.CustomRadio
import com.example.apfound.ui.widgets.CustomTextField
import com.example.apfound.utils.NavigationRoutes
import com.google.i18n.phonenumbers.PhoneNumberUtil
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfile(
  navController: NavController,
  vm: ProfileViewModel = viewModel()
) {
  val genderOptions =
    listOf(stringResource(id = R.string.male), stringResource(id = R.string.female))

  // error state
  var nameError by rememberSaveable { mutableStateOf(false) }
  var contactError by rememberSaveable { mutableStateOf(false) }
  var genderError by rememberSaveable { mutableStateOf(false) }

  var showFailedDialog by remember { mutableStateOf(false) }
  var showSuccessDialog by remember { mutableStateOf(false) }
  var isLoading by remember { mutableStateOf(false) }
  val scope = rememberCoroutineScope()

  val passwordErrorMsg =
    "Password should be at least 8 characters long with a combination of uppercase letters, lowercase letters and numbers."
  val contactErrorMsg = "Please enter a valid contact number. (+60123456789)"

  Scaffold(
    topBar = {
      CustomHeaderBasic(navController = navController)
    }
  ) { innerPadding ->
    Column(
      modifier = Modifier
        .padding(innerPadding)
        .padding(20.dp),
      verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {

      Text(
        text = stringResource(id = R.string.user_profile_title),
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.16.sp,
        fontFamily = PoppinsFamily,
        modifier = Modifier.padding(start = 5.dp)
      )

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
        text = stringResource(id = R.string.save_btn),
        onClick = {
          if (vm.name.isBlank()) {
            nameError = true
          }

          if (!isValidContactNumber(vm.contactNumber)) {
            contactError = true
          }

          if (vm.gender.isBlank()) {
            genderError = true
          }

          // Check error flags before registration
          if (!nameError && !contactError && !genderError) {
            isLoading = true
            scope.launch {
              isLoading = false
              val profileUpdated = vm.updateProfile()

              if (profileUpdated) {
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
      body = stringResource(id = R.string.edit_profile_success_desc),
      onDismissRequest = { /*TODO*/ },
      onClose = {
        showSuccessDialog = false
        navController.navigate(NavigationRoutes.profile.route)
      }
    )
  }

  if (showFailedDialog) {
    CustomDialog(
      title = stringResource(id = R.string.edit_profile_error_header),
      body = stringResource(id = R.string.edit_profile_error_desc),
      onDismissRequest = { showFailedDialog = false },
      onClose = { showFailedDialog = false }
    )
  }
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
