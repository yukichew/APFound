package com.example.apfound.ui.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
  textColor: Color = whiteColor
){
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
){
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
){
  IconButton(
    onClick = onClick
  ) {
    Icon(
      imageVector = icon,
      contentDescription = contentDescription
    )
  }
}
