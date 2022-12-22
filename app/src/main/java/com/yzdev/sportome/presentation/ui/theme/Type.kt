package com.yzdev.sportome.presentation.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.R

val QuickSandFont = FontFamily(
    Font(R.font.quicksand_bold, FontWeight.Bold),
    Font(R.font.quicksand_light, FontWeight.Light),
    Font(R.font.quicksand_medium, FontWeight.Medium),
    Font(R.font.quicksand_regular, FontWeight.Normal),
    Font(R.font.quicksand_semibold, FontWeight.SemiBold),
)

val Typography = Typography(
    h1 = TextStyle(
        fontFamily = QuickSandFont,
        fontWeight = FontWeight.Light,
        fontSize = 99.sp,
        letterSpacing = (-1.5).sp
    ),
    h2 = TextStyle(
        fontFamily = QuickSandFont,
        fontWeight = FontWeight.Light,
        fontSize = 62.sp,
        letterSpacing = (-0.5).sp
    ),
    h3 = TextStyle(
        fontFamily = QuickSandFont,
        fontWeight = FontWeight.Normal,
        fontSize = 49.sp,
        letterSpacing = 0.sp
    ),
    h4 = TextStyle(
        fontFamily = QuickSandFont,
        fontWeight = FontWeight.Normal,
        fontSize = 35.sp,
        letterSpacing = 0.25.sp
    ),
    h5 = TextStyle(
        fontFamily = QuickSandFont,
        fontWeight = FontWeight.Normal,
        fontSize = 25.sp,
        letterSpacing = 0.sp
    ),
    h6 = TextStyle(
        fontFamily = QuickSandFont,
        fontWeight = FontWeight.Medium,
        fontSize = 21.sp,
        letterSpacing = 0.15.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = QuickSandFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = 0.15.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = QuickSandFont,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        letterSpacing = 0.1.sp
    ),
    body1 = TextStyle(
        fontFamily = QuickSandFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = 0.5.sp
    ),
    body2 = TextStyle(
        fontFamily = QuickSandFont,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.25.sp
    ),
    button = TextStyle(
        fontFamily = QuickSandFont,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        letterSpacing = 1.25.sp
    ),
    caption = TextStyle(
        fontFamily = QuickSandFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 0.4.sp
    ),
    overline = TextStyle(
        fontFamily = QuickSandFont,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        letterSpacing = 1.5.sp
    )

)