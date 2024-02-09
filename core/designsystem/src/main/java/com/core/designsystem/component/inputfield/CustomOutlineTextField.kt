package com.core.designsystem.component.inputfield

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.core.designsystem.theme.Radius
import com.core.designsystem.theme.colorOnSurfaceVariant3

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlineTextField(
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions,
    enabled: Boolean,
    isError: Boolean,
    value: String,
    supportingText: String? = null,
    label: String? = null,
    @DrawableRes leadingIconId: Int? = null,
    @DrawableRes trailingIconId: Int? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit,
    onTrailingIconClicked: (() -> Unit)? = null
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused = interactionSource.collectIsFocusedAsState().value

    val focusManager = LocalFocusManager.current

    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        singleLine = true,
        textStyle = MaterialTheme.typography.bodyLarge,
        interactionSource = interactionSource,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
    ) {
        OutlinedTextFieldDefaults.DecorationBox(
            value = value,
            innerTextField = it,
            singleLine = true,
            enabled = enabled,
            interactionSource = interactionSource,
            visualTransformation = visualTransformation,
            label = {
                if (!label.isNullOrEmpty()) {
                    Text(
                        label,
                        style = MaterialTheme.typography.bodyLarge,
                        color = if (isError && enabled)
                            MaterialTheme.colorScheme.error
                        else if (isFocused) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            colorOnSurfaceVariant3
                        },
                    )
                }
            },
            supportingText = {
                if (!supportingText.isNullOrEmpty()) {
                    Text(
                        supportingText,
                        style = MaterialTheme.typography.bodySmall,
                        color = if (isError && enabled)
                            MaterialTheme.colorScheme.error
                        else if (enabled)
                            MaterialTheme.colorScheme.onSurfaceVariant
                        else
                            colorOnSurfaceVariant3
                    )
                }
            },
            leadingIcon = {
                if (leadingIconId != null) {
                    Image(
                        painter = painterResource(id = leadingIconId),
                        colorFilter = if (isFocused) {
                            ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant)
                        } else {
                            ColorFilter.tint(colorOnSurfaceVariant3)
                        },
                        contentDescription = "",
                    )
                }
            },
            trailingIcon = {
                if (trailingIconId != null) {
                    Image(
                        modifier = Modifier.clickable {
                            onTrailingIconClicked?.invoke()
                        },
                        painter = painterResource(id = trailingIconId),
                        colorFilter = if (isFocused) {
                            ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant)
                        } else {
                            ColorFilter.tint(colorOnSurfaceVariant3)
                        },
                        contentDescription = "",
                    )
                }
            },
            container = {
                OutlinedTextFieldDefaults.ContainerBox(
                    enabled,
                    isError,
                    interactionSource = interactionSource,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
                        errorBorderColor = MaterialTheme.colorScheme.error,
                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
                    ),
                    shape = RoundedCornerShape(Radius.spaceSmall),
                    focusedBorderThickness = 3.dp,
                    unfocusedBorderThickness = 1.dp
                )
            }
        )
    }
}