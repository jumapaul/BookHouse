package com.example.bookhouse.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bookhouse.presentation.navigation.Routes
import com.example.bookhouse.presentation.navigation.bottom_bar_screen.BottomBarScreen
import com.example.bookhouse.presentation.navigation.main_graph.BottomNavigator
import com.example.bookhouse.presentation.navigation.main_graph.RootNavigationGraph
import com.example.bookhouse.ui.theme.BookHouseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BookHouseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                   MainView()
                }
            }
        }
    }

    @Composable
    fun MainView() {
        val navController = rememberNavController()
        val bottomState = rememberSaveable {
            mutableStateOf(true)
        }

        val navBackStackEntry by navController.currentBackStackEntryAsState()

        when (navBackStackEntry?.destination?.route) {
            Routes.OnBoardScreenRoute.routes -> {
                bottomState.value = false
            }

            Routes.SignUpScreenRoutes.routes -> {
                bottomState.value = false
            }

            Routes.SignInScreenRoutes.routes -> {
                bottomState.value = false
            }

            else -> {
                bottomState.value = true
            }
        }

        Scaffold(
            bottomBar = {
                if (bottomState.value) {
                    BottomNavigator(
                        navController = navController,
                        bottomState = bottomState
                        )
                }
            }
        ) {
            RootNavigationGraph(
                navController = navController,
                startDestination = BottomBarScreen.HomeScreen.route,
                modifier = Modifier
                    .padding(it)
                    .fillMaxWidth()
            )
        }
    }
}


