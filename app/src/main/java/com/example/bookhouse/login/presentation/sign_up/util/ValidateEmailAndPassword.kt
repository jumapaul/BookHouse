package com.example.bookhouse.login.presentation.sign_up.util

import android.util.Patterns
import com.example.bookhouse.login.presentation.sign_up.state.RegisterValidation


fun validateEmail(email: String): Any {
    if (email.isEmpty()) return RegisterValidation.Failed("Email cannot be empty")

    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        return RegisterValidation.Failed("Wrong email format!")

    return RegisterValidation.Success
}

fun validatePassword(password: String): Any {
    if (password.isEmpty()) return RegisterValidation.Failed("Password cannot be empty")

    if (password.length < 6) return RegisterValidation.Failed("Password cannot be less than 6 characters")

    return RegisterValidation.Success
}

fun validateConfirmPassword(password: String, confirmPassword: String): Any{
    if (password.isEmpty()) return RegisterValidation.Failed("Password cannot be empty")

    if (password.length < 6) return RegisterValidation.Failed("Password cannot be less than 6 characters")

    if (password!=confirmPassword) return RegisterValidation.Failed("Not the same as password")

    return RegisterValidation.Success
}
