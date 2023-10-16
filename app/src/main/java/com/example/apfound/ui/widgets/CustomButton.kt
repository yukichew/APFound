package com.example.apfound.ui.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.apfound.ui.theme.*

@Composable
fun CustomButton(
  text: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  btnColor: Color = primaryColor,
  textColor: Color = whiteColor,
) {
  Button(
    onClick = onClick,
    shape = RoundedCornerShape(5.dp),
    colors = ButtonDefaults.buttonColors(
      containerColor = btnColor
    ),
    modifier = modifier
      .fillMaxWidth()
      .height(50.dp)
  ) {
    Text(
      text = text,
      color = textColor
    )
  }
}

@Composable
fun CustomOutlinedButton(
  text: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  btnColor: Color = primaryColor,
  textColor: Color = primaryColor
) {
  OutlinedButton(
    onClick = onClick,
    shape = RoundedCornerShape(5.dp),
    border = BorderStroke(1.dp, btnColor),
    modifier = modifier
      .fillMaxWidth()
      .height(50.dp)
  ) {
    Text(
      text = text,
      color = textColor
    )
  }
}

@Composable
fun CustomIconButton(
  onClick: () -> Unit,
  icon: ImageVector,
  contentDescription: String = ""
) {
  IconButton(onClick = onClick) {
    Icon(
      imageVector = icon,
      contentDescription = contentDescription
    )
  }
}

@Composable
fun CustomTextIconButton(
  onClick: () -> Unit,
  icon: ImageVector,
  text: String,
  contentDescription: String = "",
  iconColor: Color = primaryColor,
  textColor: Color = primaryColor,
  backgroundColor: Color = whiteColor,
  borderColor: Color = primaryColor,
  modifier: Modifier = Modifier,
) {
  Box(
    modifier = modifier
      .fillMaxWidth()
      .height(50.dp)
      .border(1.dp, borderColor) // Add border
      .background(backgroundColor) // Set background color
      .clickable { onClick() }
  ) {
    Row(
      modifier = Modifier
        .fillMaxSize()
        .padding(start = 18.dp),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Icon(
        imageVector = icon,
        contentDescription = contentDescription,
        tint = iconColor
      )
      Spacer(modifier = Modifier.width(8.dp))
      Text(
        text = text,
        color = textColor
      )
    }
  }
}
