package com.example.apfound.ui.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.apfound.R
import com.example.apfound.ui.theme.coralTreeColor

@Composable
fun CustomRadio(
  label: String = "",
  selectedItem: String,
  items: List<String>,
  onItemSelected: (String) -> Unit,
  isError: Boolean = false,
  errorMsg: String = stringResource(R.string.text_field_error_msg),
) {
  Column {
    Text(
      text = label,
      modifier = Modifier.padding(start = 5.dp, bottom = 5.dp)
    )

    Row(
      modifier = Modifier
        .fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,

      ) {
      items.forEach { item ->
        RadioButton(
          selected = selectedItem == item,
          onClick = { onItemSelected(item) },
        )
        Text(
          text = item,
          modifier = Modifier.padding(end = 20.dp)
        )
      }
    }

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
fun CustomRadioPreview() {
  var selectedGender by remember { mutableStateOf("Male") }
  val genderOptions = listOf("Male", "Female")

  CustomRadio(
    label = "Gender",
    selectedItem = selectedGender,
    items = genderOptions,
    onItemSelected = { selectedGender = it }
  )
}
