package com.example.bookhouse.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bookhouse.constants.Constants.HOME_ARGS
import com.example.bookhouse.presentation.home.HomeScreen
import com.example.bookhouse.presentation.on_board.OnBoarding
import com.example.bookhouse.presentation.sign_in.SignInScreen
import com.example.bookhouse.presentation.sign_in.UserData
import com.example.bookhouse.presentation.sign_up.SignUpScreen
import com.example.bookhouse.util.converter.fromJson

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

            SignUpScreen(
                navController = navHostController,
            )
        }

        composable(NavigationRoutes.SignInScreen.route) {
            SignInScreen(
                navController = navHostController,
            )
        }

        composable(NavigationRoutes.HomeScreen.route) {
            HomeScreen()

        }
    }
}