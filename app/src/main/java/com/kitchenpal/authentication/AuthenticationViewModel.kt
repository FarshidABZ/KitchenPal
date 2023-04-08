package com.kitchenpal.authentication

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor() : ViewModel() {
    private val _usernameState = mutableStateOf("")
    val usernameState: State<String> = _usernameState

    private val _authenticationState = MutableStateFlow<AuthenticationSate>(AuthenticationSate.Init)
    val authenticationState: StateFlow<AuthenticationSate> = _authenticationState

    fun moveToFinishState() {
        _authenticationState.value = AuthenticationSate.Finish
    }

    fun moveToInitScreen() {
        _authenticationState.value = AuthenticationSate.Init
    }

    fun onUsernameChanged(newText: String) {
        _usernameState.value = newText
    }
}