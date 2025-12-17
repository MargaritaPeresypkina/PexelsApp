package com.example.pexelsapp.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.pexelsapp.ui.theme.Red

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Bookmarks
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Surface(
        shadowElevation = 20.dp,
        color = Color.White
    ) {
        NavigationBar(
            containerColor = Color.White,
            tonalElevation = 0.dp,
            modifier = Modifier.height(64.dp)
        ) {
            items.forEach { item ->
                val isSelected = currentRoute == item.route

                NavigationBarItem(
                    selected = isSelected,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo("home") { inclusive = false }
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Box(
                            modifier = Modifier
                                .height(64.dp)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null
                                ) {
                                    navController.navigate(item.route) {
                                        popUpTo("home") { inclusive = false }
                                        launchSingleTop = true
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {


                            if (isSelected) {
                                Box(
                                    modifier = Modifier
                                        .align(Alignment.TopCenter)
                                        .width(24.dp)
                                        .height(2.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(Red)
                                )
                            }

                            Icon(
                                painter = painterResource(
                                    id = if (isSelected) item.activeIcon else item.inactiveIcon
                                ),
                                contentDescription = item.title,
                                modifier = Modifier.size(24.dp),
                                tint = Color.Unspecified
                            )
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent,
                        selectedIconColor = Color.Unspecified,
                        unselectedIconColor = Color.Unspecified,
                        selectedTextColor = Color.Unspecified,
                        unselectedTextColor = Color.Unspecified
                    )
                )
            }
        }
    }
}
