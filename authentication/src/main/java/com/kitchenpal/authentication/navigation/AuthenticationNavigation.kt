package com.kitchenpal.authentication.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kitchenpal.authentication.ui.AuthenticationRoute
import com.kitchenpal.navigation.NavigationConstant

fun NavGraphBuilder.authenticationScreen() {
    composable(NavigationConstant.authenticationRoute) {
        AuthenticationRoute(onAuthenticationDone = {

        })
    }
}