package com.example.apfound.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.apfound.R
import com.example.apfound.ui.theme.PoppinsFamily
import com.example.apfound.ui.widgets.CustomBottomBar
import com.example.apfound.ui.widgets.CustomButton
import com.example.apfound.ui.widgets.CustomHeaderBasic
import com.example.apfound.ui.widgets.CustomHorizontalCardBasic
import com.example.apfound.utils.NavigationRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(
  navController: NavController,
  vm: ProfileViewModel = viewModel()
) {
  Scaffold(
    topBar = {
      CustomHeaderBasic(navController = navController)
    },

    bottomBar = {
      CustomBottomBar(navController = navController)
    }

  ) { innerPadding ->
    Column(
      modifier = Modifier
        .padding(innerPadding)
        .padding(25.dp),
    ) {
      Text(
        text = stringResource(id = R.string.user_profile_title),
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.16.sp,
        fontFamily = PoppinsFamily,
        modifier = Modifier.padding(start = 5.dp)
      )

      Spacer(modifier = Modifier.height(10.dp))

      CustomHorizontalCardBasic(
        image = null,
        title = vm.name,
        onEditClick = {
          navController.navigate(
            NavigationRoutes.editProfile.route
          )
        }
      )

      Spacer(modifier = Modifier.height(25.dp))

      CustomHorizontalCardBasic(
        image = null,
        title = "Change Password",
        onEditClick = {
          navController.navigate(
            NavigationRoutes.editProfile.route
          )
        }
      )

      Spacer(modifier = Modifier.height(35.dp))

      CustomButton(
        text = stringResource(id = R.string.logout),
        onClick = {
        },
        modifier = Modifier.height(66.dp)
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
  val navController = rememberNavController()
  Profile(navController = navController)
}
