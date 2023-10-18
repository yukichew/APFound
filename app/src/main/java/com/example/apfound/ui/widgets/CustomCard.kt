package com.example.apfound.ui.widgets

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.apfound.ui.theme.PoppinsFamily
import com.example.apfound.ui.theme.whiteColor

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CustomCard(
  image: Uri?,
  title: String,
  onClick: () -> Unit
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .height(240.dp)
      .clickable(onClick = onClick),
    elevation = CardDefaults.cardElevation(4.dp),
    colors = CardDefaults.cardColors(
      containerColor = whiteColor
    ),
  ) {
    Column(
      modifier = Modifier.fillMaxSize()
    ) {
      Image(
        painter = rememberImagePainter(image),
        contentScale = ContentScale.Fit,
        contentDescription = null,
        modifier = Modifier
          .fillMaxWidth()
          .height(180.dp)
      )

      Spacer(modifier = Modifier.height(5.dp))

      Text(
        text = title,
        fontWeight = FontWeight(450),
        fontSize = 20.sp,
        lineHeight = 18.sp,
        modifier = Modifier.padding(13.dp),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
      )
    }
  }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CustomHorizontalCard(
  image: Uri?,
  title: String,
  onEditClick: () -> Unit,
  onDeleteClick: () -> Unit
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .height(100.dp),
    elevation = CardDefaults.cardElevation(4.dp),
    colors = CardDefaults.cardColors(
      containerColor = whiteColor
    ),
  ) {
    Row(
      modifier = Modifier
        .fillMaxSize()
        .padding(15.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      image?.let {
        Image(
          painter = rememberImagePainter(image),
          contentDescription = null,
          contentScale = ContentScale.Fit,
          modifier = Modifier
            .size(75.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
        )
      }

      Spacer(modifier = Modifier.width(10.dp))

      Text(
        text = title,
        fontWeight = FontWeight(600),
        fontSize = 18.sp,
        lineHeight = 18.sp,
        modifier = Modifier
          .padding(13.dp)
          .width(140.dp),
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        softWrap = true,
      )

      Row(
        modifier = Modifier
          .fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
      ) {
        CustomIconButton(onClick = { onEditClick() }, icon = Icons.Default.Edit)
        CustomIconButton(onClick = { onDeleteClick() }, icon = Icons.Default.Delete)
      }
    }
  }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CustomHorizontalCardBasic(
  image: Uri?,
  title: String,
  onEditClick: () -> Unit
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .height(100.dp),
    elevation = CardDefaults.cardElevation(4.dp),
    colors = CardDefaults.cardColors(
      containerColor = whiteColor
    ),
  ) {
    Row(
      modifier = Modifier
        .fillMaxSize()
        .padding(15.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      image?.let {
        Image(
          painter = rememberImagePainter(image),
          contentDescription = null,
          contentScale = ContentScale.Fit,
          modifier = Modifier
            .size(50.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
        )
      }

      Spacer(modifier = Modifier.width(10.dp))

      Text(
        text = title,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        modifier = Modifier
          .padding(13.dp)
          .width(200.dp),
        letterSpacing = 0.1.sp,
        fontFamily = PoppinsFamily,
        softWrap = true
      )

      Row(
        modifier = Modifier
          .fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
      ) {
        CustomIconButton(onClick = { onEditClick() }, icon = Icons.Default.Edit)
      }
    }
  }
}

@Preview
@Composable
fun CustomCardPreview() {
}
