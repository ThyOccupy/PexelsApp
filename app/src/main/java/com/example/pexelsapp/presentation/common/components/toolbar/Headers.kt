package com.example.pexelsapp.presentation.common.components.toolbar

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
import com.example.pexelsapp.presentation.model.HeaderUiEntity

@Composable
fun Headers(
    headers: List<HeaderUiEntity>,
    query: String,
    onHeaderClick: (HeaderUiEntity) -> Unit
) {
    val modifiedHeaders = headers.map { header ->
        header.copy(isSelected = header.name.equals(query, ignoreCase = true))
    }

    LazyRow(
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 12.dp)
    ) {
        items(modifiedHeaders.size) { index ->
            Header(
                header = modifiedHeaders[index],
                onClick = {header ->
                    header.isSelected !=header.isSelected
                    onHeaderClick(header)
                }
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
    onClick: (HeaderUiEntity) -> Unit
) {
    val boxColor: Color
    val textColor: Color

    if (header.isSelected) {
        boxColor = MaterialTheme.colorScheme.primaryContainer
        textColor = MaterialTheme.colorScheme.onPrimaryContainer
    } else {
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
            .clickable { onClick(header) }
            .padding(horizontal = 20.dp, vertical = 10.dp)

    )
}