package com.example.bookhouse.presentation.sign_in

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.example.bookhouse.R
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.Firebase
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException

class GoogleAuthClient(
    private val context: Context,
    private val oneTapClient: SignInClient
) {
    private val auth = Firebase.auth

    suspend fun signIn(): IntentSender? {
        val result = try {
            oneTapClient.beginSignIn(
                buildSignInRequest()
            ).await()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            null
        }

        //When we take intent sender and send it, our intent sender will get back with info about user sign in
        return result?.pendingIntent?.intentSender
    }

    //Function that deserializes the intents
    suspend fun signInWithIntent(intent: Intent): SignInResults {
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredential = GoogleAuthProvider.getCredential(googleIdToken, null)

        return try {
            val user = auth.signInWithCredential(googleCredential).await().user

            SignInResults(
                data = user?.run {
                    UserData(
                        userId = uid,
                        userName = displayName,
                        profilePictureUrl = photoUrl?.toString()
                    )
                },
                errorMessage = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e

            SignInResults(
                data = null,
                errorMessage = e.message
            )
        }
    }

    private fun buildSignInRequest(): BeginSignInRequest {
        return BeginSignInRequest.Builder().setGoogleIdTokenRequestOptions(
            GoogleIdTokenRequestOptions.builder().setSupported(true)
                //Only show accounts previously used to sign in.
                .setFilterByAuthorizedAccounts(false)
                //Server's client ID
                .setServerClientId(
                    context.getString(
                        R.string.web_client_id
                    )
                ).build()
            //In case its just a single account, sign in to it.
        ).setAutoSelectEnabled(true)
            .build()
    }

//Add a function to sign out an retrieve current user

    suspend fun signOut() {
        try {
            oneTapClient.signOut().await()
            auth.signOut()

        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
        }
    }

    fun getSignedInUser(): UserData? = auth.currentUser?.run {
        UserData(
            userId = uid,
            userName = displayName,
            profilePictureUrl = photoUrl?.toString()
        )
    }
}