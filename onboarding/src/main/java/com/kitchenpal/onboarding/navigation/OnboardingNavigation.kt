package com.kitchenpal.onboarding.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.kitchenpal.navigation.NavigationConstant
import com.kitchenpal.onboarding.ui.OnboardingRoute

fun NavGraphBuilder.onboardingScreen(navController: NavHostController) {
    composable(NavigationConstant.onboardingRoute) {
        OnboardingRoute(onOnboardingDone = {
            navController.navigate(NavigationConstant.authenticationRoute) {}
        })
    }
}