package com.example.pexelsapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.pexelsapp.ui.HeaderUiEntity

@Composable
fun Headers(
    headers: List<HeaderUiEntity>,
    onHeaderClick: (HeaderUiEntity) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 12.dp)
    ) {
        items(headers.size) {index ->
            Header(
                header = headers[index],
                onClick = {onHeaderClick(headers[index])}
            )

            if (index != headers.lastIndex) {
                Spacer(Modifier.width(12.dp))
            }
        }
    }
}

@Composable
fun Header(
    header: HeaderUiEntity,
    onClick: () -> Unit
) {
    val boxColor : Color
    val textColor: Color

    if(header.isSelected) {
        boxColor = MaterialTheme.colorScheme.primaryContainer
        textColor = MaterialTheme.colorScheme.onPrimaryContainer
    }
    else {
        boxColor = MaterialTheme.colorScheme.secondaryContainer
        textColor = MaterialTheme.colorScheme.onSecondaryContainer
    }

    Text(
        text = header.name,
        color = textColor,
        modifier = Modifier
            .background(
                color = boxColor,
                shape = RoundedCornerShape(100)
            )
            .clip(RoundedCornerShape(100))
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 10.dp)

    )
}