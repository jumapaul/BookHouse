package com.example.bookhouse.login.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bookhouse.login.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpSignInViewModel @Inject constructor(

) : ViewModel() {

    var uiState = mutableStateOf(User())
        private set
}