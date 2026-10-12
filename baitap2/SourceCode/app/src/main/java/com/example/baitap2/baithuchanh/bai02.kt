package com.example.baitap2.baithuchanh

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Student(
    val name: String,
    val email: String?,
    val phone: String?
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentListScreen() {
    val students = listOf(
        Student(name = "Nguyễn Văn An", email = "an@sv.edu.vn", phone = "0901 234 567"),
        Student(name = "Trần Thị Bình", email = null, phone = "0912 888 999"),
        Student(name = "Lê Minh Chi", email = "chi@sv.edu.vn", phone = null),
        Student(name = "Phạm Quốc Dũng", email = null, phone = null),
        Student(name = "Võ Thanh Em", email = "em@sv.edu.vn", phone = "0933 111 222")
    )
    var missingEmailCount = 0
    for (student in students) {
        if (student.email == null) {
            missingEmailCount++
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Danh sách sinh viên", color = Color.White) },
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
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            for (student in students) {
                CardItem(student = student)
            }
            Spacer(modifier = Modifier.height(8.dp))
            ContainerSummary(total = students.size, missingEmail = missingEmailCount)
        }
    }
}

@Composable
fun CardItem(student: Student) {
    val displayEmail = student.email ?: "Chưa cập nhật"
    val displayPhone = student.phone ?: "Chưa cập nhật"

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = student.name,
                fontSize = 16.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))

            Row {
                Text(text = "Email: ", fontSize = 14.sp, color = Color.Gray)
                Text(
                    text = displayEmail,
                    fontSize = 14.sp,
                    color = if (student.email == null) Color.Red else Color.Black
                )
            }

            Spacer(modifier = Modifier.height(2.dp))

            Row {
                Text(text = "SĐT: ", fontSize = 14.sp, color = Color.Gray)
                Text(
                    text = displayPhone,
                    fontSize = 14.sp,
                    color = if (student.phone == null) Color.Red else Color.Black
                )
            }
        }
    }
}

@Composable
fun ContainerSummary(total: Int, missingEmail: Int) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color(0xFFE5E7EB),
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Tổng: $total sinh viên", fontSize = 14.sp, color = Color.Black)
            Row {
                Text(text = "Thiếu email: ", fontSize = 14.sp, color = Color.Black)
                Text(text = "$missingEmail", fontSize = 14.sp, color = Color.Red)
            }
        }
    }
}