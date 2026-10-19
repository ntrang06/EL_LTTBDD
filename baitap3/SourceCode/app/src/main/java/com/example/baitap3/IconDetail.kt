package com.example.baitap3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun IconDetailScreen(onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .safeDrawingPadding()
    ) {
        DetailHeader(
            title = "Icon",
            onBackClick = onBackClick
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = 28.dp,
                alignment = Alignment.CenterVertically
            )
        ) {
            Text(
                text = "Các biểu tượng cơ bản",
                fontSize = 18.sp,
                color = Color.Black
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(
                        android.R.drawable.ic_menu_search
                    ),
                    contentDescription = "Tìm kiếm",
                    tint = Color(0xFF2196F3),
                    modifier = Modifier.size(40.dp)
                )

                Icon(
                    painter = painterResource(
                        android.R.drawable.ic_menu_camera
                    ),
                    contentDescription = "Máy ảnh",
                    tint = Color(0xFF4CAF50),
                    modifier = Modifier.size(40.dp)
                )

                Icon(
                    painter = painterResource(
                        android.R.drawable.ic_menu_send
                    ),
                    contentDescription = "Gửi",
                    tint = Color(0xFFF44336),
                    modifier = Modifier.size(40.dp)
                )
            }

            Text(
                text = "Cùng biểu tượng, khác kích thước",
                fontSize = 16.sp,
                color = Color.DarkGray
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(
                        android.R.drawable.ic_menu_search
                    ),
                    contentDescription = "Tìm kiếm nhỏ",
                    tint = Color(0xFF2196F3),
                    modifier = Modifier.size(24.dp)
                )

                Icon(
                    painter = painterResource(
                        android.R.drawable.ic_menu_search
                    ),
                    contentDescription = "Tìm kiếm vừa",
                    tint = Color(0xFF2196F3),
                    modifier = Modifier.size(48.dp)
                )

                Icon(
                    painter = painterResource(
                        android.R.drawable.ic_menu_search
                    ),
                    contentDescription = "Tìm kiếm lớn",
                    tint = Color(0xFF2196F3),
                    modifier = Modifier.size(72.dp)
                )
            }
        }
    }
}