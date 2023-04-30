package com.kitchenpal.authentication

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Authentication() {
    val viewModel: AuthenticationViewModel = hiltViewModel()
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "init"
    ) {
        composable("init") {
            AuthenticationInitScreen(viewModel = viewModel, navController)
        }

        composable("login") {
            LoginScreen(viewModel = viewModel, navController)
        }

        composable("finish") {
            AuthenticationFinishScreen(viewModel = viewModel)
        }
    }
}