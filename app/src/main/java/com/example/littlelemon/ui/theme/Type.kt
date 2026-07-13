package com.example.littlelemon.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.littlelemon.R

val Karla = FontFamily(
    Font(R.font.karla_regular)
)

val MarkaziText = FontFamily(
    Font(R.font.markazitext_regular)
)

// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = MarkaziText,
        fontWeight = FontWeight.Medium,
        fontSize = 64.sp
    ),
    displayMedium = TextStyle(
        fontFamily = MarkaziText,
        fontWeight = FontWeight.Medium,
        fontSize = 40.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Karla,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Karla,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)