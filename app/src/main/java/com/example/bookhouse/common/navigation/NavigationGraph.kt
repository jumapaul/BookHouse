package com.example.bookhouse.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bookhouse.login.presentation.sign_in.SignInScreen
import com.example.bookhouse.login.presentation.sign_up.SignUpScreen
import com.example.bookhouse.on_board.OnBoarding

@Composable
fun NavigationGraph(
    navHostController: NavHostController,
    startDestination: String,
) {

    NavHost(
        navController = navHostController, startDestination = startDestination
    ) {
        composable(NavigationRoutes.OnBoardScreen.route) {
            OnBoarding(navHostController)
        }

        composable(NavigationRoutes.SignUpScreen.route) {
            SignUpScreen(navHostController)
        }

        composable(NavigationRoutes.SignInScreen.route) {
            SignInScreen()
        }
    }
}