package com.kitchenpal.authentication

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kitchenpal.authentication.domain.AuthenticationInitializeUseCase
import com.kitchenpal.chat.ChatListViewType
import com.kitchenpal.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class AuthenticationViewModel @Inject constructor(
    private val useCase: AuthenticationInitializeUseCase
) : ViewModel() {
    private val _usernameState = mutableStateOf("")
    val usernameState: State<String> = _usernameState

    private val _emailAddressState = mutableStateOf("")
    val emailAddressState: State<String> = _emailAddressState

    private val _passwordState = mutableStateOf("")
    val passwordState: State<String> = _passwordState

    private val _authenticationMessagesFlow: MutableList<ChatListViewType> =
        arrayListOf<ChatListViewType>().toMutableStateList()
    val authenticationMessagesFlow: List<ChatListViewType> = _authenticationMessagesFlow

    private val _loginState = MutableStateFlow<UIState<Boolean>>(UIState.Idle)
    val loginState: StateFlow<UIState<Boolean>> = _loginState

    private val _initUIState = MutableStateFlow<UIState<Nothing>>(UIState.Loading)
    val initUIState: StateFlow<UIState<Nothing>> = _initUIState

    fun getAuthenticationInitChatMessages() {
        viewModelScope.launch {
            _initUIState.value = UIState.Loading
            delay(3000)
            useCase(null).collect {
                _initUIState.value = UIState.Success()
                it.forEach { message ->
                    _authenticationMessagesFlow.add(
                        ChatListViewType.IncomingTextMessage(message)
                    )
                }
            }
        }
    }

    fun onUsernameChanged(username: String) {
        _usernameState.value = username
    }

    fun onEmailAddressChanged(emailAddressState: String) {
        _emailAddressState.value = emailAddressState
    }

    fun onPasswordChanged(password: String) {
        _passwordState.value = password
    }

    fun login() {
        viewModelScope.launch {
            _loginState.value = UIState.Loading
            delay(3000)
            _loginState.value = UIState.Success(true)
        }
    }
}