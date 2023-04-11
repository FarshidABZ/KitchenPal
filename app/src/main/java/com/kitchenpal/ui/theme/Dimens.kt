package com.kitchenpal.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class Dimensions(
    val default: Dp = 0.dp,
    val spaceTiny: Dp = 1.dp,
    val spaceXSmall: Dp = 2.dp,
    val spaceSmall: Dp = 4.dp,
    val spaceMedium: Dp = 8.dp,
    val spaceLarge: Dp = 12.dp,
    val spaceXLarge: Dp = 16.dp,
    val spaceXXLarge: Dp = 24.dp,
    val spaceHuge: Dp = 32.dp,
)

val smallDimensions = Dimensions(0.dp, 0.75.dp, 1.5.dp, 3.dp, 6.dp, 9.dp, 12.dp, 18.dp, 24.dp)

val sw360Dimensions = Dimensions()
