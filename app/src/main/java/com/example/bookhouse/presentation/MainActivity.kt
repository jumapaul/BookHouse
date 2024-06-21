package com.example.bookhouse.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.bookhouse.presentation.navigation.main_graph.Graphs
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
                    val navController = rememberNavController()
                    RootNavigationGraph(
                        navController = navController,
                        startDestination = Graphs.HOME_GRAPH
                        )
                }
            }
        }
    }
}


