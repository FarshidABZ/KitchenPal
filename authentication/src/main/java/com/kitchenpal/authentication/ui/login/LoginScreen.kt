package com.kitchenpal.authentication.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kitchenpal.authentication.R
import com.kitchenpal.core.designsystem.component.button.FilledButton
import com.kitchenpal.core.designsystem.component.button.OutlineTextIconButton
import com.kitchenpal.core.designsystem.component.divider.TextDivider
import com.kitchenpal.core.designsystem.component.inputfield.EmailTextField
import com.kitchenpal.core.designsystem.component.inputfield.PasswordTextField
import com.kitchenpal.core.designsystem.theme.Dimens
import com.kitchenpal.core.designsystem.theme.KitchenPalTheme
import com.kitchenpal.core.designsystem.theme.colorOnSurfaceVariant2
import com.kitchenpal.core.ui.collectInLaunchedEffectWithLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
internal fun LoginRoute(
    onLoginDone: () -> Unit,
    onSignUpClicked: () -> Unit,
    onForgetPasswordClicked: () -> Unit,
    onBackClick: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    viewModel.singleEvent.collectInLaunchedEffectWithLifecycle { event ->
        when (event) {
            is SingleEvent.LoginSucceed -> {
                onLoginDone()
            }

            is SingleEvent.LoginFailed -> {
                // show error
            }
        }
    }

    val uiState by viewModel.viewState.collectAsStateWithLifecycle()
    LoginScreen(
        uiState = uiState,
        onAction = viewModel::processIntent,
        onForgetPasswordClicked,
        onBackClick,
        onSignUpClicked
    )
}

@Composable
private fun LoginScreen(
    uiState: ViewState,
    onAction: (LoginEvent) -> Unit,
    onForgetPasswordClicked: () -> Unit,
    onBackClick: () -> Unit,
    onSignUpClicked: () -> Unit
) {
    val localCoroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    KitchenPalTheme {
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            topBar = { LoginTopBar(onBackClick) },
            content = { paddingValues ->
                LoginContent(
                    paddingValues,
                    uiState,
                    onAction,
                    onSignUpClicked,
                    onForgetPasswordClicked,
                    snackbarHostState,
                    localCoroutineScope
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTopBar(onBackClick: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {},
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(MaterialTheme.colorScheme.background),
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(id = com.kitchenpal.core.designsystem.R.drawable.ic_arrow_left),
                    contentDescription = null
                )
            }
        }
    )
}


@Composable
private fun LoginContent(
    paddingValues: PaddingValues,
    uiState: ViewState,
    onAction: (LoginEvent) -> Unit,
    onSignUpClicked: () -> Unit,
    onForgetPasswordClicked: () -> Unit,
    snackbarHostState: SnackbarHostState,
    localCoroutineScope: CoroutineScope
) {
    BackgroundImage()
    LoginForm(
        paddingValues,
        uiState,
        onAction,
        onForgetPasswordClicked,
        localCoroutineScope,
        snackbarHostState,
        onSignUpClicked
    )
}

@Composable
private fun BackgroundImage() {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.weight(1f)) // Add this line
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(256.dp),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = com.kitchenpal.core.designsystem.R.drawable.bottom_background_shape),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.tertiary),
            alpha = 0.05f,
            contentDescription = ""
        )
    }
}

