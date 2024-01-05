package com.kitchenpal.authentication.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AuthenticationRoute(
    onAuthenticationDone: () -> Unit,
    viewModel: AuthenticationViewModel = hiltViewModel()
) {
    AuthenticationScreen()
}

@Composable
fun AuthenticationScreen() {
    Text(
        text = "Athentication",
        style = MaterialTheme.typography.displayLarge,
        color = MaterialTheme.colorScheme.onBackground
    )
}