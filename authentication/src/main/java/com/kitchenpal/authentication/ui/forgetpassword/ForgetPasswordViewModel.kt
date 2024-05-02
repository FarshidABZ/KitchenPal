package com.kitchenpal.authentication.ui.forgetpassword

import com.kitchenpal.core.common.base.mvibase.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
internal class ForgetPasswordViewModel @Inject constructor() :
    BaseViewModel<ResetPasswordEvent, ViewState, SingleEvent>() {
    private var _viewState = MutableStateFlow(ViewState())
    override val viewState: StateFlow<ViewState> = _viewState.asStateFlow()

    override fun processIntent(intent: ResetPasswordEvent) {
        when(intent) {
            is ResetPasswordEvent.ResetPasswordClicked -> {

            }

            is ResetPasswordEvent.EmailAddressChanged -> {

            }
        }
    }

}