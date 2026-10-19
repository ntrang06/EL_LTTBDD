package com.example.baitap3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SwitchDetailScreen(onBackClick: () -> Unit) {
    var notificationsEnabled by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .safeDrawingPadding()
    ) {
        DetailHeader(
            title = "Switch",
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Cho phép thông báo",
                    modifier = Modifier.weight(1f),
                    fontSize = 18.sp,
                    color = Color.Black
                )

                Switch(
                    checked = notificationsEnabled,
                    onCheckedChange = {
                        notificationsEnabled = it
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = Color(0xFF2196F3)
                    )
                )
            }

            Spacer(Modifier.height(24.dp))

            Text(
                text = if (notificationsEnabled) {
                    "Thông báo đang bật"
                } else {
                    "Thông báo đang tắt"
                },
                fontSize = 16.sp,
                color = if (notificationsEnabled) {
                    Color(0xFF1976D2)
                } else {
                    Color.DarkGray
                }
            )
        }
    }
}