package com.kitchenpal.authentication.domain

import com.kitchenpal.R
import com.kitchenpal.base.FlowUseCase
import com.kitchenpal.di.IoDispatcher
import com.kitchenpal.util.ResourceManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthenticationInitializeUseCase @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val resourceManager: ResourceManager
) : FlowUseCase<AuthenticationInitializeUseCase.Params, MutableList<String>>(dispatcher) {
    data class Params(val index: Int)

    override suspend fun getExecutable(params: Params?) = flow {
        val strings = resourceManager.getStringArray(R.array.authentication_init)
        emit(strings.toMutableList())
    }
}