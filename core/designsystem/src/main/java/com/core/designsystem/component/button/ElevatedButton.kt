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
import com.core.designsystem.theme.md_theme_light_disable_button
import com.core.designsystem.theme.md_theme_light_disable_button_content

@Composable
fun ElevatedButton(
    modifier: Modifier = Modifier,
    isEnable: Boolean = true,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    disabledContainerColor: Color = md_theme_light_disable_button,
    disabledContentColor: Color = md_theme_light_disable_button_content,
    text: String,
    @DrawableRes leadingIcon: Int? = null,
    @DrawableRes trailingIcon: Int? = null,
    onButtonClickListener: () -> Unit
) {
    androidx.compose.material3.ElevatedButton(
        modifier = modifier,
        enabled = isEnable,
        colors = ButtonDefaults.filledTonalButtonColors(
            containerColor = backgroundColor,
            contentColor = contentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor
        ),
        onClick = onButtonClickListener
    ) {
        Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
            if (leadingIcon != null) {
                Image(
                    modifier = Modifier
                        .padding(end = Dimens.spaceSmall)
                        .size(18.dp),
                    painter = painterResource(id = leadingIcon),
                    contentDescription = "leading icon",
                    colorFilter = ColorFilter.tint(if (isEnable) contentColor else disabledContentColor),
                )
            }

            Text(text = text, style = MaterialTheme.typography.labelLarge)

            if (trailingIcon != null) {
                Image(
                    modifier = Modifier
                        .padding(start = Dimens.spaceSmall)
                        .size(18.dp),
                    painter = painterResource(id = trailingIcon),
                    contentDescription = "leading icon",
                    colorFilter = ColorFilter.tint(if (isEnable) contentColor else disabledContentColor),
                )
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewElevatedButton() {
    KitchenPalTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ElevatedButton(text = "Elevated Button") {
            }
            ElevatedButton(text = "Elevated Button", leadingIcon = R.drawable.ic_plus_small) {
            }
            ElevatedButton(
                text = "Elevated Button",
                leadingIcon = R.drawable.ic_plus_small,
                isEnable = false
            ) {
            }
        }
    }
}