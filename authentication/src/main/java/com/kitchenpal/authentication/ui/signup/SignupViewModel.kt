package com.kitchenpal.authentication.ui.signup

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
internal class SignupViewModel @Inject constructor() :
    BaseViewModel<SignupEvent, SignUpViewState, SignupSingleEvent>() {
    private var _viewState = MutableStateFlow(SignUpViewState())
    override val viewState = _viewState.asStateFlow()

    override fun processIntent(intent: SignupEvent) {
        when (intent) {
            is SignupEvent.EmailAddressChanged -> {
                _viewState.update {
                    it.copy(emailAddress = intent.emailAddress)
                }
            }
            is SignupEvent.PasswordChanged -> {
                _viewState.update {
                    it.copy(password = intent.password)
                }
            }
            is SignupEvent.UsernameChanged -> {
                _viewState.update {
                    it.copy(username = intent.username)
                }
            }
            is SignupEvent.TermsAndConditionChanged -> {
                _viewState.update {
                    it.copy(termsAndConditionChecked = intent.checked)
                }
            }
            is SignupEvent.SignInClicked -> {
                signup(intent.signupWithGmail)
            }
        }
    }


    private fun signup(signupWithGmail: Boolean = false) {
        if(signupWithGmail) {
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
            sendSingleEvent(SignupSingleEvent.UserCreated)
        }
    }
}