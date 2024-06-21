package com.example.bookhouse.presentation.home.composables

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookhouse.ui.theme.Orange
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.delay

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PropertyComposable(
    modifier: Modifier = Modifier,
    images: List<String>,
    location: String,
    name: String,
    rating: String,
    price: String,
    propertyType: String

) {

    Column {
        SlidingImages(images = images)

        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = name)

            Row {
                Icon(
                    imageVector = Icons.Default.StarRate,
                    contentDescription = null,
                    tint = Orange,
                    modifier = modifier.size(15.dp)
                )


                Text(
                    text = rating,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null
            )

            Text(text = location)
        }

        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            Text(text = "$$price /month")
            Spacer(modifier = modifier.width(10.dp))
            Text(text = "-$propertyType")
        }
    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SlidingImages(
    modifier: Modifier = Modifier,
    images: List<String>,
    pagerState: PagerState = remember { PagerState() }
) {
    LaunchedEffect(key1 = pagerState.currentPage) {
        delay(5000L)
        pagerState.animateScrollToPage((pagerState.currentPage + 1) % images.size)
    }
    Column(
        modifier = modifier.height(200.dp),
    ) {
        HorizontalPager(
            count = images.size,
            state = pagerState
        ) { page ->

            Surface(
                shape = RoundedCornerShape(10.dp),
                modifier = modifier
                    .fillMaxWidth()
                    .align(
                        Alignment.CenterHorizontally
                    )
            ) {
                Card {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current).data(
                            images[page]
                        ).crossfade(true).build(),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = modifier.fillMaxSize()
                    )
                }

                Box(
                    modifier = modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    HorizontalPagerIndicator(
                        activeColor = Orange,
                        pagerState = pagerState,
                        modifier = modifier
                            .padding()
                            .padding(top = 190.dp)
                            .wrapContentSize()
                    )
                }
            }
        }
    }
}