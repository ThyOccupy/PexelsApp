package com.example.pexelsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.pexelsapp.ui.screen.MainScreen
import com.example.pexelsapp.ui.theme.PexelsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PexelsAppTheme(
                dynamicColor = false
            ) {
                    installSplashScreen()
                    MainScreen()
                }
            }
        }
    }