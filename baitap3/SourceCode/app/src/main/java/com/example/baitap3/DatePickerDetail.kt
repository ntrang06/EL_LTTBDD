package com.example.baitap3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDetailScreen(onBackClick: () -> Unit) {
    var showDialog by rememberSaveable {
        mutableStateOf(false)
    }

    // Chỉ lưu ngày sau khi người dùng xác nhận.
    var selectedDateMillis by rememberSaveable {
        mutableStateOf<Long?>(null)
    }

    val selectedDateText = selectedDateMillis?.let { millis ->
        val formatter = SimpleDateFormat(
            "dd/MM/yyyy",
            Locale.getDefault()
        )

        // Ngày của DatePicker được biểu diễn theo UTC.
        formatter.timeZone = TimeZone.getTimeZone("UTC")
        formatter.format(Date(millis))
    } ?: "Bạn chưa chọn ngày"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .safeDrawingPadding()
    ) {
        DetailHeader(
            title = "DatePicker",
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
                text = "Ngày đã chọn",
                fontSize = 20.sp,
                color = Color.Black
            )

            Spacer(Modifier.height(20.dp))

            Text(
                text = selectedDateText,
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
                    text = "Chọn ngày",
                    fontSize = 16.sp
                )
            }
        }
    }

    if (showDialog) {
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = selectedDateMillis
        )

        DatePickerDialog(
            onDismissRequest = {
                showDialog = false
            },

            confirmButton = {
                TextButton(
                    enabled = datePickerState.selectedDateMillis != null,
                    onClick = {
                        selectedDateMillis =
                            datePickerState.selectedDateMillis
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
        ) {
            DatePicker(
                state = datePickerState,
                title = {
                    Text(
                        text = "Chọn ngày",
                        modifier = Modifier.padding(
                            start = 24.dp,
                            top = 16.dp
                        )
                    )
                }
            )
        }
    }
}