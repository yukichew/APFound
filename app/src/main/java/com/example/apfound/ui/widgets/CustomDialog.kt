package com.example.apfound.ui.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun CustomDialog(
  title: String,
  body: String,
  onDismissRequest: () -> Unit,
  onClose: () -> Unit,
) {
  Dialog(
    onDismissRequest = { onDismissRequest() },
  ) {
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .height(350.dp)
        .padding(16.dp),
      shape = RoundedCornerShape(16.dp),
    ) {
      Column(
        modifier = Modifier
          .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        Text(
          text = title,
          modifier = Modifier.padding(16.dp),
          style = TextStyle(
            fontSize = 22.sp,
            fontWeight = FontWeight(700)
          ),
          textAlign = TextAlign.Center
        )
        Text(
          text = body,
//          modifier = Modifier.padding(16.dp),
          textAlign = TextAlign.Center
        )

        Row(
          modifier = Modifier
            .fillMaxWidth(),
          horizontalArrangement = Arrangement.Center,
        ) {
          TextButton(
            onClick = {
              onClose()
            }
          ) {
            Text("Close")
          }
        }
      }
    }
  }

  @Composable
  fun CustomDialogBasic(
    title: String,
    body: String,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
  ) {
    AlertDialog(
      title = { Text(text = title, fontWeight = FontWeight.Bold) },
      text = { Text(text = body) },
      onDismissRequest = onDismissRequest,
      dismissButton = {
        TextButton(
          onClick = {
            onDismissRequest()
          }
        ) {
          Text("Dismiss")
        }
      },
      confirmButton = {
        TextButton(
          onClick = {
            onConfirmation()
          }
        ) {
          Text("Confirm")
        }
      }
    )
  }
}


@Preview(showBackground = true)
@Composable
fun CustomDialogPreview() {
  CustomDialog("Congratulations! ", "You have successfully login.", {}, {})
}

//@Preview(showBackground = true)
//@Composable
//fun CustomDialogBasicPreview() {
//  CustomDialogBasic("Congratulations! ", "You have successfully login.", {}, {})
//}
