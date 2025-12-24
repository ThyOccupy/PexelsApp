package com.example.pexelsapp.presentation.common.components.toolbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.pexelsapp.R
import com.example.pexelsapp.presentation.common.drawable.PexelsIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit
) {
    var isActive by remember { mutableStateOf(false) }
/*    val debounce = 500L
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(query) {
        if (isActive && query.isNotEmpty()) {
            coroutineScope.launch {
                delay(debounce)
                onQueryChange(query)
            }
        }
    }*/

    SearchBar(
        inputField = {
            SearchBarDefaults.InputField(
                query = query,
                onQueryChange = onQueryChange,
                onSearch = {
                    isActive = false
                    onQueryChange(it)
                },
                expanded = isActive,
                onExpandedChange = { isActive = it },
                placeholder = { Text(text = stringResource(R.string.search_hint)) },
                leadingIcon = {
                    Icon(
                        painter = PexelsIcons.Search,
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                },
                trailingIcon = {
                    if (isActive) {
                        IconButton(
                            onClick = {
                                if (query.isNotEmpty()) {
                                    onQueryChange("")
                                } else {
                                    isActive = false
                                }
                            }
                        ) {
                            Icon(
                                painter = PexelsIcons.Cansel,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                },
                colors = TextFieldDefaults.colors(
                    cursorColor = Color.Black
                ),
            )
        },
        expanded = false,
        onExpandedChange = { },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 12.dp),
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            dividerColor = Color.LightGray
        )
    ) {}
}