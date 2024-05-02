package com.kitchenpal.authentication.ui.forgetpassword

import androidx.compose.runtime.Stable
import com.kitchenpal.core.common.base.mvibase.MviIntent
import com.kitchenpal.core.common.base.mvibase.MviSingleEvent
import com.kitchenpal.core.common.base.mvibase.MviViewState

internal sealed interface SingleEvent : MviSingleEvent {
    data object PasswordReset : SingleEvent
    data class Failed(val message: String) : SingleEvent
}

@Stable
internal data class ViewState(
    val loading: Boolean = false,
    val emailAddress: String = "",
    val error: String = ""
) : MviViewState

@Stable
internal sealed interface ResetPasswordEvent : MviIntent {
    data class EmailAddressChanged(val emailAddress: String) : ResetPasswordEvent
    data object ResetPasswordClicked : ResetPasswordEvent
}