package com.example.bookhouse.presentation.sign_up.state

// Validation state
sealed class RegisterValidation() {
    object Success : RegisterValidation()
    data class Failed(val message: String) : RegisterValidation()
}

data class RegisterFieldState(
    val email: RegisterValidation,
    val password: RegisterValidation
)

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)