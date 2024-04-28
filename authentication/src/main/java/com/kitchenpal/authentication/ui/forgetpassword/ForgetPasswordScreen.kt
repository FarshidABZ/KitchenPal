package com.kitchenpal.authentication.ui.forgetpassword

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
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kitchenpal.authentication.R
import com.kitchenpal.core.designsystem.component.button.FilledButton
import com.kitchenpal.core.designsystem.component.inputfield.EmailTextField
import com.kitchenpal.core.designsystem.theme.Dimens
import com.kitchenpal.core.designsystem.theme.KitchenPalTheme
import com.kitchenpal.core.designsystem.theme.colorOnSurfaceVariant2
import com.kitchenpal.core.ui.collectInLaunchedEffectWithLifecycle

@Composable
internal fun ForgetPasswordRoute(
    onResetPasswordDone: () -> Unit,
    onLoginClicked: () -> Unit,
    onBackClicked: () -> Unit,
    viewModel: ForgetPasswordViewModel = hiltViewModel()
) {
    viewModel.singleEvent.collectInLaunchedEffectWithLifecycle { event ->
        when (event) {
            is SingleEvent.PasswordReset -> {
            }

            is SingleEvent.Failed -> {

            }
        }
    }

    val uiState by viewModel.viewState.collectAsStateWithLifecycle()
    ForgetPasswordScreen(
        uiState = uiState,
        onAction = viewModel::processIntent,
        onBackClicked,
        onLoginClicked
    )
}

@Composable
private fun ForgetPasswordScreen(
    uiState: ViewState,
    onAction: (ResetPasswordEvent) -> Unit,
    onBackClicked: () -> Unit,
    onLoginClicked: () -> Unit
) {
    KitchenPalTheme {
        Scaffold(topBar = { ForgetPasswordTopBar(onBackClicked) }
        ) { paddingValues ->
            ForgetPasswordContent(paddingValues, uiState, onAction, onLoginClicked)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ForgetPasswordTopBar(onBackClicked: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {},
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(MaterialTheme.colorScheme.background),
        navigationIcon = {
            Image(
                modifier = Modifier
                    .clickable { onBackClicked() }
                    .size(40.dp)
                    .padding(Dimens.spaceSmall),
                painter = painterResource(com.kitchenpal.core.designsystem.R.drawable.ic_arrow_left),
                contentDescription = ""
            )
        })
}

@Composable
private fun ForgetPasswordContent(
    paddingValues: PaddingValues,
    uiState: ViewState,
    onAction: (ResetPasswordEvent) -> Unit,
    onLoginClicked: () -> Unit
) {
    BackgroundImage()
    ForgetPasswordForm(paddingValues, uiState, onAction, onLoginClicked)
}

@Composable
private fun ForgetPasswordForm(
    paddingValues: PaddingValues,
    uiState: ViewState,
    onAction: (ResetPasswordEvent) -> Unit,
    onLoginClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .imePadding()
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.spaceXXXLarge),
            text = stringResource(R.string.reset_password_title),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.spaceXXXLarge, vertical = Dimens.spaceSmall),
            text = stringResource(R.string.reset_password_description),
            style = MaterialTheme.typography.bodyLarge,
            color = colorOnSurfaceVariant2
        )

        EmailTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = Dimens.spaceXXXLarge,
                    start = Dimens.spaceXXXLarge,
                    end = Dimens.spaceXXXLarge
                ),
            value = uiState.emailAddress,
            onValueChange = {
                onAction(ResetPasswordEvent.EmailAddressChanged(it))
            },
            label = stringResource(id = R.string.email_address),
        )

        FilledButton(
            text = stringResource(id = R.string.reset_password_title),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = Dimens.spaceXLarge,
                    end = Dimens.spaceXLarge,
                    top = Dimens.spaceXXXLarge
                )
        ) {

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.spaceXLarge)
                .clickable { onLoginClicked() },
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.remember_your_password),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                modifier = Modifier.padding(start = Dimens.spaceXXSmall),
                text = stringResource(id = R.string.sign_in),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
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

@Preview(showSystemUi = true)
@Composable
private fun ForgetPasswordScreenPreview() {
    KitchenPalTheme {
        ForgetPasswordScreen(uiState = ViewState(), onAction = {}, onBackClicked = { }) {

        }
    }
}