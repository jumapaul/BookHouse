package com.example.bookhouse.presentation.navigation

sealed class NavigationRoutes(val route: String) {
    object OnBoardScreen : NavigationRoutes("on_board")

    object SignUpScreen : NavigationRoutes("sign_up")
    object SignInScreen : NavigationRoutes("sign_in")

    object HomeScreen : NavigationRoutes("home")
}