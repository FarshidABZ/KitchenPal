package com.kitchenpal.authentication

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kitchenpal.R
import com.kitchenpal.authentication.domain.AuthenticationInitializeUseCase
import com.kitchenpal.chat.ChatListViewType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class AuthenticationViewModel @Inject constructor(private val useCase: AuthenticationInitializeUseCase) :
    ViewModel() {

    private val _usernameState = mutableStateOf("")
    val usernameState: State<String> = _usernameState

    private val _authenticationMessagesFlow: MutableList<ChatListViewType> =
        arrayListOf<ChatListViewType>().toMutableStateList()
    val authenticationMessagesFlow: List<ChatListViewType> = _authenticationMessagesFlow

    private var index = 1

    init {
        viewModelScope.launch {
            useCase(null).collect {
                _authenticationMessagesFlow.add(it)
            }
        }
    }

    fun onUsernameChanged(newText: String) {
        _usernameState.value = newText
    }

    fun authenticationInitList() = arrayListOf(
        ChatListViewType.IncomingImage(drawableId = R.drawable.ic_kitchen_pal),
        ChatListViewType.IncomingTextMessage("Welcome to KitchenPal"),
        ChatListViewType.IncomingTextMessage("I'm here to make cooking joyful and help you to enjoy your meal"),
        ChatListViewType.IncomingTextMessage("What should I call?")
    )
}