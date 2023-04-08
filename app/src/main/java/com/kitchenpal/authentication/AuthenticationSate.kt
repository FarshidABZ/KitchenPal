package com.kitchenpal.authentication

import com.kitchenpal.model.UIState

sealed class AuthenticationSate : UIState() {
    object Init : AuthenticationSate()
    object Login : AuthenticationSate()
    object Email : AuthenticationSate()
    object Finish : AuthenticationSate()
}
