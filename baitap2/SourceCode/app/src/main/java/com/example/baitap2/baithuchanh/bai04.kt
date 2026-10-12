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


data class ClassStudent(
    val name: String,
    val score: Double?
)

data class AcademicRankResult(val rank: String, val color: Color)

fun getStudentRank(score: Double?): AcademicRankResult {
    if (score == null || score.isNaN() || score.isInfinite()) {
        return AcademicRankResult("Chưa có điểm", Color.Gray)
    }
    return when {
        score < 0.0 || score > 10.0 -> AcademicRankResult("Không hợp lệ", Color.Gray)
        score >= 8.0 -> AcademicRankResult("Giỏi", Color(0xFF2E7D32))
        score >= 6.5 -> AcademicRankResult("Khá", Color(0xFF1565C0))
        score >= 5.0 -> AcademicRankResult("Trung bình", Color(0xFFEF6C00))
        else -> AcademicRankResult("Yếu", Color(0xFFC62828))
    }
}

fun formatScoreValue(score: Double?): String {
    if (score == null) return "—"
    return if (score == score.toInt().toDouble()) String.format("%.1f", score) else score.toString()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassScoreBoardScreen() {
    val students = listOf(
        ClassStudent("Nguyễn Văn An", 9.0),
        ClassStudent("Trần Thị Bình", 7.2),
        ClassStudent("Lê Minh Chi", null),
        ClassStudent("Phạm Quốc Dũng", 4.5),
        ClassStudent("Võ Thanh Em", 5.8)
    )
    var totalScore = 0.0
    var countHasScore = 0
    var excellentCount = 0
    var missingScoreCount = 0
    var maxScore = -1.0

    for (student in students) {
        val s = student.score
        if (s == null) {
            missingScoreCount++
        } else {
            totalScore += s
            countHasScore++
            if (s >= 8.0) {
                excellentCount++
            }
            if (s > maxScore) {
                maxScore = s
            }
        }
    }

    val classAverage = if (countHasScore > 0) totalScore / countHasScore else 0.0
    val maxScoreDisplay = if (maxScore >= 0) formatScoreValue(maxScore) else "—"

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Bảng điểm lớp", color = Color.White) },
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
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E3A8A)),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(
                        text = "Điểm trung bình lớp",
                        fontSize = 13.sp,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = String.format("%.2f", classAverage),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalDivider(color = Color.White.copy(alpha = 0.2f), thickness = 1.dp)
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(text = "Giỏi", fontSize = 12.sp, color = Color.White.copy(alpha = 0.8f))
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(text = "$excellentCount", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        }
                        Column {
                            Text(text = "Chưa có điểm", fontSize = 12.sp, color = Color.White.copy(alpha = 0.8f))
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(text = "$missingScoreCount", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        }
                        Column {
                            Text(text = "Cao nhất", fontSize = 12.sp, color = Color.White.copy(alpha = 0.8f))
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(text = maxScoreDisplay, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        }
                    }
                }
            }

            Text(
                text = "DANH SÁCH - ${students.size} SINH VIÊN",
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
                    students.forEachIndexed { index, student ->
                        val rankResult = getStudentRank(student.score)
                        val scoreText = formatScoreValue(student.score)

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                                Text(
                                    text = student.name,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black
                                )
                                Text(
                                    text = rankResult.rank,
                                    fontSize = 13.sp,
                                    color = if (student.score == null) Color.Gray else rankResult.color,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }

                            Text(
                                text = scoreText,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (student.score == null) Color.Gray else Color.Black
                            )
                        }

                        if (index < students.size - 1) {
                            HorizontalDivider(color = Color(0xFFF0F0F0), thickness = 1.dp)
                        }
                    }
                }
            }
        }
    }
}