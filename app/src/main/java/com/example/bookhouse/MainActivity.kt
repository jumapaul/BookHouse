package com.example.bookhouse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.bookhouse.common.navigation.NavigationGraph
import com.example.bookhouse.on_board.onboard_viewmodel.OnBoardViewModel
import com.example.bookhouse.on_board.onboard_viewmodel.WelcomeViewModel
import com.example.bookhouse.ui.theme.BookHouseTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var welcomeViewModel: WelcomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            BookHouseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val screen by welcomeViewModel.startDestination
                    NavigationGraph(navHostController = navController, startDestination = screen)
                }
            }
        }
    }
}