@Composable
private fun LoginForm(
    paddingValues: PaddingValues,
    uiState: ViewState,
    onAction: (LoginEvent) -> Unit,
    onForgetPasswordClicked: () -> Unit,
    localCoroutineScope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    onSignUpClicked: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .imePadding()
            .verticalScroll(rememberScrollState())
    ) {
        val (titleText,
            welcomeMessage,
            handImage,
            emailField,
            passwordField,
            forgetPassword,
            signInButton,
            divider,
            gmailButton,
            signUpButton,
            progressBar) = createRefs()

        Text(
            modifier = Modifier.constrainAs(titleText) {
                top.linkTo(parent.top)
                start.linkTo(parent.start, margin = Dimens.spaceXXXLarge)
                end.linkTo(handImage.start)
                width = Dimension.fillToConstraints
            },
            text = stringResource(R.string.welcome_back_pal),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Text(
            modifier = Modifier.constrainAs(welcomeMessage) {
                top.linkTo(titleText.bottom, margin = Dimens.spaceLarge)
                start.linkTo(parent.start, margin = Dimens.spaceXXXLarge)
                end.linkTo(handImage.start)
                width = Dimension.fillToConstraints
            },
            style = MaterialTheme.typography.bodyLarge,
            text = stringResource(R.string.login_to_your_account),
            color = colorOnSurfaceVariant2
        )

        Image(
            painter = painterResource(id = R.drawable.hand_3d),
            contentDescription = "",
            modifier = Modifier
                .constrainAs(handImage) {
                    end.linkTo(emailField.end, margin = Dimens.spaceSmall)
                    bottom.linkTo(emailField.top)
                })

        EmailTextField(
            modifier = Modifier.constrainAs(emailField) {
                top.linkTo(welcomeMessage.bottom, margin = 36.dp)
                start.linkTo(parent.start, margin = Dimens.spaceXXXLarge)
                end.linkTo(parent.end, margin = Dimens.spaceXXXLarge)
                width = Dimension.fillToConstraints
            },
            value = uiState.emailAddress,
            onValueChange = {
                onAction(LoginEvent.EmailAddressChanged(it))
            },
            label = stringResource(id = R.string.email_address),
        )

        PasswordTextField(
            modifier = Modifier.constrainAs(passwordField) {
                top.linkTo(emailField.bottom, margin = Dimens.default)
                start.linkTo(parent.start, margin = Dimens.spaceXXXLarge)
                end.linkTo(parent.end, margin = Dimens.spaceXXXLarge)
                width = Dimension.fillToConstraints
            },
            label = stringResource(id = R.string.password),
            value = uiState.password,
        ) {
            onAction(LoginEvent.PasswordChanged(it))
        }

        Text(
            modifier = Modifier
                .constrainAs(forgetPassword) {
                    top.linkTo(passwordField.bottom)
                    end.linkTo(parent.end, margin = Dimens.spaceXXXLarge)
                }
                .clickable {
                    onForgetPasswordClicked()
                },
            text = stringResource(R.string.forgot_your_password),
            color = colorOnSurfaceVariant2,
            style = MaterialTheme.typography.bodySmall
        )

        FilledButton(
            modifier = Modifier.constrainAs(signInButton) {
                top.linkTo(forgetPassword.bottom, margin = Dimens.spaceLarge)
                start.linkTo(parent.start, margin = Dimens.spaceXXXLarge)
                end.linkTo(parent.end, margin = Dimens.spaceXXXLarge)
                width = Dimension.fillToConstraints
            },
            text = if (uiState.loading) "" else stringResource(id = R.string.sign_in)
        ) {
            onAction(LoginEvent.SignInClicked(false))
        }

        if (uiState.loading) {
            LaunchedEffect(Unit) {
                localCoroutineScope.launch {
                    snackbarHostState.showSnackbar("loading")
                }
            }

            CircularProgressIndicator(
                modifier = Modifier
                    .size(24.dp)
                    .constrainAs(progressBar) {
                        centerTo(signInButton)
                    },
                color = MaterialTheme.colorScheme.onPrimary,
                trackColor = MaterialTheme.colorScheme.primary,
                strokeWidth = 2.dp,
            )
        }

        TextDivider(
            text = stringResource(R.string.sign_in_with),
            modifier = Modifier.constrainAs(divider) {
                top.linkTo(signInButton.bottom, margin = Dimens.spaceXXXXLarge)
                start.linkTo(parent.start, margin = Dimens.spaceXXXLarge)
                end.linkTo(parent.end, margin = Dimens.spaceXXXLarge)
                width = Dimension.fillToConstraints
            })

        OutlineTextIconButton(
            modifier = Modifier.constrainAs(gmailButton) {
                top.linkTo(divider.bottom, margin = Dimens.spaceXXXXLarge)
                start.linkTo(parent.start, margin = Dimens.spaceXXXLarge)
                end.linkTo(parent.end, margin = Dimens.spaceXXXLarge)
                width = Dimension.fillToConstraints
            },
            text = stringResource(id = R.string.sign_in),
            leadingIcon = com.kitchenpal.core.designsystem.R.drawable.ic_google
        ) {
        }

        Row(modifier = Modifier
            .constrainAs(signUpButton) {
                top.linkTo(gmailButton.bottom, margin = Dimens.spaceXXXXLarge)
                start.linkTo(parent.start, margin = Dimens.spaceXXXLarge)
                end.linkTo(parent.end, margin = Dimens.spaceXXXLarge)
                width = Dimension.fillToConstraints
            }
            .clickable { onSignUpClicked() }, horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Don’t have an account?",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                modifier = Modifier.padding(start = Dimens.spaceXXSmall),
                text = "Sign Up",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(
    name = "phone",
    device = Devices.PIXEL_4,
    backgroundColor = 0xFFFFFFFF,
    showBackground = true
)
@Composable
private fun Login() {
    KitchenPalTheme {
        LoginScreen(ViewState(), {}, {}, {}, {})
    }
}