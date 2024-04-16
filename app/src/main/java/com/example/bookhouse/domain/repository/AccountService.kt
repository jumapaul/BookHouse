package com.example.bookhouse.domain.repository

interface AccountService {
    suspend fun authenticateUser(email: String, password: String)

    suspend fun sendRecoveryEmail(email: String)

    suspend fun deleteAccount()

    suspend fun signOut()
}