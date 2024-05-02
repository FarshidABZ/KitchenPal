package com.kitchenpal.authentication.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kitchenpal.authentication.ui.forgetpassword.ForgetPasswordRoute
import com.kitchenpal.authentication.ui.login.LoginRoute
import com.kitchenpal.authentication.ui.signup.SignupRoute

const val AUTHENTICATION_ROUTE = "authentication"
const val LOGIN_ROUTE = "login_route"
const val SIGNUP_ROUTE = "signup_route"
const val FORGET_PASSWORD_ROUTE = "forget_password_route"

fun NavController.navigateAuth() {
    navigate(AUTHENTICATION_ROUTE) {
        launchSingleTop = true
        popUpTo(AUTHENTICATION_ROUTE) {
            inclusive = true
        }
    }
}

private fun NavController.navigateToLogin() {
    navigate(LOGIN_ROUTE) {
        launchSingleTop = true
        popUpTo(AUTHENTICATION_ROUTE) {
            inclusive = true
        }
    }
}

private fun NavController.navigateToSignup() {
    navigate(SIGNUP_ROUTE) {
        launchSingleTop = true
    }
}

private fun NavController.navigateToForgetPassword() {
    navigate(FORGET_PASSWORD_ROUTE) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.authenticationGraph(
    navController: NavController,
    onLoginDone: () -> Unit,
    onSignupDone: () -> Unit,
    onPasswordReset: () -> Unit,
    onBackClick: () -> Unit,
) {
    navigation(startDestination = LOGIN_ROUTE, route = AUTHENTICATION_ROUTE) {
        composable(route = LOGIN_ROUTE) {
            LoginRoute(
                onLoginDone = onLoginDone,
                onSignUpClicked = navController::navigateToSignup,
                onBackClick = onBackClick,
                onForgetPasswordClicked = navController::navigateToForgetPassword
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
            ForgetPasswordRoute(
                onLoginClicked = navController::navigateToLogin,
                onBackClicked = onBackClick,
                onResetPasswordDone = onPasswordReset
            )
        }
    }
}