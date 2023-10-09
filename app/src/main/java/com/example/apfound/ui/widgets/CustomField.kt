package com.example.apfound.ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.apfound.R
import com.example.apfound.ui.theme.coralTreeColor
import com.example.apfound.ui.theme.galleryColor
import com.example.apfound.ui.theme.primaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
  label: String,
  value: String = "",
  placeholder: String,
  onValueChange: (String) -> Unit,
  isSingleLine: Boolean = false,
  maxLines: Int = 1,
  trailingIcon: @Composable () -> Unit = {},
  isError: Boolean = false,
  errorMsg: String = stringResource(R.string.text_field_error_msg),
  keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
  Column {
    Text(
      text = label,
      modifier = Modifier.padding(start = 5.dp, bottom = 5.dp)
    )

    OutlinedTextField(
      value = value,
      placeholder = { Text(text = placeholder) },
      onValueChange = onValueChange,
      label = null,
      singleLine = isSingleLine,
      maxLines = maxLines,
      trailingIcon = trailingIcon,
      isError = isError,
      keyboardOptions = keyboardOptions,
      colors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = primaryColor,
        unfocusedBorderColor = galleryColor,
      ),
      modifier = Modifier.fillMaxWidth()
    )

    if (isError) {
      Text(
        text = errorMsg,
        color = coralTreeColor,
        modifier = Modifier.padding(start = 5.dp)
      )
    }
  }
}

@Preview
@Composable
fun CustomTextFieldPreview() {
  Surface(
    modifier = Modifier.fillMaxSize()
  ) {
    CustomTextField(
      label = "test",
      placeholder = "test",
      onValueChange = {},
      isError = false,
      errorMsg = "Required Field"
    )
  }
}
