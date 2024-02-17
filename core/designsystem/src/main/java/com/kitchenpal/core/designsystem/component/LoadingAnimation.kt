package com.kitchenpal.core.designsystem.component

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.kitchenpal.core.designsystem.theme.KitchenPalTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//object LoadingAnimation {
//    private val animations = arrayListOf<Animatable<Float, AnimationVector1D>>()
//
//    fun stop(lifeCycleOwner: LifecycleOwner) {
//        lifeCycleOwner.lifecycleScope.launch {
//            animations.forEach {
//                it.stop()
//            }
//        }
//    }
//
//    @SuppressLint("ComposableNaming")
//    @Composable
//    fun DotsLoadingAnimation(
//        dotsSize: Dp = 12.dp,
//        dotsColor: Color = KitchenPalTheme.colors.primary
//    ) {
//        val dots = listOf(
//            remember { Animatable(0f) },
//            remember { Animatable(0f) },
//            remember { Animatable(0f) },
//        )
//
//        dots.forEachIndexed { index, animatable ->
//            animations.add(animatable)
//            LaunchedEffect(animatable) {
//                delay(index * 300L)
//                animatable.animateTo(
//                    targetValue = 1f, animationSpec = infiniteRepeatable(
//                        animation = keyframes {
//                            durationMillis = 2000
//                            0.0f at 0 with LinearOutSlowInEasing // for 0-15 ms
//                            1.0f at 200 with LinearOutSlowInEasing // for 15-75 ms
//                            0.0f at 400 with LinearOutSlowInEasing // for 0-15 ms
//                            0.0f at 2000
//                        },
//                        repeatMode = RepeatMode.Restart,
//                    )
//                )
//            }
//        }
//
//        val dys = dots.map { it.value }
//
//        val travelDistance = with(LocalDensity.current) { dotsSize.toPx() * 2 }
//
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.Center
//        ) {
//            dys.forEachIndexed { index, dy ->
//                Box(
//                    Modifier
//                        .size(dotsSize)
//                        .graphicsLayer {
//                            translationY = -dy * travelDistance
//                        },
//                ) {
//                    Box(
//                        Modifier
//                            .fillMaxSize()
//                            .background(color = dotsColor, shape = CircleShape)
//                    )
//                }
//
//                if (index != dys.size - 1) {
//                    Spacer(modifier = Modifier.width(8.dp))
//                }
//            }
//        }
//    }
//
//    @SuppressLint("ComposableNaming")
//    @Composable
//    fun GrowingDotLoadingAnimation(
//        dotsSize: Dp = 12.dp,
//        dotsMaxHeight: Dp = 32.dp,
//        dotsColor: Color = KitchenPalTheme.colors.primary
//    ) {
//        val dots = listOf(
//            remember { Animatable(dotsSize.value) },
//            remember { Animatable(dotsSize.value) },
//            remember { Animatable(dotsSize.value) },
//        )
//
//        dots.forEachIndexed { index, animatable ->
//            animations.add(animatable)
//            LaunchedEffect(animatable) {
//                delay((index + 1) * 300L)
//                animatable.animateTo(
//                    targetValue = dotsMaxHeight.value,
//                    animationSpec = infiniteRepeatable(
//                        animation = tween(500, delayMillis = 100, easing = LinearEasing),
//                        repeatMode = RepeatMode.Reverse
//                    )
//                )
//            }
//        }
//
//        val dys = dots.map { it.value }
//
//        Row(modifier = Modifier.height(dotsMaxHeight)) {
//            dys.forEachIndexed { index, height ->
//                Box(
//                    Modifier.height(dotsMaxHeight),
//                    contentAlignment = Alignment.BottomStart
//                ) {
//                    Box(
//                        Modifier
//                            .size(width = dotsSize, height.dp)
//                            .background(color = dotsColor, shape = CircleShape)
//                    )
//                }
//
//                if (index != 2) {
//                    Spacer(modifier = Modifier.width(KitchenPalTheme.dimens.spaceMedium))
//                }
//            }
//        }
//    }
//}