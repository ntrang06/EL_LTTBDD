package com.example.baitap3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ButtonDetailScreen(onBackClick: () -> Unit) {
    var count by rememberSaveable { mutableStateOf(0) }
    val blue = Color(0xFF2196F3)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .safeDrawingPadding()
    ) {
        DetailHeader(
            title = "Button",
            onBackClick = onBackClick
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Giá trị hiện tại",
                    fontSize = 18.sp,
                    color = Color.Black
                )

                Text(
                    text = count.toString(),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = blue
                )

                // Nút có nền: tăng giá trị.
                Button(
                    onClick = { count++ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = blue,
                        contentColor = Color.White
                    )
                ) {
                    Text("Tăng thêm 1")
                }

                // Nút có viền: chỉ bấm được khi giá trị lớn hơn 0.
                OutlinedButton(
                    onClick = { count-- },
                    enabled = count > 0,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = blue
                    )
                ) {
                    Text("Giảm đi 1")
                }

                // Nút dạng chữ: đưa giá trị về 0.
                TextButton(
                    onClick = { count = 0 },
                    enabled = count > 0,
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = blue
                    )
                ) {
                    Text("Đặt lại")
                }

                // Minh họa nút bị vô hiệu hóa.
                Button(
                    onClick = {},
                    enabled = false,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Nút đang bị vô hiệu hóa")
                }
            }
        }
    }
}