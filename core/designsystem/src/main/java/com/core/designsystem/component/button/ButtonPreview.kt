package com.core.designsystem.component.button

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.designsystem.R
import com.core.designsystem.theme.KitchenPalTheme

@Preview(showBackground = true)
@Composable
fun AllButtonPreview() {
    KitchenPalTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            LazyColumn {
                item {
                    Text(text = "Filled Button", style = MaterialTheme.typography.headlineMedium)
                    Row {
                        FilledButton(text = "Label") {}
                        Spacer(modifier = Modifier.width(8.dp))
                        FilledButton(text = "Label ", leadingIcon = R.drawable.ic_plus_small) {}
                    }
                    Row {
                        FilledButton(text = "Label ", trailingIcon = R.drawable.ic_plus_small) {}
                        Spacer(modifier = Modifier.width(8.dp))
                        FilledButton(
                            text = "Filled Button Disable",
                            isEnable = false,
                            leadingIcon = R.drawable.ic_plus_small
                        ) {}
                        Spacer(modifier = Modifier.width(8.dp))
                    }

                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Outline Button", style = MaterialTheme.typography.headlineMedium)
                    Row {
                        OutlineButton(text = "Label") {}
                        Spacer(modifier = Modifier.width(8.dp))
                        OutlineButton(text = "Label ", leadingIcon = R.drawable.ic_plus_small) {}
                    }
                    Row {
                        OutlineButton(text = "Label ", trailingIcon = R.drawable.ic_plus_small) {}
                        Spacer(modifier = Modifier.width(8.dp))
                        OutlineButton(
                            text = "Filled Button Disable",
                            isEnable = false,
                            leadingIcon = R.drawable.ic_plus_small
                        ) {}
                    }
                }

                item {
                    Text(text = "Text Button", style = MaterialTheme.typography.headlineMedium)
                    Spacer(modifier = Modifier.height(16.dp))
                    Row {
                        TextButton(text = "Filled Button") {}
                        Spacer(modifier = Modifier.width(8.dp))
                        TextButton(text = "Label ", leadingIcon = R.drawable.ic_plus_small) {}
                    }
                    Row {
                        TextButton(text = "Label ", trailingIcon = R.drawable.ic_plus_small) {}
                        Spacer(modifier = Modifier.width(8.dp))
                        TextButton(
                            text = "Filled Button Disable",
                            isEnable = false,
                            leadingIcon = R.drawable.ic_plus_small
                        ) {}
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }

                item {
                    Text(text = "Elevated Button", style = MaterialTheme.typography.headlineMedium)
                    Spacer(modifier = Modifier.height(16.dp))
                    Row {
                        ElevatedButton(text = "Label") {}
                        Spacer(modifier = Modifier.width(8.dp))
                        ElevatedButton(text = "Label ", leadingIcon = R.drawable.ic_plus_small) {}
                    }
                    Row {
                        ElevatedButton(text = "Label ", trailingIcon = R.drawable.ic_plus_small) {}
                        Spacer(modifier = Modifier.width(8.dp))
                        ElevatedButton(text = "Filled Button Disable", isEnable = false) {}
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }

                item {
                    Text(text = "Tonal Button", style = MaterialTheme.typography.headlineMedium)
                    Spacer(modifier = Modifier.height(16.dp))
                    Row {
                        TonalButton(text = "Label") {}
                        Spacer(modifier = Modifier.width(8.dp))
                        TonalButton(text = "Label ", leadingIcon = R.drawable.ic_plus_small) {}
                    }
                    Row {
                        TonalButton(text = "Label ", trailingIcon = R.drawable.ic_plus_small) {}
                        Spacer(modifier = Modifier.width(8.dp))
                        TonalButton(
                            text = "Label ",
                            leadingIcon = R.drawable.ic_plus_small,
                            isEnable = false
                        ) {}
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
        }
    }
}