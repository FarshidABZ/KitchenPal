package com.kitchenpal.authentication.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kitchenpal.authentication.ui.login.LoginRoute
import com.kitchenpal.authentication.ui.signup.SignupRoute

const val AUTH = "auth"
const val LOGIN_ROUTE = "login_route"
const val SIGNUP_ROUTE = "signup_route"
const val FORGET_PASSWORD_ROUTE = "forget_password_route"

fun NavController.navigateAuth() {
    navigate(AUTH) {
        launchSingleTop = true
        popUpTo(AUTH) {
            inclusive = true
        }
    }
}

private fun NavController.navigateToLogin() {
    navigate(LOGIN_ROUTE) {
        launchSingleTop = true
        popUpTo(AUTH) {
            inclusive = true
        }
    }
}

private fun NavController.navigateToSignup() {
    navigate(SIGNUP_ROUTE) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.authenticationGraph(
    navController: NavController,
    onLoginDone: () -> Unit,
    onSignupDone: () -> Unit,
    onBackClick: () -> Unit,
) {
    navigation(startDestination = LOGIN_ROUTE, route = AUTH) {
        composable(route = LOGIN_ROUTE) {
            LoginRoute(
                onLoginDone = onLoginDone,
                onSignUpClicked = navController::navigateToSignup,
                onBackClick = onBackClick,
            )
        }

        composable(route = SIGNUP_ROUTE) {
            SignupRoute(
                onSignupDone = onSignupDone,
                onLoginClicked = navController::navigateToLogin,
                onBackPressed = onBackClick,
            )
        }

        composable(route = FORGET_PASSWORD_ROUTE) {
            SignupRoute(
                onSignupDone = onSignupDone,
                onLoginClicked = navController::navigateToLogin,
                onBackPressed = onBackClick,
            )
        }
    }
}
