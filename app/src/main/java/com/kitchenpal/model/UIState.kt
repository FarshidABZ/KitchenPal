package com.kitchenpal.model

open class UIState {
    data class Loading(val isLoading: Boolean) : UIState()
    data class Error(val error: Any)
    data class Success(val data: Any)
}
