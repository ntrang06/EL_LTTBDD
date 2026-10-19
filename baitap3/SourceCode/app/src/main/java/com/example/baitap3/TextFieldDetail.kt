package com.example.baitap3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextFieldDetailScreen(onBackClick: () -> Unit) {
    var input by rememberSaveable {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .safeDrawingPadding()
            .imePadding()
    ) {
        DetailHeader(
            title = "TextField",
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
            OutlinedTextField(
                value = input,
                onValueChange = { newValue ->
                    input = newValue
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text("Thông tin nhập")
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp)
            )

            Spacer(Modifier.height(20.dp))

            Text(
                text = if (input.isEmpty()) {
                    "Tự động cập nhật dữ liệu theo textfield"
                } else {
                    input
                },
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFFB33939),
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}