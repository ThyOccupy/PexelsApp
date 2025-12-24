package com.example.pexelsapp.presentation.navigation

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.pexelsapp.presentation.screen.root.NavigationItem.*

@Composable
fun NavigationBar(
    navigationState: NavigationState
) {

    val navBackStackEntry = navigationState.navHostController.currentBackStackEntryAsState()

    val items = listOf(Home, Bookmarked)

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background
    ) {
        items.forEach { item ->
            val isSelected = navBackStackEntry.value?.destination?.hierarchy?.any {
                it.route == item.screen.route
            } ?: false

            val indicatorWidth = animateDpAsState(
                targetValue = if (isSelected) 24.dp else 0.dp,
                animationSpec = tween(durationMillis = 300),
                label = "indicatorWidth"
            )

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    if (!isSelected) {
                        navigationState.navigateTo(item.screen.route)
                    }
                },
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .height(2.dp)
                                .width(indicatorWidth.value)
                                .background(
                                    color = if (isSelected) MaterialTheme.colorScheme.primary
                                    else Color.Transparent,
                                    shape = RoundedCornerShape(10.dp)
                                )
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Icon(
                            painter = item.iconInactive(),
                            contentDescription = null
                        )
                    }
                },
                colors = NavigationBarItemColors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    selectedIndicatorColor = Color.Unspecified,
                    unselectedIconColor = MaterialTheme.colorScheme.secondary,
                    unselectedTextColor = MaterialTheme.colorScheme.secondary,
                    disabledIconColor = MaterialTheme.colorScheme.secondary,
                    disabledTextColor = MaterialTheme.colorScheme.secondary
                )
            )

        }
    }
}