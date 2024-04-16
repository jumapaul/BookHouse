package com.example.bookhouse.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.bookhouse.presentation.navigation.NavigationGraph
import com.example.bookhouse.presentation.navigation.NavigationRoutes
import com.example.bookhouse.presentation.sign_in.GoogleAuthClient
import com.example.bookhouse.presentation.sign_in.GoogleSignInViewModel
import com.example.bookhouse.ui.theme.BookHouseTheme
import com.google.android.gms.auth.api.identity.Identity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val googleSignInViewModel: GoogleSignInViewModel by viewModels()

    private val googleAuthUiClient by lazy {
        GoogleAuthClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            BookHouseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    val googleSignInViewModel: GoogleSignInViewModel = viewModel()

                    val navController = rememberNavController()

                    val state by googleSignInViewModel.state.collectAsStateWithLifecycle()

                    val launcher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.StartIntentSenderForResult(),
                        onResult = { result ->
                            if (result.resultCode == RESULT_OK) {
                                lifecycleScope.launch {
                                    val signInResults = googleAuthUiClient.signInWithIntent(
                                        intent = result.data ?: return@launch
                                    )
                                    googleSignInViewModel.onSignInResults(signInResults)
                                }
                            }
                        }
                    )

                    LaunchedEffect(key1 = state.isSignInSuccessful) {
                        if (state.isSignInSuccessful) {
                            Toast.makeText(
                                applicationContext,
                                "Sign In Successful",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                    NavigationGraph(
                        navHostController = navController,
                        startDestination = NavigationRoutes.SignUpScreen.route,
                        signInState = state,
                        onSignInClick = {
                            lifecycleScope.launch {
                                val signInWithIntent = googleAuthUiClient.signIn()
                                launcher.launch(
                                    IntentSenderRequest.Builder(
                                        signInWithIntent ?: return@launch
                                    ).build()
                                )
                            }
                        },
                    )
                }
            }
        }
    }
}

