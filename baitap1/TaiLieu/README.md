BÀI TẬP 1 - Tuần 1

1. Thông tin sinh viên

- Họ và tên: Bùi Ngọc Trang

- MSSV: 079306029517

- Môn học: Lập trình thiết bị di động di động

2. Mục tiêu

- Làm quen với Android Studio.

- Tạo một project Android bằng Kotlin.

- Thiết kế giao diện và chạy thử trên Android Studio

- Làm quen với GitHub và quản lý source code.

=== PHẦN 1 ===

Câu 1: Mong muốn và định hướng của bạn sau khi học xong môn học là gì?

- Mong muốn: Tự tay xây dựng thành công các ứng dụng di động hoàn chỉnh, nắm vững kiến thức nền tảng về lập trình thiết bị di động trên Android

- Định hướng: Có thể áp dụng kiến thức đã học để phát triển các dự án thực tế có thêm một điểm cộng để ghi vào CV và đáp ứng các điều kiện ứng tuyển của doanh nghiệp

Câu 2: Theo bạn, trong tương lai gần (10 năm) lập trình di động có phát triển không? Giải thích tại sao?
     
Theo em trong 10 năm tới lập trình di động sẽ phát triển mạnh mẽ vì:

- Thiết bị di động cần thiết với cuộc sống vì trong đời sống hiện nay con người luôn gắn với chiếc điện thoại. Đôi khi chỉ chờ đèn có vài chục giây cũng lấy điện thoại ra xem

- Lập trình thiết bị di động mở rộng thị trường hiện nay không chỉ có mỗi điện thoại thông minh mà bên cạnh đó còn có đồng hồ thông minh, kính thực tế ảo, màn hình thông minh trên xe ô tô

- Các thiết bị cũng được tích hợp AI tăng trải nghiệm của người dùng

- Nhu cầu chuyển đổi số ở các doanh nghiệp trên mọi lĩnh vực

Câu 3: Code UI
1. Input

- File ảnh đại diện `img.png` trong thư mục `res/drawable/`.

- Các Icon vector hệ thống: `ArrowBack` (Nút quay lại), `Edit` (Nút chỉnh sửa).

- Dữ liệu văn bản: `"BÙI NGỌC TRANG"`, `"079306029517"`.

2. Output

![img_3.png](img_3.png)

3. Giải thích các hàm

- MainActivity: ComponentActivity chính khởi chạy giao diện ứng dụng thông qua setContent.

- ProfileScreen(): Hàm Composable tổng dựng toàn bộ bố cục

- Row: Chứa thanh Header trên cùng với icon quay lại và icon chỉnh sửa.

- Image: Hiển thị ảnh đại diện, sử dụng Modifier .clip(CircleShape) để làm tròn khung hình.

- Text: Hiển thị Họ tên và MSSV với định dạng font chữ và màu sắc phù hợp.

PHẦN 2

Câu 1: Tìm hiểu mô hình giáo dục của HAA là gì? Ưu điểm và nhược điểm của mô hình này

- HAA (Horowitz Andreessen Academy): Là phương pháp đào tạo thực chiến tập trung vào dự án, khuyến khích người học tự xây dựng sản phẩm thật thay vì chỉ học lý thuyết.

- Ưu điểm: Sát với thực tế tuyển dụng, rèn tư duy giải quyết vấn đề và tính chủ động cao.

- Nhược điểm: Đòi hỏi tính tự giác lớn, dễ gây áp lực cho người mới chưa quen tự học.

Câu 2: Intenet và AI đã làm thay đổi cách con người tiếp cận tri thức như thế nào? Hãy so sánh cách một người học tìm kiếm, ghi nhớ và tận dụng kiến thức ở ba thời điểm: trước khi có Internet, thời kỳ Internet phổ biến, và thời kỳ AI tạo sinh hiện nay

Ví dụ như sinh viên hồi xưa (thời chưa có Internet) và sinh viên hiện nay

- Trước khi có Internet: Sinh viên chủ yếu lên thư viện đọc sách, nghe thầy cô giảng. Muốn nhớ kiến thức thì phải học thuộc lòng, còn khi làm bài thì áp dụng đúng theo mẫu trong sách.

- Thời kỳ Internet phổ biến: Sinh viên tìm câu trả lời bằng cách lên Google hoặc StackOverflow. Việc ghi nhớ nhẹ nhàng hơn nhờ lưu tài liệu số, lúc làm bài thì tìm code mẫu trên mạng về chỉnh sửa lại.

- Thời kỳ AI tạo sinh hiện nay: Sinh viên chat trực tiếp với AI để lấy câu trả lời ngay. Không cần nhớ chi tiết vụn vặt mà nhớ tư duy hệ thống, lúc làm bài thì đưa yêu cầu cho AI gợi ý code rồi mình kiểm tra và tối ưu lại.

Câu 3: Những năng lực nào chúng ta vẫn cần phát triển trong thời đại AI? Và vì sao những năng lực đó quan trọng?

- Tư duy sáng tạo và đặt mình vào vai người dùng: AI chỉ tổng hợp dữ liệu cũ, còn sinh viên cần hiểu nhu cầu thực tế của con người để nghĩ ra ý tưởng ứng dụng mới.

- Tư duy phản biện và nắm vững các kiến thức nền tảng: AI rất hay nói xàm hoặc cho code lỗi, sinh viên phải có kiến thức gốc để biết AI đúng hay sai mà sửa.

- Kỹ năng đặt vấn đề: Biết cách mô tả bài toán rõ ràng để AI hiểu và hỗ trợ mình làm việc nhanh hơn.

- Đạo đức nghề nghiệp: Để không phụ thuộc hoàn toàn vào AI, tránh đạo văn hay làm lộ thông tin bảo mật khi dùng AI.

