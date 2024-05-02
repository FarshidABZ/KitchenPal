package com.kitchenpal.core.designsystem.component.divider

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.kitchenpal.core.designsystem.theme.KitchenPalTheme
import com.kitchenpal.core.designsystem.theme.colorOnSurfaceVariant2

@Composable
fun TextDivider(modifier: Modifier = Modifier, text: String) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(modifier = Modifier.weight(1f), color = MaterialTheme.colorScheme.primaryContainer)
        Text(
            text = text,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall,
            color = colorOnSurfaceVariant2
        )
        HorizontalDivider(modifier = Modifier.weight(1f), color = MaterialTheme.colorScheme.primaryContainer)
    }
}

@Preview(showSystemUi = true)
@Composable
fun TextDividerPreview() {
    KitchenPalTheme {
        TextDivider(text = "or sign in with")
    }
}