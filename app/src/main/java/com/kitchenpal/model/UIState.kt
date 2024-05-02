package com.kitchenpal.model

sealed class UIState<out T> {
    data class Error(val error: Any? = null) : UIState<Any>()
    data class Success<out T>(val data: T? = null) : UIState<T>()
    object Empty : UIState<Nothing>()
    object Loading : UIState<Nothing>()
    object Idle : UIState<Nothing>()
}
