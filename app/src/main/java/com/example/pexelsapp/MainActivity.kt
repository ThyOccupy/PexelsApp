package com.example.pexelsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.pexelsapp.ui.screen.MainScreen
import com.example.pexelsapp.ui.theme.PexelsAppTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PexelsAppTheme(
                dynamicColor = false
            ) {
                    installSplashScreen()
                    MainScreen(viewModel)
                }
            }
        }
    }