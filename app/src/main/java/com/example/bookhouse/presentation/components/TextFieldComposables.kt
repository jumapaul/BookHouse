package com.example.bookhouse.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
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
        shape = RoundedCornerShape(15.dp),
        placeholder = {
            Text(text = stringResource(id = R.string.email))
        },
        modifier = modifier.fillMaxWidth(),
        supportingText = {
            Text(
                text = errorText,
                color = Color.Red
            )
        }
    )
}

@Composable
fun PasswordTextField(
    password: String,
    holderText: String,
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
        shape = RoundedCornerShape(15.dp),
        placeholder = {
            Text(text = holderText)
        },
        visualTransformation = if (isPasswordVisible) VisualTransformation.None
        else PasswordVisualTransformation(),

        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        modifier = modifier.fillMaxWidth(),
        trailingIcon = {
            val icon =
                if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
            val description = if (isPasswordVisible) "Hide Password" else "Show Password"

            IconButton(onClick = {
                isPasswordVisible = !isPasswordVisible
            }) {
                Icon(imageVector = icon, description)
            }
        },
        supportingText = {
            Text(
                text = errorText,
                color = Color.Red
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextFieldComposable(
    text: String,
    onNewValue: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }

    Box(
        modifier = modifier
            .height(50.dp)
            .clip(
                shape = RoundedCornerShape(10.dp)
            )
            .border(
                1.dp, shape = RoundedCornerShape(10.dp), color = Color.Gray
            )
    ) {
        BasicTextField(
            value = text,
            onValueChange = onNewValue,
            modifier = modifier.fillMaxWidth(),
            singleLine = true,
            interactionSource = interactionSource
        ) { innerTextField ->
            OutlinedTextFieldDefaults.DecorationBox(
                value = text,
                innerTextField = innerTextField,
                enabled = true,
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                interactionSource = interactionSource,
                placeholder = {
                    Text(text = "Search")
                },
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Search, "contentDescription",
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(),
                contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
                    top = 0.dp,
                    bottom = 0.dp
                ),
            )
        }
    }
}


