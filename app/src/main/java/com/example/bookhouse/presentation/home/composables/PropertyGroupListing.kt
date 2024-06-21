package com.example.bookhouse.presentation.home.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun PropertyListingGroup(
    modifier: Modifier = Modifier,
    groupIcon: Int,
    groupName: String,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, color = Color.LightGray),
        modifier = modifier.clickable {
            onClick()
        }
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(3.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.padding(5.dp)
        ) {
            Icon(
                painter = painterResource(id = groupIcon),
                contentDescription = null,
                modifier = modifier.size(24.dp)
            )
            Text(
                text = groupName,
                modifier = modifier.padding(5.dp)
            )
        }
    }
}