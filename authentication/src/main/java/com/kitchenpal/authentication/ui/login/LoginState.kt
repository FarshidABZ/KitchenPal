package com.kitchenpal.authentication.ui.login

data class LoginState<T>(
    val isLoading: Boolean = false,
    val emailAddress: String = "",
    val password: String = "",
    val success: T? = null,
    val failed: T? = null
)