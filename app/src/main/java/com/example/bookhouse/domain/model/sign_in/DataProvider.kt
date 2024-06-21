package com.example.bookhouse.domain.model.sign_in

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.bookhouse.util.Resource
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.firebase.auth.AuthResult

enum class AuthState {
    AUTHENTICATED, SIGNED_IN, SIGNED_OUT;
}

typealias OneTapSignInResponse = Resource<BeginSignInResult>
typealias FirebaseSignInResponse = Resource<AuthResult>

object DataProvider {
    var oneTapSignInResponse by mutableStateOf<OneTapSignInResponse>(Resource.Success(data = null))

    var googleSignInResponse by mutableStateOf<FirebaseSignInResponse>(Resource.Success(data = null))
}