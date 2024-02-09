package com.core.designsystem.component.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.designsystem.R
import com.core.designsystem.theme.Dimens
import com.core.designsystem.theme.KitchenPalTheme
import com.core.designsystem.theme.md_theme_light_disable_button
import com.core.designsystem.theme.md_theme_light_disable_button_content


@Composable
fun OutlineTextIconButton(
    modifier: Modifier = Modifier,
    isEnable: Boolean = true,
    outlineColor: Color = MaterialTheme.colorScheme.outline,
    contentColor: Color = MaterialTheme.colorScheme.primary,
    backgroundColor: Color = Color.Transparent,
    disabledContainerColor: Color = md_theme_light_disable_button,
    disabledContentColor: Color = md_theme_light_disable_button_content,
    text: String,
    @DrawableRes leadingIcon: Int? = null,
    @DrawableRes trailingIcon: Int? = null,
    onButtonClickListener: () -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        enabled = isEnable,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = contentColor,
            containerColor = backgroundColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor
        ),
        border = BorderStroke(1.dp, if (isEnable) outlineColor else disabledContainerColor),
        onClick = onButtonClickListener
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (leadingIcon != null) {
                Image(
                    modifier = Modifier
                        .size(18.dp),
                    painter = painterResource(id = leadingIcon),
                    contentDescription = "leading icon",
                )
            }

            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(
                    horizontal =
                    if (trailingIcon != null || leadingIcon != null)
                        Dimens.spaceSmall
                    else
                        Dimens.default
                )
            )

            if (trailingIcon != null) {
                Image(
                    modifier = Modifier
                        .size(18.dp),
                    painter = painterResource(id = trailingIcon),
                    contentDescription = "leading icon",
                )
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewOutlineTextIconButton() {
    KitchenPalTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlineTextIconButton(text = "Outline Button") {
            }

            OutlineTextIconButton(text = "Outline Button", isEnable = false) {
            }

            OutlineTextIconButton(
                text = "label ",
                trailingIcon = R.drawable.ic_google,
                modifier = Modifier.fillMaxWidth()
            ) {
            }

            OutlineTextIconButton(
                text = "label ",
                leadingIcon = R.drawable.ic_google,
                modifier = Modifier.fillMaxWidth()
            ) {
            }
            OutlineTextIconButton(
                text = "label ",
                trailingIcon = R.drawable.ic_add,
                isEnable = false
            ) {
            }
        }
    }
}