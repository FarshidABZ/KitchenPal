package com.kitchenpal.authentication.ui.signup

import androidx.compose.runtime.Immutable
import com.kitchenpal.core.common.base.mvibase.MviIntent
import com.kitchenpal.core.common.base.mvibase.MviSingleEvent
import com.kitchenpal.core.common.base.mvibase.MviViewState

internal sealed interface SignupSingleEvent : MviSingleEvent {
    data object UserCreated : SignupSingleEvent
    data class SignupFailed(val message: String) : SignupSingleEvent
}

@Immutable
internal data class SignUpViewState(
    val loading: Boolean = false,
    val emailAddress: String = "",
    val username: String = "",
    val termsAndConditionChecked: Boolean = false,
    val password: String = "",
    val error: String = ""
) : MviViewState

@Immutable
internal sealed interface SignupEvent : MviIntent {
    data class EmailAddressChanged(val emailAddress: String) : SignupEvent
    data class UsernameChanged(val username: String) : SignupEvent
    data class PasswordChanged(val password: String) : SignupEvent
    data class TermsAndConditionChanged(val checked: Boolean) : SignupEvent
    data class SignInClicked(val signupWithGmail: Boolean) : SignupEvent
}