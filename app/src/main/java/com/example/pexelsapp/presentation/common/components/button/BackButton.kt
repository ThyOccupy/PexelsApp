package com.example.pexelsapp.presentation.common.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.pexelsapp.R

@Composable
fun BackButton(
    icon: Painter,
    onBackPressed: () -> Unit
) {
    Icon(
        painter = icon,
        contentDescription = stringResource(R.string.back),
        tint = MaterialTheme.colorScheme.onSecondaryContainer,
        modifier = Modifier
            .size(40.dp)
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
            .clickable { onBackPressed() }
            .padding(10.dp)
    )
}