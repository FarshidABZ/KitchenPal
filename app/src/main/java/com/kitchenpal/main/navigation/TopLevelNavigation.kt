package com.kitchenpal.main.navigation

import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation

const val TOP_LEVEL_ROUTE = "top_level_route"

const val HOME_ROUTE = "recipe_list_route"
const val CHAT_ROUTE = "CHAT_ROUTE"
const val FAVORITE_ROUTE = "FAVORITE_ROUTE"
const val PROFILE_ROUTE = "PROFILE_ROUTE"


fun NavController.navigateToTopLevels(popupToRoute: String) {
    navigate(TOP_LEVEL_ROUTE) {
        popUpTo(popupToRoute) {
            inclusive = true
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavController.navigateToHome(navOptions: NavOptions) =
    navigate(HOME_ROUTE, navOptions)

fun NavController.navigateToChat(navOptions: NavOptions) =
    navigate(CHAT_ROUTE, navOptions)

fun NavController.navigateToFavorite(navOptions: NavOptions) =
    navigate(FAVORITE_ROUTE, navOptions)

fun NavController.navigateToProfile(navOptions: NavOptions) =
    navigate(PROFILE_ROUTE, navOptions)

fun NavGraphBuilder.topLevelScreens() {
    navigation(startDestination = HOME_ROUTE, route = TOP_LEVEL_ROUTE) {
        composable(HOME_ROUTE) {
            Text(text = "Welcome to Kitchen pal list")
        }
        composable(CHAT_ROUTE) {
            Text(text = "Welcome to Chat")
        }
        composable(FAVORITE_ROUTE) {
            Text(text = "Welcome to Favorites")
        }
        composable(PROFILE_ROUTE) {
            Text(text = "Welcome to Profile")
        }
    }
}