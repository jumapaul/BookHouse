package com.example.bookhouse.presentation.navigation.auth_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.bookhouse.presentation.navigation.main_graph.Graphs
import com.example.bookhouse.presentation.sign_in.SignInScreen
import com.example.bookhouse.presentation.sign_up.SignUpScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController){
    navigation(
        route = Graphs.AUTHENTICATION_GRAPH,
        startDestination = AuthScreenRoutes.SignUpScreenRoutes.route
    ){
        composable(AuthScreenRoutes.SignUpScreenRoutes.route) {
            SignUpScreen(
                navController = navController,
            )
        }

        composable(AuthScreenRoutes.SignInScreenRoutes.route) {
            SignInScreen(
                navController = navController,
            )
        }
    }
}

sealed class AuthScreenRoutes(val route: String){
    object SignUpScreenRoutes : AuthScreenRoutes( "sign_up")
    object SignInScreenRoutes : AuthScreenRoutes("sign_in")
}