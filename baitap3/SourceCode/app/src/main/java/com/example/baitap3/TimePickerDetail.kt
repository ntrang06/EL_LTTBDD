package com.example.baitap3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
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
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDetailScreen(onBackClick: () -> Unit) {
    var showDialog by rememberSaveable {
        mutableStateOf(false)
    }

    var selectedHour by rememberSaveable {
        mutableStateOf(8)
    }

    var selectedMinute by rememberSaveable {
        mutableStateOf(0)
    }

    var hasSelectedTime by rememberSaveable {
        mutableStateOf(false)
    }

    val selectedTimeText = if (hasSelectedTime) {
        String.format(
            Locale.getDefault(),
            "%02d:%02d",
            selectedHour,
            selectedMinute
        )
    } else {
        "Bạn chưa chọn giờ"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .safeDrawingPadding()
    ) {
        DetailHeader(
            title = "TimePicker",
            onBackClick = onBackClick
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Giờ đã chọn",
                fontSize = 20.sp,
                color = Color.Black
            )

            Spacer(Modifier.height(20.dp))

            Text(
                text = selectedTimeText,
                fontSize = 26.sp,
                color = Color(0xFF2196F3),
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(28.dp))

            Button(
                onClick = {
                    showDialog = true
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2196F3),
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Chọn giờ",
                    fontSize = 16.sp
                )
            }
        }
    }

    if (showDialog) {
        val timePickerState = rememberTimePickerState(
            initialHour = selectedHour,
            initialMinute = selectedMinute,
            is24Hour = true
        )

        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },

            title = {
                Text("Chọn giờ và phút")
            },

            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 420.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TimePicker(
                        state = timePickerState
                    )
                }
            },

            confirmButton = {
                TextButton(
                    onClick = {
                        selectedHour = timePickerState.hour
                        selectedMinute = timePickerState.minute
                        hasSelectedTime = true
                        showDialog = false
                    }
                ) {
                    Text("Xác nhận")
                }
            },

            dismissButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text("Hủy")
                }
            }
        )
    }
}