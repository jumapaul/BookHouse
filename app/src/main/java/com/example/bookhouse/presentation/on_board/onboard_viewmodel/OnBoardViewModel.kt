package com.example.bookhouse.presentation.on_board.onboard_viewmodel

import androidx.lifecycle.ViewModel
import com.example.bookhouse.util.DataStoreUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnBoardViewModel @Inject constructor(
    private val dataStoreUtils: DataStoreUtils
) : ViewModel() {

    fun saveState(state: String, value: Boolean) {
        dataStoreUtils.saveDate(state, value)
    }
}