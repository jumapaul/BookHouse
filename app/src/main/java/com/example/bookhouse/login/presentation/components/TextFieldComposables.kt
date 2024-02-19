package com.example.bookhouse.login.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.bookhouse.R

@Composable
fun EmailTextField(
    email: String,
    onNewValue: (String) -> Unit,
    errorText: String,
    modifier: Modifier = Modifier,
    ) {
    OutlinedTextField(
        value = email,
        onValueChange = onNewValue,
        singleLine = true,
        placeholder = {
            Text(text = stringResource(id = R.string.email))
        },
        modifier = modifier.fillMaxWidth(),
        supportingText = {
            Text(text = errorText)
        }
    )
}

@Composable
fun PasswordTextField(
    password: String,
    onNewValue: (String) -> Unit,
    errorText: String,
    modifier: Modifier = Modifier,
    ) {

    var isPasswordVisible by remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        value = password,
        onValueChange = onNewValue,
        singleLine = true,
        placeholder = {
            Text(text = stringResource(id = R.string.password))
        },
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        modifier = modifier.fillMaxWidth(),
        trailingIcon = {
            var icon =
                if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
            val description = if (isPasswordVisible) "Hide Password" else "Show Password"

            IconButton(onClick = {
                isPasswordVisible = !isPasswordVisible
            }) {
                Icon(imageVector = icon, description)
            }
        },
        supportingText = {
            Text(text = errorText)
        }
    )
}