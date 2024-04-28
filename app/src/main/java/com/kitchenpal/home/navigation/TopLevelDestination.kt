package com.kitchenpal.home.navigation

import androidx.annotation.DrawableRes

enum class TopLevelDestination(
    val route: String,
    val title: String,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
) {
    HOME(
        route = HOME_ROUTE,
        title = "Home",
        selectedIcon = com.kitchenpal.core.designsystem.R.drawable.ic_home,
        unselectedIcon = com.kitchenpal.core.designsystem.R.drawable.ic_home_filled,
    ),
    CHAT(
        route = CHAT_ROUTE,
        title = "Chat",
        selectedIcon = com.kitchenpal.core.designsystem.R.drawable.ic_message_5,
        unselectedIcon = com.kitchenpal.core.designsystem.R.drawable.ic_message_filled,
    ),
    FAVORITE(
        route = FAVORITE_ROUTE,
        title = "Favorites",
        selectedIcon = com.kitchenpal.core.designsystem.R.drawable.ic_bookmark,
        unselectedIcon = com.kitchenpal.core.designsystem.R.drawable.ic_bookmark_filled,
    ),
    PROFILE(
        route = PROFILE_ROUTE,
        title = "Profile",
        selectedIcon = com.kitchenpal.core.designsystem.R.drawable.ic_profile,
        unselectedIcon = com.kitchenpal.core.designsystem.R.drawable.ic_profile_filled,
    )
}
