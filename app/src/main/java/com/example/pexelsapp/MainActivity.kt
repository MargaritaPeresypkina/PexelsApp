package com.example.pexelsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.pexelsapp.presentation.navigation.NavGraph
import com.example.pexelsapp.ui.theme.PexelsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setupSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            PexelsAppTheme {
                NavGraph()
            }
        }
    }

    private fun setupSplashScreen() {
        val splashScreen = installSplashScreen()
        var keepSplashOn = true
        splashScreen.setKeepOnScreenCondition { keepSplashOn }

        lifecycleScope.launch {
            delay(2000)
            keepSplashOn = false
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PexelsAppTheme {
        Text("Hello Preview")
    }
}
