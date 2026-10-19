package com.example.baitap3

import androidx.compose.foundation.layout.*
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FloatingActionButtonDetailScreen(onBackClick: () -> Unit) {
    var count by rememberSaveable { mutableStateOf(0) }
    val blue = Color(0xFF2196F3)

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding(),
        containerColor = Color.White,
        topBar = {
            DetailHeader(
                title = "FloatingActionButton",
                onBackClick = onBackClick
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { count++ },
                containerColor = blue,
                contentColor = Color.White
            ) {
                Icon(
                    painter = painterResource(
                        android.R.drawable.ic_input_add
                    ),
                    contentDescription = "Tăng bộ đếm",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Số lần bấm",
                fontSize = 20.sp,
                color = Color.Black
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = count.toString(),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = blue
            )

            Spacer(Modifier.height(24.dp))

            Text(
                text = "Bấm nút + ở góc dưới bên phải để tăng bộ đếm.",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = Color.DarkGray
            )
        }
    }
}