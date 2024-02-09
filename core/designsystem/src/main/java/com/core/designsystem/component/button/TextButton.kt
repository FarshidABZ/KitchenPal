package com.core.designsystem.component.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.designsystem.R
import com.core.designsystem.theme.Dimens
import com.core.designsystem.theme.KitchenPalTheme
import com.core.designsystem.theme.md_theme_light_disable_primary
import com.core.designsystem.theme.md_theme_light_disable_primary_content


@Composable
fun TextButton(
    modifier: Modifier = Modifier,
    isEnable: Boolean = true,
    text: String,
    backgroundColor: Color = Color.Transparent,
    contentColor: Color = MaterialTheme.colorScheme.primary,
    disabledContainerColor: Color = md_theme_light_disable_primary,
    disabledContentColor: Color = md_theme_light_disable_primary_content,
    @DrawableRes leadingIcon: Int? = null,
    @DrawableRes trailingIcon: Int? = null,
    onButtonClickListener: () -> Unit
) {
    androidx.compose.material3.TextButton(
        modifier = modifier,
        enabled = isEnable,
        colors = ButtonDefaults.textButtonColors(
            contentColor = contentColor,
            containerColor = backgroundColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor
        ),
        onClick = onButtonClickListener
    ) {
        val color = if (isEnable) contentColor else disabledContentColor
        Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
            if (leadingIcon != null) {
                Image(
                    modifier = Modifier
                        .size(18.dp),
                    painter = painterResource(id = leadingIcon),
                    colorFilter = ColorFilter.tint(color),
                    contentDescription = "leading icon",
                )
            }

            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge,
                color = color,
                modifier = Modifier.padding(horizontal = Dimens.spaceSmall)
            )

            if (trailingIcon != null) {
                Image(
                    modifier = Modifier
                        .size(18.dp),
                    painter = painterResource(id = trailingIcon),
                    colorFilter = ColorFilter.tint(color),
                    contentDescription = "leading icon",
                )
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewTextButton() {
    KitchenPalTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextButton(text = "Text Button") {
            }

            TextButton(text = "Text Button", leadingIcon = R.drawable.ic_add) {
            }

            TextButton(text = "Text Button", isEnable = false) {
            }

            TextButton(
                text = "Text Button",
                leadingIcon = R.drawable.ic_add,
                isEnable = false
            ) {
            }

            TextButton(
                text = "Text Button",
                trailingIcon = R.drawable.ic_add,
                isEnable = false
            ) {
            }
        }
    }
}