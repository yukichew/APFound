package com.example.apfound.screens

import android.annotation.SuppressLint
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.apfound.R
import com.example.apfound.utils.NavigationRoutes
import com.example.apfound.utils.NavigationRoutes.login
import kotlinx.coroutines.delay

@SuppressLint("RememberReturnType")
@Composable
fun SplashScreen(
  navController: NavController
){
  val scale = remember {
    Animatable(0f)
  }

  val alpha = remember {
    Animatable(0f)
  }

  LaunchedEffect(key1 = true)  {
    scale.animateTo(
      targetValue = 1.0f,
      animationSpec = tween(
        durationMillis = 400,
        easing = {
          OvershootInterpolator(2f).getInterpolation(it)
        }
      )
    )

    alpha.animateTo(
      targetValue = 1.0f,
      animationSpec = tween(
        durationMillis = 800
      )
    )

    delay(1500L)
    navController.navigate(NavigationRoutes.onboarding.route)
  }

  Box(
    contentAlignment = Alignment.Center,
    modifier = Modifier.fillMaxSize()
  ){
    Image(
      painter = painterResource(id = R.drawable.logo),
      contentDescription = stringResource(id = R.string.logo),
      modifier = Modifier
        .scale(scale.value)
        .alpha(alpha.value)
    )
  }
}
