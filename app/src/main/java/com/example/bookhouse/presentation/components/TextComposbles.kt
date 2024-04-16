package com.example.bookhouse.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.bookhouse.presentation.navigation.NavigationRoutes
import com.example.bookhouse.ui.theme.LightBlue

@Composable
fun LargeTexts(
    color: Color,
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = color,
        style = MaterialTheme.typography.headlineLarge,
        modifier = modifier
    )
}

@Composable
fun SmallTexts(
    text: String,
    textAlign: TextAlign,
    modifier: Modifier = Modifier,

    ) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier,
        textAlign = textAlign
    )
}

@Composable
fun BlueColoredTexts(
    text: String,
    color: Color,
    style: TextStyle,
    textAlign: TextAlign,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = color,
        style = style,
        textAlign = textAlign,
        modifier = modifier
    )
}


