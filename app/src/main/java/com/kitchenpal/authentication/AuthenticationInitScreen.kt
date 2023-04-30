package com.kitchenpal.authentication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.kitchenpal.chat.ChatLazyColumn
import com.kitchenpal.model.UIState
import com.kitchenpal.ui.component.LoadingAnimation
import com.kitchenpal.ui.component.RoundUserInputField
import com.kitchenpal.ui.component.TextInputType
import com.kitchenpal.ui.theme.KitchenPalTheme

@Composable
internal fun AuthenticationInitScreen(
    viewModel: AuthenticationViewModel,
    navController: NavHostController
) {
    val uiState = viewModel.initUIState.collectAsState()

    when (uiState.value) {
        is UIState.Loading -> {
            ShowLoading()
            viewModel.getAuthenticationInitChatMessages()
        }

        else -> {
            AuthenticationInitContent(viewModel, navController)
        }
    }
}

@Composable
private fun ShowLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(KitchenPalTheme.dimens.spaceXLarge),
        contentAlignment = Alignment.BottomStart
    ) {
        LoadingAnimation.DotsLoadingAnimation(dotsSize = 12.dp)
    }
}

@Composable
private fun AuthenticationInitContent(
    viewModel: AuthenticationViewModel,
    navController: NavHostController
) {
    val dimens = KitchenPalTheme.dimens

    val constraintSet = ConstraintSet {
        val chatList = createRefFor("chatList")
        val signupButton = createRefFor("signupButton")
        val loginButton = createRefFor("login")
        val userInput = createRefFor("userInput")

        constrain(loginButton) {
            bottom.linkTo(parent.bottom, margin = dimens.spaceXXLarge)
            end.linkTo(parent.end, margin = dimens.spaceXXLarge)
        }

        constrain(signupButton) {
            bottom.linkTo(loginButton.top, margin = dimens.spaceXLarge)
            end.linkTo(parent.end, margin = dimens.spaceXXLarge)
        }

        constrain(userInput) {
            bottom.linkTo(signupButton.top, margin = dimens.spaceXXLarge)
            end.linkTo(parent.end, margin = dimens.spaceXXLarge)
            start.linkTo(parent.start, margin = dimens.spaceXXLarge)

            width = Dimension.fillToConstraints
        }

        constrain(chatList) {
            top.linkTo(parent.top, margin = dimens.spaceXXLarge)
            bottom.linkTo(userInput.top, margin = dimens.spaceXXLarge)
            end.linkTo(parent.end, margin = dimens.spaceXXLarge)
            start.linkTo(parent.start, margin = dimens.spaceXXLarge)

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

        RoundUserInputField(
            modifier = Modifier.layoutId("userInput"),
            value = usernameState,
            maxChar = 15,
            inputType = TextInputType(ImeAction.Done),
            onImeAction = {
                navController.navigate("finish")
            }
        ) { newText ->
            viewModel.onUsernameChanged(newText)
        }

        Button(
            modifier = Modifier.layoutId("signupButton"),
            onClick = {
                navController.navigate("finish")
            }) {
            Text(text = "Get Start")
        }

        Button(modifier = Modifier.layoutId("login"),
            onClick = {
                navController.navigate("login")
            }) {
            Text(text = "Already have account")
        }
    }
}