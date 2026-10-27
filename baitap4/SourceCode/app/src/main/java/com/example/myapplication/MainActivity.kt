package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.MenuBook
import androidx.compose.material.icons.rounded.Groups
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MaterialTheme(
                colorScheme = lightColorScheme(
                    primary = Color(0xFF2954A3)
                )
            ) {
                LibraryApp { message ->
                    Toast.makeText(
                        this,
                        message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}

// Dùng hàm đa hình đã tạo trong Library.kt.
@Composable
fun EntityInfo(entity: LibraryEntity) {
    Text(text = displayInfo(entity))
}

@Composable
fun LibraryApp(showMessage: (String) -> Unit) {
    val manager = remember { LibraryManager() }

    var selectedTab by remember { mutableStateOf(0) }

    var selectedStudentId by remember {
        mutableStateOf(1)
    }

    var selectedBookIds by remember {
        mutableStateOf(setOf<Int>())
    }

    var showStudentDialog by remember {
        mutableStateOf(false)
    }

    var bookName by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var studentName by remember { mutableStateOf("") }

    val tabNames = listOf("Quản lý", "DS Sách", "Sinh viên")
    val tabIcons = listOf(
        Icons.Rounded.Home,
        Icons.Rounded.MenuBook,
        Icons.Rounded.Groups
    )

    val currentStudent = manager.students.find {
        it.id == selectedStudentId
    }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                tonalElevation = 8.dp
            ) {
                tabNames.forEachIndexed { index, title ->
                    NavigationBarItem(
                        selected = selectedTab == index,
                        onClick = {
                            selectedTab = index
                        },
                        icon = {
                            Icon(
                                imageVector = tabIcons[index],
                                contentDescription = null,
                                modifier = Modifier.size(26.dp)
                            )
                        },
                        label = {
                            Text(title)
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFF2954A3),
                            selectedTextColor = Color(0xFF2954A3),
                            indicatorColor = Color(0xFFE7EEFF),
                            unselectedIconColor = Color(0xFF8492A6),
                            unselectedTextColor = Color(0xFF8492A6)
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .imePadding()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Hệ thống Quản lý Thư viện",
                style = MaterialTheme.typography.headlineSmall
            )

            when (selectedTab) {

                // MÀN HÌNH 1: QUẢN LÝ MƯỢN SÁCH
                0 -> {
                    Text(
                        text = "Sinh viên",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedCard(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = currentStudent?.name ?: "Chưa chọn",
                                modifier = Modifier.padding(12.dp)
                            )
                        }

                        Button(
                            onClick = {
                                showStudentDialog = true
                            }
                        ) {
                            Text("Thay đổi")
                        }
                    }

                    Text(
                        text = "Danh sách sách",
                        style = MaterialTheme.typography.titleMedium
                    )

                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(
                            items = manager.books,
                            key = { it.id }
                        ) { book ->
                            Card(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.padding(12.dp),
                                    verticalAlignment =
                                        Alignment.CenterVertically
                                ) {
                                    Checkbox(
                                        checked = book.id in selectedBookIds,
                                        enabled = book.borrowerId == null,
                                        onCheckedChange = { checked ->
                                            selectedBookIds = if (checked) {
                                                selectedBookIds + book.id
                                            } else {
                                                selectedBookIds - book.id
                                            }
                                        }
                                    )

                                    Column(
                                        modifier = Modifier.weight(1f)
                                    ) {
                                        Text(
                                            text = book.name,
                                            style = MaterialTheme
                                                .typography.titleMedium
                                        )

                                        Text(
                                            text = if (book.borrowerId == null) {
                                                "Có thể mượn"
                                            } else {
                                                "Đang mượn: ${
                                                    manager.borrowerName(book)
                                                }"
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }

                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            if (selectedBookIds.isEmpty()) {
                                showMessage("Bạn chưa chọn sách")
                            } else {
                                val count = manager.borrowBooks(
                                    studentId = selectedStudentId,
                                    bookIds = selectedBookIds
                                )

                                selectedBookIds = emptySet()
                                showMessage("Đã mượn $count sách")
                            }
                        }
                    ) {
                        Text("Thêm")
                    }
                }

                // MÀN HÌNH 2: DANH SÁCH SÁCH
                1 -> {
                    OutlinedTextField(
                        value = bookName,
                        onValueChange = {
                            bookName = it
                        },
                        label = {
                            Text("Tên sách")
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = author,
                        onValueChange = {
                            author = it
                        },
                        label = {
                            Text("Tác giả")
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Button(
                        onClick = {
                            if (manager.addBook(bookName, author)) {
                                bookName = ""
                                author = ""
                                showMessage("Đã thêm sách mới")
                            } else {
                                showMessage("Nhập đủ tên sách và tác giả")
                            }
                        }
                    ) {
                        Text("Thêm sách mới")
                    }

                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(
                            items = manager.books,
                            key = { it.id }
                        ) { book ->
                            Card(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp),
                                    verticalArrangement =
                                        Arrangement.spacedBy(8.dp)
                                ) {
                                    EntityInfo(book)

                                    if (book.borrowerId == null) {
                                        Text("Trạng thái: Chưa được mượn")
                                    } else {
                                        Text(
                                            text = "Người mượn: ${
                                                manager.borrowerName(book)
                                            }"
                                        )

                                        Button(
                                            onClick = {
                                                manager.returnBook(book.id)
                                                showMessage("Đã trả sách")
                                            }
                                        ) {
                                            Text("Trả sách")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                // MÀN HÌNH 3: DANH SÁCH SINH VIÊN
                2 -> {
                    OutlinedTextField(
                        value = studentName,
                        onValueChange = {
                            studentName = it
                        },
                        label = {
                            Text("Tên sinh viên")
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Button(
                        onClick = {
                            if (manager.addStudent(studentName)) {
                                studentName = ""
                                showMessage("Đã thêm sinh viên")
                            } else {
                                showMessage("Bạn chưa nhập tên sinh viên")
                            }
                        }
                    ) {
                        Text("Thêm sinh viên")
                    }

                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(
                            items = manager.students,
                            key = { it.id }
                        ) { student ->
                            Card(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp),
                                    verticalArrangement =
                                        Arrangement.spacedBy(8.dp)
                                ) {
                                    EntityInfo(student)

                                    val borrowedBooks = manager.books.filter {
                                        it.borrowerId == student.id
                                    }

                                    val borrowedNames =
                                        if (borrowedBooks.isEmpty()) {
                                            "Chưa có"
                                        } else {
                                            borrowedBooks.joinToString {
                                                it.name
                                            }
                                        }

                                    Text(
                                        text = "Sách đang mượn: $borrowedNames"
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // HỘP THOẠI CHỌN SINH VIÊN
    if (showStudentDialog) {
        AlertDialog(
            onDismissRequest = {
                showStudentDialog = false
            },
            title = {
                Text("Chọn sinh viên")
            },
            text = {
                LazyColumn(
                    modifier = Modifier.heightIn(max = 300.dp)
                ) {
                    items(
                        items = manager.students,
                        key = { it.id }
                    ) { student ->
                        TextButton(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                selectedStudentId = student.id
                                selectedBookIds = emptySet()
                                showStudentDialog = false
                            }
                        ) {
                            Text(student.name)
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showStudentDialog = false
                    }
                ) {
                    Text("Đóng")
                }
            }
        )
    }
}