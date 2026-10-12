package com.example.baitap2.baithuchanh

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorScreen() {
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<Double?>(null) }
    var selectedOp by remember { mutableStateOf<String?>(null) }

    val onCalculate: (String) -> Unit = { op ->
        if (selectedOp == op) {
            selectedOp = null
            result = null
        } else {
            selectedOp = op
            val n1 = num1.toDoubleOrNull()
            val n2 = num2.toDoubleOrNull()
            result = if (n1 != null && n2 != null) {
                when (op) {
                    "+" -> n1 + n2
                    "-" -> n1 - n2
                    "*" -> n1 * n2
                    "/" -> if (n2 != 0.0) n1 / n2 else Double.NaN
                    else -> null
                }
            } else {
                null
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Thực hành 03",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        OutlinedTextField(
            value = num1,
            onValueChange = {
                num1 = it
                selectedOp?.let { op ->
                    val n1 = num1.toDoubleOrNull()
                    val n2 = num2.toDoubleOrNull()
                    result = if (n1 != null && n2 != null) {
                        when (op) {
                            "+" -> n1 + n2
                            "-" -> n1 - n2
                            "*" -> n1 * n2
                            "/" -> if (n2 != 0.0) n1 / n2 else Double.NaN
                            else -> null
                        }
                    } else null
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CalcButton("+", Color(0xFFD35400), selectedOp == "+") { onCalculate("+") }
            CalcButton("-", Color(0xFFF39C12), selectedOp == "-") { onCalculate("-") }
            CalcButton("*", Color(0xFF8E44AD), selectedOp == "*") { onCalculate("*") }
            CalcButton("/", Color(0xFF2C3E50), selectedOp == "/") { onCalculate("/") }
        }

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = num2,
            onValueChange = {
                num2 = it
                selectedOp?.let { op ->
                    val n1 = num1.toDoubleOrNull()
                    val n2 = num2.toDoubleOrNull()
                    result = if (n1 != null && n2 != null) {
                        when (op) {
                            "+" -> n1 + n2
                            "-" -> n1 - n2
                            "*" -> n1 * n2
                            "/" -> if (n2 != 0.0) n1 / n2 else Double.NaN
                            else -> null
                        }
                    } else null
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Kết quả: ${formatResult(result)}",
                fontSize = 16.sp
            )
        }
    }
}

fun formatResult(res: Double?): String {
    if (res == null) return ""
    if (res.isNaN()) return "Lỗi chia cho 0"
    return if (res == res.toInt().toDouble()) res.toInt().toString() else res.toString()
}

@Composable
fun CalcButton(symbol: String, bgColor: Color, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .background(bgColor, shape = MaterialTheme.shapes.medium)
            .then(
                if (isSelected) Modifier.border(3.dp, Color.Black, shape = MaterialTheme.shapes.medium)
                else Modifier
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(text = symbol, color = Color.White, fontSize = 24.sp)
    }
}