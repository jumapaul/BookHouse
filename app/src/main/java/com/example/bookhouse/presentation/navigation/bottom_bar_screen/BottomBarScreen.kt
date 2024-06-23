package com.example.bookhouse.presentation.navigation.bottom_bar_screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Message
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {

    object HomeScreen: BottomBarScreen(
        route = "HOME",
        title = "Home",
        icon = Icons.Filled.Home
    )

    object FavoriteScreen : BottomBarScreen(
        route = "FAVORITE",
        title = "Favorite",
        icon = Icons.Filled.Favorite)

    object BookingScreen : BottomBarScreen(
        route = "BOOKINGS",
        title = "Bookings",
        icon = Icons.Filled.CalendarMonth)

    object MessagesScreen : BottomBarScreen(
        route = "MESSAGES",
        title = "Messages",
        icon = Icons.Filled.Message)
}

