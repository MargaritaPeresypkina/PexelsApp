package com.example.pexelsapp.presentation.navigation

import androidx.annotation.DrawableRes
import com.example.pexelsapp.R

sealed class BottomNavItem(
    val route: String,
    val title: String,
    @DrawableRes val activeIcon: Int,
    @DrawableRes val inactiveIcon: Int
) {
    object Home: BottomNavItem(
        route = "home",
        title = "Home",
        activeIcon = R.drawable.home_button_inactive,
        inactiveIcon = R.drawable.home_button_active
    )

    object Bookmarks: BottomNavItem(
        route = "bookmarks",
        title = "Bookmarks",
        activeIcon = R.drawable.bookmark_icon_bottom_bar_active,
        inactiveIcon = R.drawable.bookmark_icon_bottom_bar_inactive
    )
}