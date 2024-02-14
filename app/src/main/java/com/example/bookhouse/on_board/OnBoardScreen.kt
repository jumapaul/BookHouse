package com.example.bookhouse.on_board

import android.annotation.SuppressLint
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bookhouse.R
import com.example.bookhouse.common.navigation.NavigationRoutes
import com.example.bookhouse.on_board.onboard_viewmodel.OnBoardViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoarding(
    navController: NavController,
    onBoardViewModel: OnBoardViewModel = hiltViewModel()
) {

    val items = OnBoardItems.getItem()
    val scope = rememberCoroutineScope()
    val pageState = rememberPagerState()

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {


        HorizontalPager(
            count = items.size,
            state = pageState,
            modifier = Modifier
                .fillMaxHeight(0.9f)
                .fillMaxWidth()
        ) { page ->
            OnBoardItem(items = items[page], items.size, pageState)
        }


        BottomSection(
            onBackClick = {
                if (pageState.currentPage + 1 > 1) scope.launch {
                    pageState.scrollToPage(pageState.currentPage - 1)
                }
            },
            onSkipClick = {
                if (pageState.currentPage + 1 < items.size) scope.launch {
                    pageState.scrollToPage(pageState.currentPage + 1)
                } else if (pageState.currentPage == items.size - 1) {
                    onBoardViewModel.saveOnBoardingState(completed = true)
                    navController.popBackStack()
                    navController.navigate(NavigationRoutes.SignUpScreen.route)
                }
            },
            pageState,
            items.size
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardItem(
    items: OnBoardItems,
    size: Int,
    pagerState: PagerState
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = items.image),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )


        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .wrapContentHeight(),
            shape = RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp),
        ) {


            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(10.dp)
            ) {
                TopSection(size = size, index = pagerState.currentPage)

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = stringResource(id = items.title),
                    style = MaterialTheme.typography.headlineSmall,
                    color = colorResource(id = R.color.light_blue),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = stringResource(id = items.desc),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun TopSection(
    size: Int,
    index: Int
) {
    Box(modifier = Modifier) {
        Indicators(size = size, index = index)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BottomSection(
    onBackClick: () -> Unit,
    onSkipClick: () -> Unit,
    pagerState: PagerState,
    size: Int,
    modifier: Modifier = Modifier,
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {

        //Back Button
        IconButton(
            onClick = onBackClick, modifier = modifier.align(Alignment.CenterStart)
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }

        //Skip Button
        TextButton(
            onClick = onSkipClick, modifier = modifier.align(Alignment.CenterEnd)
        ) {
            Text(
                text = if (pagerState.currentPage == size - 1) "Finish" else "Next",
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun BoxScope.Indicators(size: Int, index: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        repeat(size) {
            Indicator(isSelected = it == index)
        }
    }
}

@Composable
fun Indicator(isSelected: Boolean) {
    val width = animateDpAsState(
        targetValue = if (isSelected) 25.dp else 10.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = ""
    )
    Box(
        modifier = Modifier
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
            )
    )
}
