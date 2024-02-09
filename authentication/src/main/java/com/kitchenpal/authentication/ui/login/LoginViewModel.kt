package com.kitchenpal.authentication.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor() : ViewModel() {

    private var emailAddress: String = ""
    private var password: String = ""

    private var _loginState =
        MutableStateFlow(LoginState<String>())
    val loginState = _loginState.asStateFlow()

    fun handleIntent(intent: LoginEvent) {
        when (intent) {
            is LoginEvent.EmailAddressChanged -> {
                emailAddress = intent.emailAddress
                _loginState.value = _loginState.value.copy(emailAddress = emailAddress)
            }
            is LoginEvent.PasswordChanged -> {
                password = intent.password
                _loginState.value = _loginState.value.copy(password = password)
            }
            is LoginEvent.SignInClicked -> {
                _loginState.value = _loginState.value.copy(isLoading = true)
                signIn(intent.signInWithGmail)
            }
        }
    }

    private fun signIn(signInWithGmail: Boolean = false) {
        //do something in response set login state
        _loginState.value = _loginState.value.copy(isLoading = true)
        viewModelScope.launch {
            delay(3000)
            _loginState.value =
                _loginState.value.copy(isLoading = false, success = "Loading success")
        }
    }
}