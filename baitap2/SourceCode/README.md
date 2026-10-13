## 1. Cấu trúc Source Code

- Code nằm trong SourceCode/app/src/main/java/com/example/baitap2/baithuchanh/

- `MainActivity.kt`: Điểm khởi chạy chính của ứng dụng, chứa hàm `setContent` để gọi hiển thị các màn hình bài tập.

- `baithuchanh/`: Thư mục chứa các tệp mã nguồn cho từng bài:

    * `bai01.kt`: bài máy tính
  
    * `bai02.kt`: danh sách sinh viên
  
    * `bai03.kt`: xếp loại học lực
  
    * `bai04.kt`: bài Bảng điểm lớp

## 2. Hướng dẫn chạy chương trình

1. Mở thư mục `SourceCode` bằng **Android Studio**

2. Đợi Gradle đồng bộ hóa  hoàn tất

3. Kết nối máy ảo hoặc thiết bị Android thật

4. Mở tệp `MainActivity.kt`, thay đổi hàm hiển thị trong khối `setContent { ... }` bằng màn hình bài tập muốn kiểm tra 

(Ví dụ: `CalculatorScreen()`, `StudentListScreen()`, `GradeClassificationScreen()`, hoặc `ClassScoreBoardScreen()`)

5. Bấm nút **Run** (biểu tượng hình tam giác màu xanh lá) để chạy ứng dụng