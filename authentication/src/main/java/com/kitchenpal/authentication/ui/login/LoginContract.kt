package com.kitchenpal.authentication.ui.login

import androidx.compose.runtime.Stable
import com.kitchenpal.core.common.base.mvibase.MviIntent
import com.kitchenpal.core.common.base.mvibase.MviSingleEvent
import com.kitchenpal.core.common.base.mvibase.MviViewState

internal sealed interface SingleEvent : MviSingleEvent {
    data object LoginSucceed : SingleEvent
    data class LoginFailed(val message: String) : SingleEvent
}

@Stable
internal data class ViewState(
    val loading: Boolean = false,
    val emailAddress: String = "",
    val password: String = "",
    val success: String = "",
    val error: String = ""
) : MviViewState

@Stable
internal sealed interface LoginEvent : MviIntent {
    data class EmailAddressChanged(val emailAddress: String) : LoginEvent
    data class PasswordChanged(val password: String) : LoginEvent
    data class SignInClicked(val signInWithGmail: Boolean) : LoginEvent
}