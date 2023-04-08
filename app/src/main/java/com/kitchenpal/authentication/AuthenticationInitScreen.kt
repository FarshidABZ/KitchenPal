package com.kitchenpal.authentication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthenticationInitScreen(viewModel: AuthenticationViewModel) {
    val constraintSet = ConstraintSet {
        val chatList = createRefFor("chatList")
        val signupButton = createRefFor("signupButton")
        val loginButton = createRefFor("login")
        val userInput = createRefFor("userInput")

        constrain(loginButton) {
            bottom.linkTo(parent.bottom, margin = 24.dp)
            end.linkTo(parent.end, margin = 24.dp)
        }

        constrain(signupButton) {
            bottom.linkTo(loginButton.top, margin = 16.dp)
            end.linkTo(parent.end, margin = 24.dp)
        }

        constrain(userInput) {
            bottom.linkTo(signupButton.top, margin = 24.dp)
            end.linkTo(parent.end, margin = 24.dp)
            start.linkTo(parent.start, margin = 24.dp)
        }

        constrain(chatList) {
            top.linkTo(parent.top, margin = 24.dp)
            bottom.linkTo(userInput.top, margin = 24.dp)
            end.linkTo(parent.end, margin = 24.dp)
            start.linkTo(parent.start, margin = 24.dp)

            height = Dimension.fillToConstraints
            width = Dimension.fillToConstraints
        }
    }

    val listState = rememberLazyListState()

    ConstraintLayout(constraintSet = constraintSet, modifier = Modifier.fillMaxSize()) {
        LazyColumn(state = listState, modifier = Modifier
            .layoutId("chatList"), verticalArrangement = Arrangement.Bottom, content = {
            item {
                Text(text = "Chat item")
            }
        })

        val usernameState by viewModel.usernameState

        TextField(
            modifier = Modifier.layoutId("userInput"),
            value = usernameState,
            onValueChange = { newText ->
                viewModel.onUsernameChanged(newText)
            })

        Button(
            modifier = Modifier.layoutId("signupButton"),
            onClick = { viewModel.moveToFinishState() }) {
            Text(text = "Get Start")
        }

        Button(modifier = Modifier.layoutId("login"),
            onClick = { viewModel.moveToFinishState() }) {
            Text(text = "Already have account")
        }
    }
}

@Composable
private fun ChatItems() {

}