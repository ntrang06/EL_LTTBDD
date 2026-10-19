package com.example.baitap3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun IconButtonDetailScreen(onBackClick: () -> Unit) {
    var isFavorite by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .safeDrawingPadding()
    ) {
        DetailHeader(
            title = "IconButton",
            onBackClick = onBackClick
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Đánh dấu yêu thích",
                fontSize = 20.sp,
                color = Color.Black
            )

            Spacer(Modifier.height(24.dp))

            IconButton(
                onClick = { isFavorite = !isFavorite },
                modifier = Modifier.size(64.dp)
            ) {
                Icon(
                    painter = painterResource(
                        id = if (isFavorite) {
                            android.R.drawable.btn_star_big_on
                        } else {
                            android.R.drawable.btn_star_big_off
                        }
                    ),
                    contentDescription = if (isFavorite) {
                        "Bỏ yêu thích"
                    } else {
                        "Thêm vào yêu thích"
                    },
                    tint = if (isFavorite) {
                        Color(0xFFFFB300)
                    } else {
                        Color.Gray
                    },
                    modifier = Modifier.size(40.dp)
                )
            }

            Spacer(Modifier.height(16.dp))

            Text(
                text = if (isFavorite) {
                    "Đã thêm vào yêu thích"
                } else {
                    "Chưa đánh dấu yêu thích"
                },
                fontSize = 18.sp,
                color = if (isFavorite) {
                    Color(0xFF2196F3)
                } else {
                    Color.DarkGray
                }
            )
        }
    }
}