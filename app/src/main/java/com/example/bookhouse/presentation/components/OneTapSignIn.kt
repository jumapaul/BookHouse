package com.example.bookhouse.presentation.components

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.example.bookhouse.domain.model.sign_in.DataProvider
import com.example.bookhouse.util.Resource
import com.google.android.gms.auth.api.identity.BeginSignInResult

@Composable
fun OneTapSignIn(
    launch: (result: BeginSignInResult) -> Unit,
    context: Context,
    navController: NavController
) {
    when (
        val oneTapSignInResponse = DataProvider.oneTapSignInResponse
    ) {
        is Resource.Error -> {
            LaunchedEffect(key1 = Unit) {
                Log.d("error--------->", "OneTapSignIn: ${oneTapSignInResponse.message}")
                Toast.makeText(context, "${oneTapSignInResponse.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        is Resource.Loading -> {
            ProgressIndicatorComposable()
        }

        is Resource.Success -> {
            oneTapSignInResponse.data?.let { signInResult ->
                LaunchedEffect(key1 = signInResult) {
                    launch(signInResult)
                }
            }
        }
    }
}