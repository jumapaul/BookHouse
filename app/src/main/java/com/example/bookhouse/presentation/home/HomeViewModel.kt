package com.example.bookhouse.presentation.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookhouse.presentation.navigation.NavigationRoutes
import com.example.bookhouse.util.DataStoreUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dataStoreUtils: DataStoreUtils
) : ViewModel(){

    private val _startDestination: MutableState<String> =
        mutableStateOf(NavigationRoutes.OnBoardScreen.route)

    val startDestination: State<String> = _startDestination

//    private fun getData(){
//        val data = dataStoreUtils.readOnBoardingState()
//        _startDestination.value = data
//    }
}