package com.kitchenpal.authentication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.kitchenpal.util.HandleBackPress

@Composable
fun Authentication(lifecycleOwner: LifecycleOwner) {
    val viewModel: AuthenticationViewModel = hiltViewModel()
    val authenticationState: MutableState<AuthenticationSate> =
        remember { mutableStateOf(AuthenticationSate.Init) }

    HandleBackPress {
        when (viewModel.authenticationState.value) {
            is AuthenticationSate.Finish -> viewModel.moveToInitScreen()
            else -> {}
        }
    }

    LaunchedEffect(Unit) {
        lifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.authenticationState.collect { newState ->
                authenticationState.value = newState
            }
        }
    }

    when (authenticationState.value) {
        is AuthenticationSate.Init -> AuthenticationInitScreen(viewModel)
        is AuthenticationSate.Login -> {}
        is AuthenticationSate.Email -> {}
        is AuthenticationSate.Finish -> AuthenticationFinishScreen(viewModel)
    }
}