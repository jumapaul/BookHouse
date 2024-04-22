package com.example.bookhouse.data.repositoryImpl

import android.util.Log
import com.example.bookhouse.constants.Constants.SIGN_IN_REQUEST
import com.example.bookhouse.constants.Constants.SIGN_UP_REQUEST
import com.example.bookhouse.domain.model.FirebaseSignInResponse
import com.example.bookhouse.domain.model.OneTapSignInResponse
import com.example.bookhouse.domain.repository.AuthRepository
import com.example.bookhouse.util.Resource
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.identity.SignInCredential
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named
import kotlin.math.log

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private var oneTapClient: SignInClient,
    @Named(SIGN_IN_REQUEST) private var signInRequest: BeginSignInRequest,
    @Named(SIGN_UP_REQUEST) private var signUpRequest: BeginSignInRequest
) : AuthRepository {


    override suspend fun oneTapSignIn(): OneTapSignInResponse {
        return try {
            val signInResult = oneTapClient.beginSignIn(signInRequest).await()
            Resource.Success(data = signInResult)
        } catch (e: Exception) {
            try {
                val signUpRequest = oneTapClient.beginSignIn(signUpRequest).await()
                Resource.Success(data = signUpRequest)
            } catch (e: Exception) {
                Resource.Error(message = e.message ?: "An error occurred")
            }
        }
    }

    override suspend fun signInWithGoogle(credential: SignInCredential): FirebaseSignInResponse {
        val googleCredential = GoogleAuthProvider.getCredential(credential.googleIdToken, null)
        return authenticateUser(googleCredential)
    }

    private suspend fun authenticateUser(credential: AuthCredential): FirebaseSignInResponse {
        return if (auth.currentUser != null) {
            authLink(credential)
        } else {
            authSignIn(credential)
        }
    }

    private suspend fun authLink(credential: AuthCredential): FirebaseSignInResponse {
        return try {
            val authResult = auth.signInWithCredential(credential).await()
//            val authResult = auth.currentUser?.linkWithCredential(credential)?.await()
            Resource.Success(data = authResult)
        } catch (e: FirebaseAuthException) {
            Resource.Error(message = e.message ?: "An error occurred")
        }
    }

    private suspend fun authSignIn(credential: AuthCredential): FirebaseSignInResponse {
        return try {
            val authResult = auth.signInWithCredential(credential).await()

            Resource.Success(data = authResult)
        } catch (e: Exception) {
            Resource.Error(message = e.message ?: "An error occurred")
        }
    }
}