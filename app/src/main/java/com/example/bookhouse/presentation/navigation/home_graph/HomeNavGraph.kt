package com.example.bookhouse.presentation.navigation.home_graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bookhouse.presentation.bookings.BookingsScreen
import com.example.bookhouse.presentation.favorite.FavoriteScreen
import com.example.bookhouse.presentation.home.HomeScreen
import com.example.bookhouse.presentation.messages.MessagesScreen
import com.example.bookhouse.presentation.navigation.bottom_bar_screen.BottomBarScreen
import com.example.bookhouse.presentation.navigation.main_graph.Graphs

@Composable
fun HomeNavGraph(navHostController: NavHostController){
    NavHost(
        navController =navHostController,
        route = Graphs.HOME_GRAPH,
        startDestination = BottomBarScreen.HomeScreen.route
        ){

        composable(BottomBarScreen.HomeScreen.route) {
            HomeScreen()
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
    }
}