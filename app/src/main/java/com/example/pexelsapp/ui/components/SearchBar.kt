package com.example.pexelsapp.ui.components

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pexelsapp.R
import com.example.pexelsapp.ui.theme.PexelsAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit
) {
    var active by remember { mutableStateOf(false) }

    SearchBar(
        inputField = {
            SearchBarDefaults.InputField(
                query = query,
                onQueryChange = onQueryChange,
                onSearch = { active = false },
                expanded = active,
                onExpandedChange = { active = it },
                placeholder = { Text(text = "Search") },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "search icon",
                        tint = Color.Unspecified
                    )
                },
                trailingIcon = {
                    if (active) {
                        IconButton(
                            onClick = {
                                if (query.isNotEmpty()) {
                                    onQueryChange("")
                                } else {
                                    active = false
                                }
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_cancel),
                                contentDescription = "Clear search",
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
        expanded = active,
        onExpandedChange = { active = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 12.dp),
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            dividerColor = Color.LightGray
        )
    ) {

        // todo SearchResultsContent()

    }
}




@Preview(showBackground = true)
@Composable
fun LightPreviewSearchBar() {
    PexelsAppTheme(dynamicColor = false) {
        SearchBar(
            "", {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewSearchBar() {
    PexelsAppTheme(
        dynamicColor = false,
        darkTheme = true
    ) {
        SearchBar(
            "", {}
        )
    }
}