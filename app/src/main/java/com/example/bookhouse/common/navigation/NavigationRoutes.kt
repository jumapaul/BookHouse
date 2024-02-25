package com.example.bookhouse.common.navigation

sealed class NavigationRoutes(val route: String) {
    object OnBoardScreen : NavigationRoutes("on_board")

    object SignUpScreen : NavigationRoutes("sign_up")
    object SignInScreen : NavigationRoutes("sign_in")
}