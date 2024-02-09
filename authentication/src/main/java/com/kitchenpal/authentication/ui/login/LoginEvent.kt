package com.kitchenpal.authentication.ui.login

sealed interface LoginEvent {
    data class EmailAddressChanged(val emailAddress: String) : LoginEvent
    data class PasswordChanged(val password: String) : LoginEvent
    data class SignInClicked(val signInWithGmail: Boolean) : LoginEvent
}