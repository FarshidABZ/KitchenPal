package com.kitchenpal.authentication

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.kitchenpal.chat.ChatLazyColumn
import com.kitchenpal.ui.theme.Elevation
import com.kitchenpal.ui.theme.KitchenPalTheme

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

            width = Dimension.fillToConstraints
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

    ConstraintLayout(constraintSet = constraintSet, modifier = Modifier.fillMaxSize()) {
        ChatLazyColumn(
            modifier = Modifier.layoutId("chatList"),
            verticalArrangement = Arrangement.Bottom,
            items = viewModel.authenticationMessagesFlow
        )

        val usernameState by viewModel.usernameState

        UserInputField(
            modifier = Modifier.layoutId("userInput"),
            value = usernameState,
            viewModel = viewModel,
            maxChar = 15,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInputField(
    modifier: Modifier = Modifier,
    value: String,
    viewModel: AuthenticationViewModel,
    maxChar: Int = Int.MAX_VALUE,
    onValueChange: (String) -> Unit
) {
    val shape = RoundedCornerShape(
        topStart = 64.dp,
        topEnd = 64.dp,
        bottomEnd = 64.dp,
        bottomStart = 64.dp
    )

    val colors = KitchenPalTheme.colors

    TextField(
        value = value,
        onValueChange = {
            if (it.length <= maxChar) onValueChange(it)
        },
        keyboardOptions =
        KeyboardOptions(
            capitalization = KeyboardCapitalization.Sentences,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                viewModel.moveToFinishState()
            }
        ),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = colors.background,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        modifier = modifier
            .border(BorderStroke(1.dp, colors.inverseOnSurface), shape = shape)
            .background(colors.background, shape)
            .shadow(Elevation.Level_1, shape = shape),
        placeholder = {
            Text(text = "Username")
        }
    )
}