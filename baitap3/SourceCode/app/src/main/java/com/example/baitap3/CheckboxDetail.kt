package com.example.baitap3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CheckboxDetailScreen(onBackClick: () -> Unit) {
    val options = listOf("Kotlin", "Android", "Jetpack Compose")

    var selectedOptions by rememberSaveable {
        mutableStateOf(listOf<String>())
    }

    val blue = Color(0xFF2196F3)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .safeDrawingPadding()
    ) {
        DetailHeader(
            title = "Checkbox",
            onBackClick = onBackClick
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Chọn nội dung bạn muốn học",
                fontSize = 20.sp,
                color = Color.Black
            )

            Spacer(Modifier.height(24.dp))

            options.forEach { option ->
                val isChecked = option in selectedOptions

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 56.dp)
                        .toggleable(
                            value = isChecked,
                            role = Role.Checkbox,
                            onValueChange = { checked ->
                                selectedOptions = if (checked) {
                                    selectedOptions + option
                                } else {
                                    selectedOptions - option
                                }
                            }
                        )
                        .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = null,
                        colors = CheckboxDefaults.colors(
                            checkedColor = blue,
                            uncheckedColor = Color.Gray,
                            checkmarkColor = Color.White
                        )
                    )

                    Spacer(Modifier.width(12.dp))

                    Text(
                        text = option,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            Text(
                text = if (selectedOptions.isEmpty()) {
                    "Bạn chưa chọn nội dung nào"
                } else {
                    "Đã chọn: ${selectedOptions.joinToString(", ")}"
                },
                fontSize = 16.sp,
                color = if (selectedOptions.isEmpty()) {
                    Color.DarkGray
                } else {
                    blue
                }
            )
        }
    }
}