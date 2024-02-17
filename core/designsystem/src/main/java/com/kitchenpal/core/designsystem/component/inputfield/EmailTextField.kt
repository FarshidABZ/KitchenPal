package com.kitchenpal.core.designsystem.component.inputfield

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.kitchenpal.core.designsystem.R

@Composable
fun EmailTextField(
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
    enabled: Boolean = true,
    isError: Boolean = false,
    supportingText: String? = null,
    label: String? = null,
    value: String,
    onValueChange: (String) -> Unit,
) {
    CustomOutlineTextField(
        modifier = modifier,
        keyboardOptions = keyboardOptions.copy(keyboardType = KeyboardType.Email),
        enabled = enabled,
        isError = isError,
        value = value,
        leadingIconId = R.drawable.ic_mail,
        supportingText = supportingText,
        label = label,
        onValueChange = onValueChange
    )
}

@Preview(showSystemUi = true)
@Composable
fun EmailTextFieldPreview() {
    EmailTextField(value = "", onValueChange = {})
}
