package com.example.myapplication

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

// 1. TRỪU TƯỢNG
// Lớp cha quy định các đối tượng phải có hàm cung cấp thông tin.
abstract class LibraryEntity(
    val id: Int,
    val name: String
) {
    abstract fun getInfo(): String
}

// 2. KẾ THỪA
// Student kế thừa id, name từ LibraryEntity.
class Student(
    id: Int,
    name: String
) : LibraryEntity(id, name) {

    override fun getInfo(): String {
        return "Mã sinh viên: $id\nHọ tên: $name"
    }
}

// Book cũng kế thừa LibraryEntity.
class Book(
    id: Int,
    name: String,
    val author: String
) : LibraryEntity(id, name) {

    // 3. ĐÓNG GÓI
    // Bên ngoài được đọc nhưng không được tự gán người mượn.
    var borrowerId: Int? by mutableStateOf(null)
        private set

    fun borrow(studentId: Int): Boolean {
        if (borrowerId != null) {
            return false
        }

        borrowerId = studentId
        return true
    }

    fun returnBook() {
        borrowerId = null
    }

    override fun getInfo(): String {
        return "Mã sách: $id\nTên sách: $name\nTác giả: $author"
    }
}

// 4. ĐA HÌNH
// Cùng gọi getInfo() qua kiểu lớp cha,
// kết quả phụ thuộc đối tượng được truyền vào là Book hay Student.
fun displayInfo(entity: LibraryEntity): String {
    return entity.getInfo()
}

class LibraryManager {

    // Danh sách gốc được bảo vệ bằng private.
    private val bookList = mutableStateListOf(
        Book(1, "Lập trình Kotlin", "Nguyễn Văn An"),
        Book(2, "Lập trình Android", "Trần Văn Bình"),
        Book(3, "Lập trình hướng đối tượng", "Lê Thị Hoa")
    )

    private val studentList = mutableStateListOf(
        Student(1, "Nguyễn Văn A"),
        Student(2, "Trần Thị B"),
        Student(3, "Lê Văn C")
    )

    // Bên ngoài nhận bản sao danh sách để đọc.
    val books: List<Book>
        get() = bookList.toList()

    val students: List<Student>
        get() = studentList.toList()

    fun addBook(name: String, author: String): Boolean {
        if (name.isBlank() || author.isBlank()) {
            return false
        }

        val id = (bookList.maxOfOrNull { it.id } ?: 0) + 1

        bookList.add(
            Book(id, name.trim(), author.trim())
        )
        return true
    }

    fun addStudent(name: String): Boolean {
        if (name.isBlank()) {
            return false
        }

        val id = (studentList.maxOfOrNull { it.id } ?: 0) + 1

        studentList.add(
            Student(id, name.trim())
        )
        return true
    }

    fun borrowBooks(studentId: Int, bookIds: Set<Int>): Int {
        if (studentList.none { it.id == studentId }) {
            return 0
        }

        var count = 0

        for (book in bookList) {
            if (book.id in bookIds && book.borrow(studentId)) {
                count++
            }
        }

        return count
    }

    fun returnBook(bookId: Int) {
        bookList.find { it.id == bookId }?.returnBook()
    }

    fun borrowerName(book: Book): String {
        return studentList.find {
            it.id == book.borrowerId
        }?.name ?: "Không xác định"
    }
}