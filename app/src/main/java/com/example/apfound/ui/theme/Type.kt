package com.example.apfound.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.apfound.R

val Inter = FontFamily(
  Font(R.font.inter_font),
)

val Poppins = FontFamily(
  Font(R.font.poppins_font)
)

val PoppinsFamily = FontFamily(
  Font(R.font.poppins_extra_bold, FontWeight.ExtraBold),
  Font(R.font.poppins_semi_bold, FontWeight.SemiBold),
  Font(R.font.poppins_bold, FontWeight.Bold)
)

val Typography = Typography(
  bodyMedium = TextStyle(
    fontFamily = Inter,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.5.sp
  )
)
