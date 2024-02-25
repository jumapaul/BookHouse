package com.example.bookhouse.login.presentation.sign_up.state

sealed class AuthState {
    object Initial: AuthState()
    object Loading: AuthState()
    object Authenticated: AuthState()
    data class Error(val message: String): AuthState()
}

// Validation state
sealed class RegisterValidation(){
    object Success: RegisterValidation()
    data class Failed(val message: String): RegisterValidation()
}

data class RegisterFieldState(
    val email: RegisterValidation,
    val password: RegisterValidation
)