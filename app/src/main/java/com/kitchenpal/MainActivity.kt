package com.kitchenpal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kitchenpal.core.designsystem.theme.KitchenPalTheme
import com.kitchenpal.home.navigation.KitchenPalApp
import com.kitchenpal.home.navigation.rememberKitchenPalAppState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val appState = rememberKitchenPalAppState()
            KitchenPalTheme {
                KitchenPalApp(appState)
            }
        }
    }
}