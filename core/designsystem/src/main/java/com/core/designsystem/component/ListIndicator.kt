package com.core.designsystem.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.core.designsystem.theme.KitchenPalTheme
import com.core.designsystem.theme.md_theme_light_surfaceContainerHighest

@Composable
fun ListIndicator(
    modifier: Modifier = Modifier,
    count: Int,
    size: Int,
    spacer: Int,
    selectedIndex: Int = 0,
    indicatorSelectedLength: Int = 40,
    selectedColor: Color = MaterialTheme.colorScheme.primaryContainer,
    unselectedColor: Color = md_theme_light_surfaceContainerHighest
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(count) {
            val isSelected = selectedIndex == it
            IndicatorView(
                isSelected, size, indicatorSelectedLength, selectedColor, unselectedColor, spacer
            )
        }
    }
}

@Composable
private fun IndicatorView(
    isSelected: Boolean,
    size: Int,
    indicatorSelectedLength: Int,
    selectedColor: Color,
    unselectedColor: Color,
    spacer: Int
) {
    val color: Color by animateColorAsState(
        targetValue = if (isSelected) {
            selectedColor
        } else {
            unselectedColor
        },
        animationSpec = tween(
            durationMillis = 1000,
        ), label = ""
    )
    val width: Dp by animateDpAsState(
        targetValue = if (isSelected) {
            indicatorSelectedLength.dp
        } else {
            size.dp
        },
        animationSpec = tween(
            durationMillis = 350,
        ), label = ""
    )
    Row {
        Box(
            modifier =
            Modifier
                .size(
                    width = width,
                    height = size.dp
                )
                .clip(CircleShape)
                .background(
                    color
                )
        )
        Spacer(modifier = Modifier.width(spacer.dp))
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewListIndicator() {
    KitchenPalTheme {
        var selectedPage by remember { mutableIntStateOf(0) }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            ListIndicator(
                count = 5,
                size = 8,
                spacer = 8,
                selectedIndex = selectedPage,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(36.dp))

            Button(
                onClick = { if (selectedPage != 4) selectedPage++ else selectedPage = 0 },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Click me")
            }

            Spacer(modifier = Modifier.height(80.dp))
        }

    }
}