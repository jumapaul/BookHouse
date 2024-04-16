package com.example.bookhouse.presentation.sign_up

import android.util.Log
import android.widget.Toast
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
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bookhouse.R
import com.example.bookhouse.presentation.navigation.NavigationRoutes
import com.example.bookhouse.domain.model.User
import com.example.bookhouse.presentation.components.BlueColoredTexts
import com.example.bookhouse.presentation.components.ElevatedButtonComposable
import com.example.bookhouse.presentation.components.EmailTextField
import com.example.bookhouse.presentation.components.LargeTexts
import com.example.bookhouse.presentation.components.PasswordTextField
import com.example.bookhouse.presentation.components.SignButton
import com.example.bookhouse.presentation.components.SmallTexts
import com.example.bookhouse.presentation.sign_up.state.RegisterValidation
import com.example.bookhouse.presentation.sign_up.state.SignInState
import com.example.bookhouse.presentation.sign_up.util.validateConfirmPassword
import com.example.bookhouse.presentation.sign_up.util.validateEmail
import com.example.bookhouse.presentation.sign_up.util.validatePassword
import com.example.bookhouse.ui.theme.LightBlue
import com.example.bookhouse.util.Resource

@Composable
fun SignUpScreen(
    navController: NavController,
    state: SignInState,
    onSignUpWithGoogleClick: () -> Unit,
    signUpViewModel: SignUpViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,

    ) {

    val destinationState = signUpViewModel.startDestinationState.collectAsState().value

    Log.d("----------->", "SignUpScreen: $destinationState")
    var emailText by remember {
        mutableStateOf("")
    }

    var emailTextError by remember {
        mutableStateOf("")
    }

    var passwordText by remember {
        mutableStateOf("")
    }

    var passwordTextError by remember {
        mutableStateOf("")
    }

    var confirmPasswordText by remember {
        mutableStateOf("")
    }

    var confirmPasswordTextError by remember {
        mutableStateOf("")
    }

    val user = User(emailText, confirmPasswordText)

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(25.dp)
    ) {


        Spacer(modifier = modifier.height(60.dp))
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

        LargeTexts(color = Color.Black, text = "Sign Up for free")

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

        Spacer(modifier = modifier.height(5.dp))

        SmallTexts(
            text = "Confirm Password",
            textAlign = TextAlign.Start,
            modifier = modifier
                .fillMaxWidth()
                .padding(3.dp),
        )

        PasswordTextField(
            password = confirmPasswordText,
            holderText = "Confirm password",
            onNewValue = { confirmPassword ->
                val results = validateConfirmPassword(passwordText, confirmPassword)
                confirmPasswordTextError =
                    if (results is RegisterValidation.Failed) results.message else ""
                confirmPasswordText = confirmPassword
            },
            errorText = confirmPasswordTextError
        )

        Spacer(modifier = modifier.height(10.dp))

        SignButton(
            onclick = {
                signUpViewModel.createAccountWithEmailAndPassword(
                    user, confirmPasswordText
                )
            },
            signText = "Sign Up"
        )
        Spacer(modifier = modifier.height(20.dp))
        SmallTexts(text = "Or continue with", textAlign = TextAlign.Center)

        Spacer(modifier = modifier.height(5.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            ElevatedButtonComposable(
                painter = painterResource(id = R.drawable.google),
                text = "Google",
                onclick = {
                    onSignUpWithGoogleClick()
                })

            Spacer(modifier = modifier.width(10.dp))

            ElevatedButtonComposable(
                painter = painterResource(id = R.drawable.facebook),
                text = "Facebook",
                onclick = {
                    TODO()
                })
        }

        Spacer(modifier = modifier.height(15.dp))

        Row {
            SmallTexts(text = "Already have an account?", textAlign = TextAlign.Center)
            Spacer(modifier = modifier.width(5.dp))
            BlueColoredTexts(
                text = "Sign In",
                color = LightBlue,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Start,
                modifier = Modifier.clickable {
                    Log.d("----->", "SignUpScreen: ")
                    navController.navigate(NavigationRoutes.SignInScreen.route)
                }
            )
        }

    }
    val registerState by signUpViewModel.register.collectAsState()

    when (registerState) {
        is Resource.Loading -> {

        }

        is Resource.Success -> {
            LaunchedEffect(key1 = Unit) {
                navController.navigate(NavigationRoutes.SignInScreen.route)
            }
        }

        is Resource.Error -> {
            val context = LocalContext.current
            val errorMessage = registerState.message
            LaunchedEffect(key1 = Unit) {
                Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
            }
        }
    }

    if (!destinationState) {
        navController.navigate(NavigationRoutes.OnBoardScreen.route)
    }
}
