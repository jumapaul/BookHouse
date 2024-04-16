package com.example.bookhouse.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookhouse.R
import com.example.bookhouse.ui.theme.LightBlue

@Composable
fun SignButton(
    onclick: () -> Unit,
    signText: String,
    modifier: Modifier = Modifier
) {

    OutlinedButton(
        onClick = onclick,
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = LightBlue
        ),
        border = BorderStroke(width = 0.dp, color = Color.Transparent)
    ) {
        Text(
            text = signText,
            color = Color.White,
            fontSize = 20.sp,
            modifier = modifier.padding(vertical = 6.dp)
        )
    }
}

@Composable
fun ElevatedButtonComposable(
    painter: Painter,
    text: String,
    onclick: () -> Unit
){
    ElevatedButton(onClick = {
        onclick()
    }) {
        Image(painter = painter, contentDescription = null)
        SmallTexts(text = text, textAlign = TextAlign.Start)
    }
}