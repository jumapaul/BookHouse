package com.example.bookhouse.login.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookhouse.R

@Composable
fun LargeTexts(
    color: Color,
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = color,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.headlineLarge,
        fontSize = 20.sp,
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
        fontSize = 20.sp,
        modifier = modifier,
        textAlign = textAlign
    )
}

@Composable
fun ClickableTexts(
    painter: Painter,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable {
                onClick
            },
    ) {
        Image(painter = painter, contentDescription = null)
        SmallTexts(text = text, textAlign = TextAlign.Start)
    }
}

@Preview(showBackground = true)
@Composable
fun TextPreview() {
    ClickableTexts(
        painter = painterResource(id = R.drawable.google),
        text = "Google",
        onClick = { /*TODO*/ })
}
