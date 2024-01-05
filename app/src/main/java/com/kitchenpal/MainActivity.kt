package com.kitchenpal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.core.designsystem.theme.KitchenPalTheme
import com.kitchenpal.authentication.navigation.authenticationScreen
import com.kitchenpal.navigation.NavigationConstant
import com.kitchenpal.onboarding.navigation.onboardingScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KitchenPalTheme {
                val navController = rememberNavController()
                KitchenPalNavHost(navController)
            }
        }
    }
}

@Composable
fun KitchenPalNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = NavigationConstant.onboardingRoute,
) {
    NavHost(navController = navController, startDestination = startDestination) {
        onboardingScreen(navController)
        authenticationScreen()
    }
}

@Preview(showSystemUi = true)
@Composable
fun AppPreview() {
    KitchenPalTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Text(text = "Hello World", style = MaterialTheme.typography.bodyLarge)
        }
    }
}