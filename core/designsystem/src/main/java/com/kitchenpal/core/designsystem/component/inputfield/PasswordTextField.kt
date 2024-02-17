package com.kitchenpal.core.designsystem.component.inputfield

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.kitchenpal.core.designsystem.R

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
    enabled: Boolean = true,
    isError: Boolean = false,
    supportingText: String? = null,
    label: String? = null,
    value: String,
    onValueChange: (String) -> Unit,
) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    CustomOutlineTextField(
        modifier = modifier,
        keyboardOptions = keyboardOptions.copy(keyboardType = KeyboardType.Password),
        enabled = enabled,
        isError = isError,
        value = value,
        leadingIconId = R.drawable.ic_lock,
        trailingIconId = if (passwordVisible) R.drawable.ic_eye_slash else R.drawable.ic_eye,
        supportingText = supportingText,
        label = label,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        onValueChange = onValueChange,
        onTrailingIconClicked = {
            passwordVisible = passwordVisible.not()
        }
    )
}


@Preview(showSystemUi = true)
@Composable
fun PasswordTextFieldPreview() {
    PasswordTextField(value = "") {}
}