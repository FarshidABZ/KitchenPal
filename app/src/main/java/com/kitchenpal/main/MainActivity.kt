package com.kitchenpal.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.kitchenpal.core.designsystem.theme.KitchenPalTheme
import com.kitchenpal.main.navigation.KitchenPalApp
import com.kitchenpal.main.navigation.rememberKitchenPalAppState
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