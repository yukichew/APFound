package com.example.apfound.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.apfound.R
import com.example.apfound.ui.widgets.CustomButton
import com.example.apfound.ui.widgets.CustomHeader
import com.example.apfound.ui.widgets.CustomTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(
  navBack: () -> Unit,
) {
  Scaffold(
    topBar = {
      CustomHeader(title = stringResource(id = R.string.login), navBack = navBack)
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
        label = stringResource(id = R.string.email_field),
        placeholder = stringResource(id = R.string.email_field),
        onValueChange = {}
      )

      CustomTextField(
        label = stringResource(id = R.string.password_field),
        placeholder = stringResource(id = R.string.password_field),
        onValueChange = {},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
      )

      Spacer(modifier = Modifier.height(10.dp))

      CustomButton(
        text = stringResource(id = R.string.login),
        onClick = { /*TODO*/ }
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
  Login(
    navBack = {}
  )
}

