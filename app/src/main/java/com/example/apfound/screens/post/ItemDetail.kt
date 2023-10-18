package com.example.apfound.screens.post

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Message
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.apfound.ui.theme.PoppinsFamily
import com.example.apfound.ui.theme.galleryColor
import com.example.apfound.ui.theme.whiteColor
import com.example.apfound.ui.widgets.CustomBottomBar
import com.example.apfound.ui.widgets.CustomHeaderBasic
import com.example.apfound.ui.widgets.CustomIconButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemDetail(
  navController: NavController,
  vm: ItemDetailViewModel = viewModel()
) {
  val scope = rememberCoroutineScope()

  Scaffold(
    topBar = {
      CustomHeaderBasic(navController = navController)
    },
    bottomBar = {
      CustomBottomBar(navController = navController)
    },
    containerColor = whiteColor
  ) { innerPadding ->
    Column(
      modifier = Modifier
        .padding(innerPadding)
        .padding(start = 25.dp, end = 25.dp),
    ) {
      Image(
        painter = rememberImagePainter(vm.image),
        contentScale = ContentScale.Fit,
        contentDescription = null,
        modifier = Modifier
          .fillMaxWidth()
          .height(200.dp)
      )

      Spacer(modifier = Modifier.height(10.dp))

      Box(
        modifier = Modifier
          .background(galleryColor)
          .padding(10.dp)
          .clip(RoundedCornerShape(10.dp))
      ) {
        Text(
          text = vm.category,
          fontSize = 12.sp,
          letterSpacing = 0.1.sp,
          textAlign = TextAlign.Center,
        )
      }

      Spacer(modifier = Modifier.height(10.dp))

      Text(
        text = vm.name,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        letterSpacing = 0.1.sp,
        fontFamily = PoppinsFamily
      )

      Text(
        text = vm.date,
        fontSize = 12.sp,
        letterSpacing = 0.1.sp
      )

      Spacer(modifier = Modifier.height(10.dp))

      Text(
        text = vm.desc,
        fontSize = 14.2.sp,
        letterSpacing = 0.1.sp,
        lineHeight = 18.2.sp,
      )

      Spacer(modifier = Modifier.height(30.dp))

      Row(
        modifier = Modifier
          .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          modifier = Modifier
            .weight(4f),
          horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
          Icon(
            imageVector = Icons.Outlined.Person,
            contentDescription = ""
          )

          Text(
            text = vm.fullName,
            fontSize = 14.sp,
            letterSpacing = 0.1.sp
          )

        }

        Row(
          horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
          CustomIconButton(
            onClick = {
//              val emailIntent = Intent(Intent.ACTION_SEND)
//              emailIntent.type = "message/rfc822"
//              emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(vm.email))
//              context.startActivity(emailIntent)
            },
            icon = Icons.Outlined.Email
          )

          CustomIconButton(
            onClick = {
//              val messageIntent = Intent(Intent.ACTION_SENDTO)
//              messageIntent.data = Uri.parse("smsto:${vm.contactNumber}")
//              context.startActivity(messageIntent)
            },
            icon = Icons.Outlined.Message
          )

          CustomIconButton(
            onClick = {
//              val dialIntent = Intent(Intent.ACTION_DIAL)
//              dialIntent.data = Uri.parse("tel:${vm.contactNumber}")
//              context.startActivity(dialIntent)
            },
            icon = Icons.Outlined.Phone
          )
        }

      }
    }
  }
}

@Preview
@Composable
fun ItemDetailPreview() {

}
