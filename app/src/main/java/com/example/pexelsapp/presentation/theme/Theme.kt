package com.example.pexelsapp.presentation.theme

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.WindowManager
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Red,
    secondary = GrayDarkTheme,
    background = Black,
    onBackground = White,
    primaryContainer = Red,
    onPrimaryContainer = White,
    secondaryContainer = LightGrayDarkTheme,
    onSecondaryContainer = White,
)

private val LightColorScheme = lightColorScheme(
    primary = Red,
    secondary = Gray,
    background = White,
    onBackground = Black,
    primaryContainer = Red,
    onPrimaryContainer = White,
    secondaryContainer = LightGray,
    onSecondaryContainer = Black
)

@Composable
fun PexelsAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme

    }
    // Setting up the system UI (status bar)
    val view: View = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val activity = view.context as Activity
            val window = activity.window

            window.apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    attributes.layoutInDisplayCutoutMode =
                        WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
                }
                WindowCompat.setDecorFitsSystemWindows(this, false)
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
            }
            activity.actionBar?.hide()
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}