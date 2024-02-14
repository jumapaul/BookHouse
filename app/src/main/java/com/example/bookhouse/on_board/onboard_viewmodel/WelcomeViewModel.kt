package com.example.bookhouse.on_board.onboard_viewmodel

import android.view.View
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookhouse.common.navigation.NavigationRoutes
import com.example.bookhouse.on_board.utils.DataStoreUtils
import kotlinx.coroutines.launch
import javax.inject.Inject

class WelcomeViewModel @Inject constructor(
    dataStoreUtils: DataStoreUtils
) : ViewModel() {
    private val _startDestination: MutableState<String> =
        mutableStateOf(NavigationRoutes.OnBoardScreen.route)

    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            dataStoreUtils.readOnBoardingState().collect { completed ->
                if (completed) {
                    _startDestination.value = NavigationRoutes.SignUpScreen.route
                } else {
                    _startDestination.value = NavigationRoutes.OnBoardScreen.route
                }
            }
        }
    }
}