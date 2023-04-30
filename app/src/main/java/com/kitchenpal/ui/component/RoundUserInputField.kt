package com.kitchenpal.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.kitchenpal.R
import com.kitchenpal.ui.theme.Elevation
import com.kitchenpal.ui.theme.KitchenPalTheme
import androidx.compose.material3.IconButton as IconButton1


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoundUserInputField(
    modifier: Modifier = Modifier,
    value: String,
    isPassword: Boolean = false,
    maxChar: Int = Int.MAX_VALUE,
    placeholderText: String = "",
    onImeAction: (() -> Unit)? = null,
    inputType: InputType? = null,
    onValueChange: (String) -> Unit
) {
    var passwordVisibility by remember { mutableStateOf(isPassword.not()) }

    val shape = RoundedCornerShape(
        topStart = 64.dp,
        topEnd = 64.dp,
        bottomEnd = 64.dp,
        bottomStart = 64.dp
    )

    val colors = KitchenPalTheme.colors

    TextField(
        modifier = modifier
            .border(BorderStroke(1.dp, colors.inverseOnSurface), shape = shape)
            .background(colors.background, shape)
            .shadow(Elevation.Level_1, shape = shape),
        value = value,
        singleLine = true,
        visualTransformation = getVisualTransformation(isPassword, passwordVisibility),
        placeholder = { Text(text = placeholderText) },
        keyboardOptions = inputType?.get() ?: KeyboardOptions.Default,
        onValueChange = {
            if (it.length <= maxChar) onValueChange(it)
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = colors.background,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onImeAction?.invoke()
            },
            onNext = {
                onImeAction?.invoke()
            },
            onSend = {
                onImeAction?.invoke()
            },
        ),
        trailingIcon = {
            if (isPassword) {
                IconButton1(onClick = {
                    passwordVisibility = !passwordVisibility
                }) {
                    val imageVector =
                        ImageVector.vectorResource(id = if (passwordVisibility) R.drawable.ic_visibility_off else R.drawable.ic_visibility)
                    Icon(imageVector, "", tint = KitchenPalTheme.colors.primary)
                }
            }
        },
    )
}

private fun getVisualTransformation(isPassword: Boolean, passwordVisibility: Boolean) =
    if (isPassword && !passwordVisibility) PasswordVisualTransformation() else VisualTransformation.None


interface InputType {
    fun get(): KeyboardOptions
}

class PasswordInputType(private val imeAction: ImeAction? = null) : InputType {
    override fun get() =
        KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            keyboardType = KeyboardType.Password,
            autoCorrect = false,
        )
}

class TextInputType(private val imeAction: ImeAction? = null) : InputType {
    override fun get() =
        KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            autoCorrect = false,
        )
}

class EmailInputType(private val imeAction: ImeAction? = null) : InputType {
    override fun get() =
        KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            keyboardType = KeyboardType.Email,
            autoCorrect = false,
        )
}