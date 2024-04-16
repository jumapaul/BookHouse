package com.example.bookhouse.presentation.sign_in

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookhouse.presentation.sign_up.state.SignInState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoogleSignInViewModel @Inject constructor(): ViewModel() {


    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun onSignInResults(results: SignInResults) = viewModelScope.launch {
            Log.d("--------------->", "onSignInResults")
            _state.update {
                it.copy(
                    isSignInSuccessful = results.data != null,
                    signInError = results.errorMessage
                )
            }

    }

    //Once the user navigates back to the login screen, should be signed out.
    fun resetState() {
        _state.update { SignInState() }
    }

}