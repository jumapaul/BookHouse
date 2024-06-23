package com.example.bookhouse.presentation.navigation.main_graph

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.bookhouse.presentation.navigation.bottom_bar_screen.BottomBarScreen
import com.example.bookhouse.ui.theme.Orange

@Composable
fun BottomNavigator(
    navController: NavController,
    bottomState: MutableState<Boolean>
) {

    val screens = listOf(
        BottomBarScreen.HomeScreen,
        BottomBarScreen.FavoriteScreen,
        BottomBarScreen.BookingScreen,
        BottomBarScreen.MessagesScreen
    )



    AnimatedVisibility(
        visible = bottomState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
            BottomNavigation(
                backgroundColor = Color.White
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                screens.forEach { screen ->
                    BottomNavigationItem(
                        selected = currentRoute == screen.route,
                        selectedContentColor = Orange,
                        unselectedContentColor = Color.Black,
                        onClick = {
                            navController.navigate(screen.route) {
                                navController.graph.startDestinationRoute?.let { route ->
                                    popUpTo(route) {
                                        saveState = true
                                    }
                                }
                            }
                        },
                        icon = {
                            screen.icon.let { imageVector ->
                                (imageVector)
                            }.let {
                                Icon(imageVector = it, contentDescription = null)
                            }
                        },
                        label = {
                            Text(text = screen.title)
                        },
                        alwaysShowLabel = false
                    )
                }
            }
        }
    )
}
