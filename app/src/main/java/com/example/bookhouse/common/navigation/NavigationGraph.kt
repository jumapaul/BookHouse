package com.example.bookhouse.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bookhouse.login.presentation.SignUpScreen
import com.example.bookhouse.on_board.OnBoarding

@Composable
fun NavigationGraph(
    navHostController: NavHostController,
) {

    NavHost(
        navController = navHostController, startDestination = NavigationRoutes.OnBoardScreen.route
    ) {
        composable(NavigationRoutes.OnBoardScreen.route) {
            OnBoarding(navHostController)
        }

        composable(NavigationRoutes.SignUpScreen.route){
            SignUpScreen()
        }
    }
}