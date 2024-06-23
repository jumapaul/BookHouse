package com.example.bookhouse.presentation.navigation

sealed class Routes(val routes: String) {
    object OnBoardScreenRoute : Routes("on_boards")
    object SignUpScreenRoutes : Routes( "sign_up")
    object SignInScreenRoutes : Routes("sign_in")
}