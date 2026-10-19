package com.example.baitap3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
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
import kotlin.math.roundToInt

@Composable
fun SliderDetailScreen(onBackClick: () -> Unit) {
    var volume by rememberSaveable {
        mutableStateOf(50f)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .safeDrawingPadding()
    ) {
        DetailHeader(
            title = "Slider",
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
                text = "Âm lượng",
                fontSize = 20.sp,
                color = Color.Black
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = "${volume.roundToInt()}%",
                fontSize = 32.sp,
                color = Color(0xFF2196F3)
            )

            Spacer(Modifier.height(16.dp))

            Slider(
                value = volume,
                onValueChange = {
                    volume = it
                },
                valueRange = 0f..100f,
                modifier = Modifier.fillMaxWidth(),
                colors = SliderDefaults.colors(
                    thumbColor = Color(0xFF2196F3),
                    activeTrackColor = Color(0xFF2196F3),
                    inactiveTrackColor = Color(0xFFBBDEFB)
                )
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("0%", color = Color.DarkGray)
                Text("100%", color = Color.DarkGray)
            }
        }
    }
}