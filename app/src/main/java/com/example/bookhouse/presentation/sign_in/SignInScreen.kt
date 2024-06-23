package com.example.bookhouse.presentation.sign_in

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bookhouse.R
import com.example.bookhouse.domain.model.sign_in.User
import com.example.bookhouse.presentation.components.BlueColoredTexts
import com.example.bookhouse.presentation.components.ElevatedButtonComposable
import com.example.bookhouse.presentation.components.EmailTextField
import com.example.bookhouse.presentation.components.LargeTexts
import com.example.bookhouse.presentation.components.OneTapSignIn
import com.example.bookhouse.presentation.components.PasswordTextField
import com.example.bookhouse.presentation.components.SignButton
import com.example.bookhouse.presentation.components.SmallTexts
import com.example.bookhouse.presentation.navigation.Routes
import com.example.bookhouse.presentation.navigation.bottom_bar_screen.BottomBarScreen
import com.example.bookhouse.presentation.sign_in.reset_password.ResetPasswordDialog
import com.example.bookhouse.presentation.sign_up.state.RegisterValidation
import com.example.bookhouse.presentation.sign_up.util.validateEmail
import com.example.bookhouse.presentation.sign_up.util.validatePassword
import com.example.bookhouse.ui.theme.LightBlue
import com.example.bookhouse.util.Resource
import com.google.android.gms.auth.api.identity.BeginSignInResult

@Composable
fun SignInScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    signInViewModel: SignInViewModel = hiltViewModel(),
) {
    val resetEmailState = signInViewModel.resetPassword.collectAsState().value

    var emailText by remember {
        mutableStateOf("")
    }

    var emailTextError by remember {
        mutableStateOf("")
    }

    var dialogEmailTextError by remember {
        mutableStateOf("")
    }

    var passwordText by remember {
        mutableStateOf("")
    }

    var passwordTextError by remember {
        mutableStateOf("")
    }

    val user = User(emailText, passwordText)

    var showDialog by remember {
        mutableStateOf(false)
    }

    var resetEmailText by remember {
        mutableStateOf("")

    }

    val signInState = signInViewModel.signIn.collectAsState().value


    val context = LocalContext.current

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartIntentSenderForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                try {
                    val credential =
                        signInViewModel.oneTapClient.getSignInCredentialFromIntent(result.data)
                    signInViewModel.signInWithGoogle(credential)

                    val userName = credential.displayName
                    val profilePicUri = credential.profilePictureUri
                    val userData = UserData(userName, profilePicUri.toString())
                    signInViewModel.saveUserData(userData)

                    navController.navigate(BottomBarScreen.HomeScreen.route)

                } catch (e: Exception) {
                    Log.e("launcher------>", "Login oneTap ${e.message}")
                }
            } else if (result.resultCode == Activity.RESULT_CANCELED) {
                Log.e("Launcher----->", "OneTapClient canceled")
            }
        }

    fun launch(signInResults: BeginSignInResult) {
        val intent = IntentSenderRequest.Builder(signInResults.pendingIntent.intentSender).build()
        launcher.launch(intent)
    }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(25.dp)
    ) {

        Box(
            modifier = modifier
                .wrapContentSize()
                .clip(CircleShape)
                .background(LightBlue),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.booking_icon),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = modifier.size(100.dp)
            )
        }
        Spacer(modifier = modifier.height(10.dp))
        LargeTexts(color = LightBlue, text = "BookHouse")

        Spacer(modifier = modifier.height(20.dp))

        SmallTexts(
            text = "Email",
            textAlign = TextAlign.Start,
            modifier = modifier
                .fillMaxWidth()
                .padding(3.dp),
        )

        EmailTextField(
            email = emailText,
            onNewValue = { newValue ->
                val results = validateEmail(newValue)
                emailTextError = if (results is RegisterValidation.Failed) {
                    results.message
                } else ""
                emailText = newValue
            },
            errorText = emailTextError
        )

        Spacer(modifier = modifier.height(5.dp))

        SmallTexts(
            text = "Password",
            textAlign = TextAlign.Start,
            modifier = modifier
                .fillMaxWidth()
                .padding(3.dp),
        )

        PasswordTextField(
            password = passwordText,
            holderText = "Password",
            onNewValue = { password ->
                val results = validatePassword(password)
                passwordTextError =
                    if (results is RegisterValidation.Failed) results.message else ""
                passwordText = password
            },
            errorText = passwordTextError
        )

        BlueColoredTexts(
            text = "Forgot Password?",
            color = LightBlue,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Start,
            modifier = Modifier.clickable {
                showDialog = true
            }
        )

        if (showDialog) {
            ResetPasswordDialog(
                showDialog = showDialog,
                onDismissRequest = { showDialog = false },
                onConfirmationRequest = {
                    signInViewModel.resetPassword(resetEmailText)

                },
                onNewValue = { resetEmail ->
                    val result = validateEmail(resetEmail)
                    dialogEmailTextError = if (result is RegisterValidation.Failed) {
                        result.message
                    } else ""
                    resetEmailText = resetEmail
                },
                emailTextError = dialogEmailTextError,
                email = resetEmailText

            )
        }

        Spacer(modifier = modifier.height(10.dp))

        SignButton(
            onclick = {
                signInViewModel.signInWithEmailAndPassword(user)
            },
            signText = "Sign In"
        )
        Spacer(modifier = modifier.height(20.dp))
        SmallTexts(text = "Or continue with", textAlign = TextAlign.Center)

        Spacer(modifier = modifier.height(5.dp))

        Row {

            ElevatedButtonComposable(
                painter = painterResource(id = R.drawable.google),
                text = "Google",
                onclick = {
                    Log.d("----------->", "SignInScreen: clicked")
                    signInViewModel.oneTapSignIn()
                }
            )
            Spacer(modifier = modifier.width(10.dp))

            ElevatedButtonComposable(
                painter = painterResource(id = R.drawable.facebook),
                text = "Facebook"
            ) {
                TODO()
            }
        }

        Spacer(modifier = modifier.height(15.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            SmallTexts(text = "Don't have an account?", textAlign = TextAlign.Center)
            Spacer(modifier = modifier.width(5.dp))
            BlueColoredTexts(
                text = "Create one",
                color = LightBlue,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Start,
                modifier = modifier.clickable {
                    navController.navigate(Routes.SignUpScreenRoutes.routes)
                }
            )
        }

    }


    when (signInState) {
        is Resource.Success -> {

            LaunchedEffect(key1 = Unit) {
                navController.navigate(BottomBarScreen.HomeScreen.route)

            }
        }

        is Resource.Error -> {
            val errorMessage = signInState.message
            LaunchedEffect(key1 = Unit) {
                Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
            }
        }

        is Resource.Loading -> {

        }
    }

    when (resetEmailState) {
        is Resource.Error -> {
            LaunchedEffect(key1 = Unit) {
                Toast.makeText(context, "${resetEmailState.message}", Toast.LENGTH_SHORT).show()
            }
        }

        is Resource.Loading -> {

        }

        is Resource.Success -> {
            LaunchedEffect(key1 = Unit) {
                Toast.makeText(context, "Reset link sent to email", Toast.LENGTH_SHORT).show()
                showDialog = false
            }
        }
    }

    OneTapSignIn(
        context = context,
        launch = {
            launch(it)
        },
        navController = navController
    )

}
