package com.kitchenpal.core.common.base

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(val data: T?)