package com.example.bookhouse.on_board

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.bookhouse.R

data class OnBoardItems(
    val image: Int,
    val title: Int,
    val desc: Int
)
{
    companion object {
        @Composable
        fun getItem(): List<OnBoardItems> {

            return listOf(
                OnBoardItems(
                    R.drawable.on_board_1,
                    R.string.onboard_title_1,
                    R.string.onboard_desc_1
                ),
                OnBoardItems(
                    R.drawable.on_board_2,
                    R.string.onboard_title_2,
                    R.string.onboard_desc_2
                ),
                OnBoardItems(
                    R.drawable.on_board_3,
                    R.string.onboard_title_3,
                    R.string.onboard_desc_3
                )
            )
        }
    }
}
