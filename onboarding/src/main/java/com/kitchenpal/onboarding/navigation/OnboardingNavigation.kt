package com.kitchenpal.onboarding.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kitchenpal.onboarding.ui.OnboardingRoute

const val ONBOARDING_ROUTE = "onboarding_route"

fun NavGraphBuilder.onboardingScreen(onboardingDone: () -> Unit) {
    composable(ONBOARDING_ROUTE) {
        OnboardingRoute(onboardingDone)
    }
}