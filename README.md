# ltw-th2
sử dụng spring jdbc để kết nối CSDL
Lớp controller là nơi xử lý các resquest
Lớp model chứa thực thể product
Lớp dao là nơi xử lý vào ra cơ sở dữ liệu

HomeController xử lý các yêu cầu request:
- GetMapping("/") sẽ chuyển đến đến trang index.html.
- @GetMapping("/view") xử lý các request trên đường dẫn /view khi có yêu cầu view từ trang index tại đây lấy ra list product từ csdl bằng hàm getProducts sau đó gắn vào model trả về cho trang product.html
-> product.html có hàm th:each đọc list products để hiển thị
-
