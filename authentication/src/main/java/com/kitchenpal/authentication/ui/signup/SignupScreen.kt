package com.kitchenpal.authentication.ui.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
internal fun SignupRoute(
    onSignupDone: () -> Unit,
    onLoginClicked: () -> Unit,
    onBackPressed: () -> Unit,
    viewModel: SignupViewModel = hiltViewModel()
) {
    viewModel.singleEvent.collectInLaunchedEffectWithLifecycle { event ->
        when (event) {
            is SignupSingleEvent.UserCreated -> {
                onSignupDone()
            }
            is SignupSingleEvent.SignupFailed -> {
                // show error
            }
        }
    }

    val uiState by viewModel.viewState.collectAsStateWithLifecycle()
    SignupScreen(
        uiState = uiState,
        onAction = viewModel::processIntent,
        onBackPressed,
        onLoginClicked
    )
}

@Composable
private fun SignupScreen(
    uiState: SignUpViewState,
    onAction: (SignupEvent) -> Unit,
    onBackPressed: () -> Unit,
    onLoginClicked: () -> Unit,
) {
    val localCoroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    KitchenPalTheme {
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            topBar = { LoginTopBar(onBackPressed) },
            content = { paddingValues ->
                LoginContent(
                    paddingValues,
                    uiState,
                    onAction,
                    snackbarHostState,
                    localCoroutineScope,
                    onLoginClicked
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoginTopBar(onBackPressed: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {},
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(MaterialTheme.colorScheme.background),
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
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
    uiState: SignUpViewState,
    onAction: (SignupEvent) -> Unit,
    snackbarHostState: SnackbarHostState,
    localCoroutineScope: CoroutineScope,
    onLoginClicked: () -> Unit,
) {
    BackgroundImage()
    SignupForm(
        paddingValues,
        uiState,
        onAction,
        localCoroutineScope,
        snackbarHostState,
        onLoginClicked
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SignupForm(
    paddingValues: PaddingValues,
    uiState: SignUpViewState,
    onAction: (SignupEvent) -> Unit,
    localCoroutineScope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    onLoginClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .imePadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = Dimens.spaceXXXLarge)
    ) {
        Text(
            text = stringResource(R.string.sign_up),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Text(
            modifier = Modifier
                .padding(top = Dimens.spaceSmall)
                .fillMaxWidth(),
            style = MaterialTheme.typography.bodyLarge,
            text = stringResource(R.string.sign_up_message),
            color = colorOnSurfaceVariant2
        )

        EmailTextField(
            modifier = Modifier
                .padding(top = Dimens.spaceXXXLarge)
                .fillMaxWidth(),
            value = uiState.emailAddress,
            onValueChange = {
                onAction(SignupEvent.EmailAddressChanged(it))
            },
            label = stringResource(id = R.string.email_address),
        )

        PasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            label = stringResource(id = R.string.password),
            value = uiState.password,
        ) {
            onAction(SignupEvent.PasswordChanged(it))
        }

        CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clickable {
                        onAction(SignupEvent.TermsAndConditionChanged(uiState.termsAndConditionChecked))
                    }, verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = uiState.termsAndConditionChecked,
                    onCheckedChange = { onAction(SignupEvent.TermsAndConditionChanged(uiState.termsAndConditionChecked)) },
                    colors = CheckboxDefaults.colors(uncheckedColor = colorOnSurfaceVariant2)
                )

                Text(
                    modifier = Modifier.padding(start = Dimens.spaceLarge),
                    text = "Accept terms & Condition",
                    style = MaterialTheme.typography.bodySmall,
                    color = colorOnSurfaceVariant2
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimens.spaceLarge)
        ) {

            FilledButton(
                modifier = Modifier
                    .fillMaxWidth(),
                text = if (uiState.loading) "" else stringResource(id = R.string.sign_in)
            ) {
                onAction(SignupEvent.SignInClicked(false))
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
                        .align(Alignment.Center),
                    color = MaterialTheme.colorScheme.onPrimary,
                    trackColor = MaterialTheme.colorScheme.primary,
                    strokeWidth = 2.dp,
                )
            }

        }

        TextDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimens.spaceXXXXLarge),
            text = stringResource(R.string.sign_in_with)
        )

        OutlineTextIconButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimens.spaceXXXXLarge),
            text = stringResource(id = R.string.sign_in),
            leadingIcon = com.kitchenpal.core.designsystem.R.drawable.ic_google
        ) {
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimens.spaceXXXXLarge)
                .clickable { onLoginClicked() },
            horizontalArrangement = Arrangement.Center
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
        SignupScreen(SignUpViewState(false, termsAndConditionChecked = true), {}, {}, {})
    }
}