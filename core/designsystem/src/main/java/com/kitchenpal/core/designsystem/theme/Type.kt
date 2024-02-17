package com.kitchenpal.core.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kitchenpal.core.designsystem.R

private val poppinsFamily = FontFamily(
    Font(R.font.poppins_light, FontWeight.Light),
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_bold, FontWeight.Bold)
)

internal val KitchenPalTypography = Typography(
    displayLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        fontFamily = poppinsFamily,
        letterSpacing = (-0.25).sp,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    displayMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        fontFamily = poppinsFamily,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    displaySmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        fontFamily = poppinsFamily,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    headlineLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        fontFamily = poppinsFamily,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    headlineMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        fontFamily = poppinsFamily,
        lineHeight = 36.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    headlineSmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        fontFamily = poppinsFamily,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    titleLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        fontFamily = poppinsFamily,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    titleMedium = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        fontFamily = poppinsFamily,
        letterSpacing = 0.1.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    titleSmall = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontFamily = poppinsFamily,
        letterSpacing = 0.1.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    bodyLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        fontFamily = poppinsFamily,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    bodyMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontFamily = poppinsFamily,
        letterSpacing = 0.25.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    bodySmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontFamily = poppinsFamily,
        letterSpacing = 0.4.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    labelLarge = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontFamily = poppinsFamily,
        letterSpacing = 0.1.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    labelMedium = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontFamily = poppinsFamily,
        letterSpacing = 0.5.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    labelSmall = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        lineHeight = 16.sp,
        fontFamily = poppinsFamily,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
)