package com.example.pexelsapp.presentation.common.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.pexelsapp.presentation.common.drawable.PexelsIcons

@Composable
fun BookmarkButton(
    isBookmarked: Boolean,
    onClick: () -> Unit
) {
    var isBooked by remember {
        mutableStateOf(isBookmarked)
    }

    val icon = if (isBooked) PexelsIcons.BookmarkFilled
    else PexelsIcons.BookmarkOutline
    Box(
        modifier = Modifier
            .size(48.dp)
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = CircleShape
            )
            .clip(CircleShape)
            .clickable {
                isBooked = !isBooked
            }
            .padding(14.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = if (!isBooked) {
                MaterialTheme.colorScheme.onBackground
            } else MaterialTheme.colorScheme.primary
        )
    }
}