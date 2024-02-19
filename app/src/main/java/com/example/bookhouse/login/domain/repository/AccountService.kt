package com.example.bookhouse.login.domain.repository

import com.example.bookhouse.login.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AccountService {
    suspend fun authenticateUser(email: String, password: String)

    suspend fun sendRecoveryEmail(email: String)

    suspend fun deleteAccount()

    suspend fun signOut()
}