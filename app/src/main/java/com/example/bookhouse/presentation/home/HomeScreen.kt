package com.example.bookhouse.presentation.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.bookhouse.R
import com.example.bookhouse.presentation.home.composables.PropertyComposable
import com.example.bookhouse.presentation.home.composables.PropertyListingGroup
import com.example.bookhouse.presentation.home.composables.SearchView
import com.example.bookhouse.presentation.navigation.main_graph.BottomNavigator
import com.example.bookhouse.presentation.sign_in.SignInViewModel
import com.example.bookhouse.ui.theme.Orange
import com.google.accompanist.pager.ExperimentalPagerApi

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
    signInViewModel: SignInViewModel = hiltViewModel()
) {

    val destinationState = signInViewModel.startDestinationState.collectAsState().value

    val user = homeViewModel.user.value

    var searchText by remember {
        mutableStateOf("")
    }

    val properties = homeViewModel.propertyListingState.value.data
    var propertiesPerGroup = homeViewModel.getListingGroups("Apartment")

    Log.d("--------->list per group", "HomeScreen: ${propertiesPerGroup?.size}")

    val propertiesData = homeViewModel.propertiesGroup.collectAsState().value.keys

    val groupIcon = listOf(
        R.drawable.apartment_building,
        R.drawable.home,
        R.drawable.office,
        R.drawable.hotel,
        R.drawable.villa,
        R.drawable.flat,
        R.drawable.townhouse,
        R.drawable.bread_and_breakfast,
    )
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigator(navController = navController)
        }
    ) {

        Surface {
            Image(
                painter = painterResource(id = R.drawable.image_1),
                contentDescription = null,
                modifier = modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )

            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
                modifier = modifier
                    .padding(top = 30.dp)
            ) {

                Text(
                    text = "Find your best\nPlace to stay\n",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.White,
                    modifier = modifier
                        .padding()
                        .padding(start = 10.dp),
                    fontWeight = FontWeight.Bold
                )

                SearchView(
                    modifier = modifier.padding(horizontal = 10.dp),
                    searchText = searchText,
                    onSearchChange = {
                        searchText = it
                    }
                )
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    modifier = modifier
                        .fillMaxSize(),
                    color = Color.White
                ) {
                    Column(
                        modifier = modifier.padding(10.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Featured places",
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )

                            Row {
                                Text(
                                    text = "View all",
                                    color = Orange
                                )

                                Icon(
                                    imageVector = Icons.Default.ArrowRight,
                                    contentDescription = null,
                                    tint = Orange
                                )
                            }
                        }

                        Spacer(modifier = modifier.height(10.dp))
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            itemsIndexed(propertiesData.toList()) { index, groupName ->
                                PropertyListingGroup(
                                    groupIcon = groupIcon[index],
                                    groupName = groupName,
                                    onClick = {
                                        propertiesPerGroup =
                                            homeViewModel.getListingGroups(groupName)
                                    }
                                )
                            }
                        }

                        Spacer(modifier = modifier.height(5.dp))

                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(7.dp)
                        ) {
                            propertiesPerGroup?.let { it1 ->
                                items(it1.toList()) { property ->
                                    PropertyComposable(
                                        images = property.photos,
                                        location = property.host_location,
                                        name = property.name,
                                        rating = property.review_scores_rating.toString(),
                                        price = property.price.toString(),
                                        propertyType = property.property_type
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }


    }


//    if (!destinationState) {
//        navController.navigate(Graphs.ON_BOARD_SCREEN)
//    }
}
