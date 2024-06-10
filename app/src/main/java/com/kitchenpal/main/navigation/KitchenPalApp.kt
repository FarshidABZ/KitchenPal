/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kitchenpal.main.navigation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kitchenpal.authentication.navigation.authenticationGraph
import com.kitchenpal.authentication.navigation.navigateAuth
import com.kitchenpal.core.designsystem.theme.Radius
import com.kitchenpal.core.share.recipe.RecipeSharedViewModel
import com.kitchenpal.onboarding.navigation.onboardingScreen
import com.kitchenpal.preferences.navigation.preferencesScreen


@Composable
fun KitchenPalApp(appState: KitchenPalState) {
    val navBackStackEntry by appState.navController.currentBackStackEntryAsState()
    val bottomBarState by rememberSaveable(navBackStackEntry) {
        mutableStateOf(getBottomBarVisibility(navBackStackEntry))
    }

    Scaffold(
        bottomBar = {
            if (bottomBarState) {
                KitchenPalBottomBar(
                    appState.topLevelDestinations,
                    appState::navigateToTopLevelDestination,
                    currentDestination = appState.currentDestination
                )
            }
        }
    ) { padding ->
        Row(
            Modifier
                .fillMaxSize()
                .consumeWindowInsets(padding)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal,
                    ),
                ),
        ) {
            NavigationFlow(appState)
        }
    }
}

@Composable
fun NavigationFlow(
    appState: KitchenPalState,
    startDestination: String = TOP_LEVEL_ROUTE,
) {
    val navController = appState.navController
    val sharedViewModel: RecipeSharedViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = startDestination) {
        topLevelScreens(sharedViewModel)
        onboardingScreen {
            clearBackStack(navController)
            appState.navigateToTopLevelDestination(TopLevelDestination.HOME)
        }
        authenticationGraph(
            navController = navController,
            onLoginDone = { appState.navigateToTopLevelDestination(TopLevelDestination.HOME) },
            onSignupDone = { appState.navigateToTopLevelDestination(TopLevelDestination.HOME) },
            onPasswordReset = (navController::navigateAuth),
            onBackClick = navController::popBackStack
        )
        preferencesScreen {
            appState.navigateToTopLevelDestination(TopLevelDestination.HOME)
        }
    }
}

fun clearBackStack(navController: NavHostController) {
    navController.popBackStack(
        navController.graph.findStartDestination().id,
        inclusive = true
    )
}


@Composable
fun KitchenPalBottomBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?
) {
    NavigationBar(
        containerColor = Color(0xFFFBFAFA),
        modifier = Modifier
            .fillMaxWidth()
            .customShadow(Color.Black, offsetY = 2.dp, borderRadius = 32.dp, shadowRadius = 8.dp)
            .clip(
                RoundedCornerShape(
                    topStart = Radius.spaceXXXXLarge,
                    topEnd = Radius.spaceXXXXLarge
                )
            )
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)

            NavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    Icon(
                        painter = if (selected)
                            painterResource(id = destination.selectedIcon)
                        else
                            painterResource(id = destination.unselectedIcon),
                        contentDescription = destination.title
                    )
                })
        }
    }
}

fun Modifier.customShadow(
    color: Color = Color.Black,
    alpha: Float = 0.5f,
    shadowRadius: Dp = 8.dp,
    borderRadius: Dp = 0.dp,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 4.dp
): Modifier = this.then(
    Modifier.drawBehind {
        val paint = Paint().apply {
            this.color = color.copy(alpha = alpha)
            this.isAntiAlias = true
        }
        drawShadow(paint, shadowRadius, borderRadius, offsetX, offsetY)
    }
)

private fun DrawScope.drawShadow(
    paint: Paint,
    shadowRadius: Dp,
    borderRadius: Dp,
    offsetX: Dp,
    offsetY: Dp
) {
    drawIntoCanvas { canvas ->
        paint.asFrameworkPaint().setShadowLayer(
            shadowRadius.toPx(),
            offsetX.toPx(),
            offsetY.toPx(),
            paint.color.toArgb()
        )
        canvas.drawRoundRect(
            offsetX.toPx(),
            offsetY.toPx(),
            size.width + offsetX.toPx(),
            size.height + offsetY.toPx(),
            borderRadius.toPx(),
            borderRadius.toPx(),
            paint
        )
    }
}


private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.route, true) ?: false
    } ?: false