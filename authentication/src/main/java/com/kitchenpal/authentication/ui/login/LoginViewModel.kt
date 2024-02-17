package com.kitchenpal.authentication.ui.login

import androidx.lifecycle.viewModelScope
import com.kitchenpal.core.common.base.mvibase.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor() : BaseViewModel<LoginEvent, ViewState, SingleEvent>() {
    private var _viewState = MutableStateFlow(ViewState())
    override val viewState = _viewState.asStateFlow()

    override fun processIntent(intent: LoginEvent) {
        when (intent) {
            is LoginEvent.EmailAddressChanged -> {
                _viewState.update {
                    it.copy(emailAddress = intent.emailAddress)
                }
            }
            is LoginEvent.PasswordChanged -> {
                _viewState.update {
                    it.copy(password = intent.password)
                }
            }
            is LoginEvent.SignInClicked -> {
                signIn(intent.signInWithGmail)
            }
        }
    }

    private fun signIn(signInWithGmail: Boolean = false) {
        if(signInWithGmail) {
            Unit
        }

        _viewState.update {
            it.copy(loading = true)
        }
        viewModelScope.launch {
            delay(3000)
            _viewState.update {
                it.copy(loading = false)
            }
            sendSingleEvent(SingleEvent.LoginSucceed)
        }
    }
}