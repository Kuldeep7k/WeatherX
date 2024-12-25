package com.ghoul.weatherx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ghoul.weatherx.ui.theme.WeatherXTheme
import com.ghoul.weatherx.ui.theme.screens.WeatherScreen
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherXTheme {
                var showSplash by remember { mutableStateOf(true) }

                LaunchedEffect(showSplash) {
                    delay(3000)
                    showSplash = false
                }

                if (showSplash) {
                    SplashScreen()
                } else {
                    WeatherScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DecoratedComposablePreview() {
    WeatherScreen()
}
