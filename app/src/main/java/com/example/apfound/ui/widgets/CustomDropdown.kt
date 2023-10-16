package com.example.apfound.ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomDropdown(
  label: String = "",
  selectedItem: String,
  items: List<String>,
  onItemSelected: (String) -> Unit
) {
  var expanded by remember { mutableStateOf(false) }

  Column {
    Text(
      text = label,
      modifier = Modifier.padding(start = 5.dp, bottom = 5.dp)
    )

    CustomButton(
      text = if (selectedItem.isEmpty()) "Select ${label.lowercase()}" else selectedItem,
      onClick = { expanded = true },
      modifier = Modifier.padding(16.dp)
    )

    DropdownMenu(
      expanded = expanded,
      onDismissRequest = { expanded = false }
    ) {
      items.forEach { item ->
        DropdownMenuItem(
          onClick = {
            onItemSelected(item)
            expanded = false
          },
          text = { Text(text = item) }
        )
      }
    }

  }
}

@Preview
@Composable
fun CustomDropdownPreview() {
  var selectedGender by remember { mutableStateOf("Select Gender") }
  val genderItems = listOf("Male", "Female")

  CustomDropdown(label = "Gender", selectedItem = selectedGender, items = genderItems, onItemSelected = { selectedGender = it })
}
