package com.example.baitap3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
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
fun RadioButtonDetailScreen(onBackClick: () -> Unit) {
    val options = listOf("Cơ bản", "Trung cấp", "Nâng cao")

    var selectedOption by rememberSaveable {
        mutableStateOf("Cơ bản")
    }

    val blue = Color(0xFF2196F3)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .safeDrawingPadding()
    ) {
        DetailHeader(
            title = "RadioButton",
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
                text = "Chọn trình độ học",
                fontSize = 20.sp,
                color = Color.Black
            )

            Spacer(Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectableGroup()
            ) {
                options.forEach { option ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 56.dp)
                            .selectable(
                                selected = option == selectedOption,
                                onClick = {
                                    selectedOption = option
                                },
                                role = Role.RadioButton
                            )
                            .padding(horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = option == selectedOption,
                            onClick = null,
                            colors = RadioButtonDefaults.colors(
                                selectedColor = blue,
                                unselectedColor = Color.Gray
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
            }

            Spacer(Modifier.height(24.dp))

            Text(
                text = "Đã chọn: $selectedOption",
                fontSize = 18.sp,
                color = blue
            )
        }
    }
}