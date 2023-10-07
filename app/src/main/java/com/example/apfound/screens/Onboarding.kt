package com.example.apfound.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apfound.R
import com.example.apfound.ui.widgets.CustomButton
import com.example.apfound.ui.widgets.CustomOutlinedButton

@Composable
fun Onboarding() {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.SpaceBetween,
    modifier = Modifier.fillMaxSize()
  ) {
    Image(
      painterResource(id = R.drawable.onboarding),
      contentDescription = stringResource(R.string.app_name),
//      modifier = Modifier
//        .clip(
//          RoundedCornerShape(
//            bottomStart = 15.dp,
//            bottomEnd = 15.dp
//          )
//        )
    )

    Spacer(modifier = Modifier.height(10.dp))

    Text(
      text = "Welcome to APFound!",
      style = TextStyle(
        fontSize = 28.sp,
        fontWeight = FontWeight(800),
        color = Color(0xFF000000),
        textAlign = TextAlign.Center,
      )
    )

    Text(
      text = "APFound, your go-to solution for lost and found on campus. " +
        "Students now have an easy way to discover those missing treasures.",
      style = TextStyle(
        fontSize = 14.sp,
        lineHeight = 18.sp,
        fontWeight = FontWeight(300),
        color = Color(0xFF000000),
        textAlign = TextAlign.Center
      ),
      modifier = Modifier.padding(start = 35.dp, end = 35.dp)
    )

    Spacer(modifier = Modifier.height(120.dp))

    CustomButton(
      text = "Login",
      onClick = { /*TODO*/ }
    )

    CustomOutlinedButton(
      text = "Register",
      onClick = { /*TODO*/ }
    )

    Spacer(modifier = Modifier.height(25.dp))
  }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
  Onboarding()
}
