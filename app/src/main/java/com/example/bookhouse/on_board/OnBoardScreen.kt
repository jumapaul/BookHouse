package com.example.bookhouse.on_board

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookhouse.R
import com.example.bookhouse.ui.theme.BookHouseTheme

@Composable
fun OnBoardScreen() {

}

@Composable
fun FirstOnBoardScreen(
    modifier: Modifier = Modifier,
    onBoard: Int
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = onBoard), contentDescription = null,
            modifier = modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = modifier.fillMaxSize()
        ) {
            Surface(
                shape = RoundedCornerShape(
                    topStart = 30.dp, topEnd = 30.dp
                ),
                modifier = modifier.wrapContentHeight().align(Alignment.End)
            ) {
                Column {
                    Text(
                        text = "Find Your Perfect rental to stay",
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(
                                vertical = 30.dp
                            ),
                        fontSize = 34.sp,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "We provide a fastest way to help you find a perfect home",
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(
                                vertical = 20.dp
                            ),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineMedium,
                    )
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardPreview() {
    BookHouseTheme {
        FirstOnBoardScreen(onBoard = R.drawable.third_on_board)
    }
}