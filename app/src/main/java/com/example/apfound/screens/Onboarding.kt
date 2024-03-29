package com.example.apfound.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.apfound.R
import com.example.apfound.ui.widgets.CustomButton
import com.example.apfound.ui.widgets.CustomOutlinedButton
import com.example.apfound.utils.NavigationRoutes

@Composable
fun Onboarding(
  navController: NavController
) {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
      .fillMaxSize()
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

    Spacer(modifier = Modifier.height(25.dp))

    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
//      verticalArrangement = Arrangement.spacedBy(15.dp),
      modifier = Modifier.padding(start = 20.dp, end = 20.dp)
    ) {
      Text(
        text = stringResource(id = R.string.welcome_message),
        style = TextStyle(
          fontSize = 28.sp,
          fontWeight = FontWeight(800),
          color = Color(0xFF000000),
          textAlign = TextAlign.Center,
        )
      )

      Spacer(modifier = Modifier.height(5.dp))

      Text(
        text = stringResource(id = R.string.slogan),
        style = TextStyle(
          fontSize = 14.sp,
          lineHeight = 18.sp,
          fontWeight = FontWeight(300),
          color = Color(0xFF000000),
          textAlign = TextAlign.Center
        )
      )

      Spacer(modifier = Modifier.height(110.dp))

      CustomButton(
        text = stringResource(id = R.string.login),
        onClick = {
          navController.navigate(NavigationRoutes.login.route)
        }
      )

      Spacer(modifier = Modifier.height(20.dp))

      CustomOutlinedButton(
        text = stringResource(id = R.string.register),
        onClick = {
          navController.navigate(NavigationRoutes.signUp.route)
        }
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {

}
