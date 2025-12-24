package com.example.pexelsapp.presentation.common.drawable

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.pexelsapp.R

object PexelsIcons {
    val HomeFilled: Painter @Composable get() = painterResource(R.drawable.ic_navigation_home_active)
    val HomeOutline: Painter @Composable get() = painterResource(R.drawable.ic_navigation_home_inactive)
    val BookmarkFilled: Painter @Composable get() = painterResource(R.drawable.ic_bookmark_active)
    val BookmarkOutline: Painter @Composable get() = painterResource(R.drawable.ic_bookmark_inactive)
    val Search: Painter @Composable get() = painterResource(R.drawable.ic_search)
    val Cansel: Painter @Composable get() = painterResource(R.drawable.ic_cancel)
    val NoNetwork: Painter @Composable get() = painterResource(R.drawable.ic_internet_off)
    val Back: Painter @Composable get() = painterResource(R.drawable.ic_back)
    val Download: Painter @Composable get() = painterResource(R.drawable.ic_download)
}