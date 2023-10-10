package com.example.apfound.ui.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apfound.R
import com.example.apfound.ui.theme.whiteColor

@Composable
fun CustomCard(
  image: Int,
  title: String,
  body: String
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .height(280.dp),
    elevation = CardDefaults.cardElevation(10.dp),
    colors = CardDefaults.cardColors(
      containerColor = whiteColor
    ),
  ) {
    Column(
      modifier = Modifier.fillMaxSize()
    ) {
      Image(
        painter = painterResource(id = image),
        contentScale = ContentScale.Crop,
        contentDescription = null,
        modifier = Modifier.fillMaxWidth()
      )

      Text(
        text = title,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        modifier = Modifier.padding(10.dp)
      )

      Text(
        text = body,
        fontSize = 14.sp,
        modifier = Modifier.padding(6.dp),
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        color = Color.Gray
      )
    }
  }
}

@Preview
@Composable
fun CustomCardPreview() {
  CustomCard(R.drawable.logo,"Title", "Lorem Ipsum Lorem Ipsum Lorem IpsumLorem IpsumLorem Ipsum Lorem Ipsum Lorem Ipsum Lorem IpsumLorem IpsumLorem Ipsum")
}
