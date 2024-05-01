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

package com.kitchenpal.home.navigation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kitchenpal.authentication.navigation.authenticationGraph
import com.kitchenpal.authentication.navigation.navigateAuth
import com.kitchenpal.core.designsystem.theme.Radius
import com.kitchenpal.onboarding.navigation.ONBOARDING_ROUTE
import com.kitchenpal.onboarding.navigation.onboardingScreen
import com.kitchenpal.preferences.navigation.preferencesScreen


@Composable
fun KitchenPalApp(appState: KitchenPalState, modifier: Modifier = Modifier) {
    var bottomBarState by rememberSaveable { (mutableStateOf(true)) }
    val navBackStackEntry by appState.navController.currentBackStackEntryAsState()

    bottomBarState = getBottomBarVisibility(navBackStackEntry)

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
                .padding(padding)
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
    startDestination: String = ONBOARDING_ROUTE,
) {
    val navController = appState.navController
    NavHost(navController = navController, startDestination = startDestination) {
        topLevelScreens()
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
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
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

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.route, true) ?: false
    } ?: false