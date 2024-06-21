package com.example.bookhouse.presentation.navigation.on_board

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.bookhouse.presentation.navigation.main_graph.Graphs
import com.example.bookhouse.presentation.on_board.OnBoarding

fun NavGraphBuilder.onBoardNavGraph(navHostController: NavHostController){
    navigation(
        route = Graphs.ON_BOARD_SCREEN,
        startDestination = OnBoardScreens.OnBoardScreen.route
    ){
        composable(OnBoardScreens.OnBoardScreen.route) {
            OnBoarding(navHostController)
        }
    }
}

sealed class OnBoardScreens(val route: String){
    object OnBoardScreen : OnBoardScreens("on_boards")
}