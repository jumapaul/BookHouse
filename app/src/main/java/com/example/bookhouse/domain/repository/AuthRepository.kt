package com.example.bookhouse.domain.repository

import com.example.bookhouse.domain.model.sign_in.FirebaseSignInResponse
import com.example.bookhouse.domain.model.sign_in.OneTapSignInResponse
import com.google.android.gms.auth.api.identity.SignInCredential

interface AuthRepository {

    suspend fun oneTapSignIn(): OneTapSignInResponse

    suspend fun signInWithGoogle(credential: SignInCredential): FirebaseSignInResponse


}