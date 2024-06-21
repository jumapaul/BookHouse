package com.example.bookhouse.presentation.navigation.main_graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bookhouse.presentation.home.HomeScreen
import com.example.bookhouse.presentation.navigation.auth_graph.authNavGraph
import com.example.bookhouse.presentation.navigation.on_board.onBoardNavGraph

@Composable
fun RootNavigationGraph(
    navController: NavHostController,
    startDestination: String
    ){
    NavHost(
        navController = navController,
        route = Graphs.ROOT,
        startDestination = startDestination
        ){

        authNavGraph(navController = navController)

        composable(route = Graphs.HOME_GRAPH){
            HomeScreen()
        }

        onBoardNavGraph(navHostController = navController)
    }

}

object Graphs{
    const val ROOT = "root_graph"
    const val ON_BOARD_SCREEN = "on_board"
    const val AUTHENTICATION_GRAPH = "auth_graph"
    const val HOME_GRAPH = "home_graph"
}