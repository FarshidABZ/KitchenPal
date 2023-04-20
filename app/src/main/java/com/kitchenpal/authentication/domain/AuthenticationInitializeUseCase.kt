package com.kitchenpal.authentication.domain

import com.kitchenpal.base.FlowUseCase
import com.kitchenpal.chat.ChatListViewType
import com.kitchenpal.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthenticationInitializeUseCase @Inject constructor(@IoDispatcher private val dispatcher: CoroutineDispatcher) :
    FlowUseCase<AuthenticationInitializeUseCase.Params, ChatListViewType>(dispatcher) {
    data class Params(val index: Int)

    override suspend fun getExecutable(params: Params?) = flow {
        var index = 1
        repeat(3) {
            delay(1000)
            emit(ChatListViewType.IncomingTextMessage("Farshid ${index++}"))
        }
    }
}