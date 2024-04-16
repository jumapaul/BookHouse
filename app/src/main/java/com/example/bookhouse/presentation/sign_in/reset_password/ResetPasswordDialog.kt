package com.example.bookhouse.presentation.sign_in.reset_password

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.bookhouse.presentation.components.EmailTextField
import com.example.bookhouse.presentation.components.SmallTexts
import com.example.bookhouse.presentation.components.VerticalSpacer

@Composable
fun ResetPasswordDialog(
    showDialog: Boolean,
    emailTextError: String,
    onDismissRequest: () -> Unit,
    onConfirmationRequest: () -> Unit,
    onNewValue: (String) -> Unit,
    email: String
) {
    if (showDialog) {
        Dialog(onDismissRequest = { onDismissRequest() }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(375.dp),
                shape = RoundedCornerShape(16.dp)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SmallTexts(
                        text = "Reset password",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    VerticalSpacer(10.dp)
                    EmailTextField(
                        email = email,
                        onNewValue = onNewValue,
                        errorText = emailTextError
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextButton(
                            onClick = { onDismissRequest() },
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text(text = "Cancel")
                        }

                        TextButton(
                            onClick = { onConfirmationRequest() },
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text(text = "Send")
                        }
                    }
                }
            }
        }

    }

}