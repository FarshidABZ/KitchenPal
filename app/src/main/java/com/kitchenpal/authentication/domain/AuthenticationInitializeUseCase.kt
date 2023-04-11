package com.kitchenpal.authentication.domain

import com.kitchenpal.base.FlowUseCase
import com.kitchenpal.chat.ChatListViewType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthenticationInitializeUseCase @Inject constructor(private val dispatcher: CoroutineDispatcher) :
    FlowUseCase<Nothing, ChatListViewType>(dispatcher) {
    override suspend fun getExecutable(params: Nothing?) = flow {
        var index = 1
        repeat(3) {
            delay(1000)
            emit(ChatListViewType.IncomingTextMessage("Farshid ${index++}"))
        }
    }
}