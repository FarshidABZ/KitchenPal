package com.kitchenpal.authentication.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.kitchenpal.authentication.ui.login.AuthenticationRoute
import com.kitchenpal.navigation.NavigationConstant

fun NavGraphBuilder.authenticationScreen(navController: NavHostController) {
    composable(NavigationConstant.authenticationRoute) {
        AuthenticationRoute(
            onAuthenticationDone = {
                navController.popBackStack()
            },
            onBackPressed = {
                navController.popBackStack()
            },
            viewModel = hiltViewModel()
        )
    }
}