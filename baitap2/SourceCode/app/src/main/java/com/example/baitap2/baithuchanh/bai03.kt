package com.example.baitap2.baithuchanh

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class AcademicResult(val rank: String, val color: Color)

fun classifyScore(score: Double?): AcademicResult {
    if (score == null || score.isNaN() || score.isInfinite()) {
        return AcademicResult("Lỗi dữ liệu", Color.Gray)
    }

    return when {
        score < 0.0 || score > 10.0 -> AcademicResult("Không hợp lệ", Color.Gray)
        score >= 8.0 -> AcademicResult("Giỏi", Color(0xFF2E7D32))
        score >= 6.5 -> AcademicResult("Khá", Color(0xFF1565C0))
        score >= 5.0 -> AcademicResult("Trung bình", Color(0xFFEF6C00))
        else -> AcademicResult("Yếu", Color(0xFFC62828))
    }
}

fun parseScoreSafe(input: String?): Double? {
    if (input.isNullOrBlank()) return null
    val normalized = input.trim().replace(',', '.')
    return normalized.toDoubleOrNull()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GradeClassificationScreen() {
    val mainScore = 8.7
    val mainResult = classifyScore(mainScore)

    val extraScores = listOf(9.0, 7.0, 5.5, 3.0, 11.0)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Xếp loại học lực", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1E3A8A))
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFF3F4F6))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Điểm trung bình",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    val mainScoreText = if (mainScore == mainScore.toInt().toDouble()) mainScore.toInt().toString() else mainScore.toString()
                    Text(
                        text = mainScoreText,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = mainResult.color.copy(alpha = 0.12f),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = mainResult.rank,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = mainResult.color
                            )
                        }
                    }
                }
            }

            Text(
                text = "KIỂM TRA THÊM",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                modifier = Modifier.padding(top = 4.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    extraScores.forEachIndexed { index, score ->
                        val result = classifyScore(score)
                        val scoreText = if (score == score.toInt().toDouble()) score.toInt().toString() else score.toString()

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Điểm $scoreText",
                                fontSize = 14.sp,
                                color = Color.Black
                            )

                            Surface(
                                color = result.color.copy(alpha = 0.1f),
                                shape = MaterialTheme.shapes.small
                            ) {
                                Text(
                                    text = result.rank,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = result.color,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                )
                            }
                        }
                        if (index < extraScores.size - 1) {
                            HorizontalDivider(color = Color(0xFFF0F0F0), thickness = 1.dp)
                        }
                    }
                }
            }
        }
    }
}