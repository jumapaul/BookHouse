package com.example.bookhouse.presentation.navigation.main_graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bookhouse.presentation.bookings.BookingsScreen
import com.example.bookhouse.presentation.favorite.FavoriteScreen
import com.example.bookhouse.presentation.home.HomeScreen
import com.example.bookhouse.presentation.messages.MessagesScreen
import com.example.bookhouse.presentation.navigation.Routes
import com.example.bookhouse.presentation.navigation.bottom_bar_screen.BottomBarScreen
import com.example.bookhouse.presentation.on_board.OnBoardingScreen
import com.example.bookhouse.presentation.sign_in.SignInScreen
import com.example.bookhouse.presentation.sign_up.SignUpScreen

@Composable
fun RootNavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String
    ){
    NavHost(
        navController = navController,
        startDestination = startDestination
        ){

        composable(BottomBarScreen.HomeScreen.route) {
            HomeScreen(navController)
        }

        composable(BottomBarScreen.FavoriteScreen.route) {
            FavoriteScreen()
        }

        composable(BottomBarScreen.BookingScreen.route) {
            BookingsScreen()
        }

        composable(BottomBarScreen.MessagesScreen.route) {
            MessagesScreen()
        }

        composable(Routes.SignUpScreenRoutes.routes) {
            SignUpScreen(navController)
        }

        composable(Routes.SignInScreenRoutes.routes) {
            SignInScreen(navController)
        }

        composable(Routes.OnBoardScreenRoute.routes){
            OnBoardingScreen(navController = navController)
        }

    }

}