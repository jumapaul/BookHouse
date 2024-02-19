package com.example.bookhouse.login.presentation

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookhouse.R
import com.example.bookhouse.login.presentation.components.ClickableTexts
import com.example.bookhouse.login.presentation.components.EmailTextField
import com.example.bookhouse.login.presentation.components.LargeTexts
import com.example.bookhouse.login.presentation.components.PasswordTextField
import com.example.bookhouse.login.presentation.components.SignButton
import com.example.bookhouse.login.presentation.components.SmallTexts
import com.example.bookhouse.ui.theme.LightBlue

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier
) {

    var emailText by remember {
        mutableStateOf("")
    }

    var passwordText by remember {
        mutableStateOf("")
    }
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(25.dp)
    ) {

        Spacer(modifier = modifier.height(130.dp))
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

        Spacer(modifier = modifier.height(60.dp))

        LargeTexts(color = Color.Black, text = "Sign In for free")

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
                emailText = newValue
            }, errorText = ""
        )

        Spacer(modifier = modifier.height(20.dp))

        SmallTexts(
            text = "Password",
            textAlign = TextAlign.Start,
            modifier = modifier
                .fillMaxWidth()
                .padding(3.dp),
        )

        PasswordTextField(
            password = passwordText,
            onNewValue = { password ->
                passwordText = password
            },
            errorText = ""
        )
        Text(text = "Forgot password?", textAlign = TextAlign.Start,
            modifier = modifier
                .fillMaxWidth()
                .clickable {
                    TODO()
                },
            color = LightBlue
        )
        Spacer(modifier = modifier.height(20.dp))

        SignButton(
            onclick = { /*TODO*/ },
            signText = "Sign In"
        )
        Spacer(modifier = modifier.height(20.dp))
        SmallTexts(text = "Or continue with", textAlign = TextAlign.Center)

        Spacer(modifier = modifier.height(20.dp))

        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ClickableTexts(
                painter = painterResource(id = R.drawable.google), text = "Google",
                onClick = {
                    TODO()
                },
                modifier = modifier.weight(1f)
            )
            Spacer(modifier = modifier.width(10.dp))
            ClickableTexts(
                painter = painterResource(id = R.drawable.facebook), text = "Facebook",
                onClick = {
                    TODO()
                },
                modifier = modifier.weight(1f)
            )
        }

        Spacer(modifier = modifier.height(20.dp))

        Row {
            SmallTexts(text = "Do not have an account?", textAlign = TextAlign.Center)
            Spacer(modifier = modifier.width(5.dp))
            Text(
                text = "Create one",
                color = LightBlue,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 20.sp,
                modifier = modifier.clickable {
                    TODO()
                }
            )
        }

    }

}

@Preview(
    showBackground = true
)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen()

}