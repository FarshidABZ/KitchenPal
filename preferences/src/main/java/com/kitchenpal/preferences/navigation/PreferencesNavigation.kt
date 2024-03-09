package com.kitchenpal.preferences.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kitchenpal.preferences.ui.PreferencesRoute

const val PREFERENCES_ROUTE = "preferences_route"

fun NavController.navigateToPreferences(clearTop: Boolean = false) {
    navigate(PREFERENCES_ROUTE) {
        if (clearTop) {
            launchSingleTop = true
            popUpTo(PREFERENCES_ROUTE) {
                inclusive = true
            }
        }
    }
}


fun NavGraphBuilder.preferencesScreen(preferencesDone: () -> Unit) {
    composable(PREFERENCES_ROUTE) {
        PreferencesRoute(preferencesDone)
    }
}