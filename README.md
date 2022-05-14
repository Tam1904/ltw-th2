# ltw-th2
sử dụng spring jdbc để kết nối CSDL
Lớp controller là nơi xử lý các resquest
Lớp model chứa thực thể product
Lớp dao là nơi xử lý vào ra cơ sở dữ liệu

HomeController xử lý các yêu cầu request:
- @GetMapping("/") sẽ xử lý request đến đường dẫn http://localhost:8080/ chuyển đến đến trang index.html.
- @GetMapping("/view") xử lý các request trên đường dẫn /view khi có yêu cầu view từ trang index.html, tại đây lấy ra list product từ csdl bằng hàm getProducts() sau đó gắn vào model trả về cho trang product.html
-> product.html dùng th:each đọc list products để hiển thị
- @GetMapping("/add") tại đây khi có yêu cầu add product từ trang product.html thì ta khởi tạo 1 product đặt giá trị mặc định, do add và update dùng chung 1 trang nên ta dùng 1 type để phân biệt khi nào add và update sẽ dùng các hàm tương ứng. product và type sẽ được gắn vào model trả về cho trang update.html
- @GetMapping("/edit") tại đây tiếp nhận yêu cầu edit product từ product.html, ta sẽ dùng hàm getProduct(code) lấy ra sản phẩm theo code  rồi gắn vào model, và type(mô tả ở add) trả về cho trang update.html.
- @PostMapping("/UpdateProduct") tại đây xử lý validate kiểm tra xem có thuộc tính nào rỗng hay không, kiểm tra giá trị price xem có dúng là kiểu float hay không, trong trường hợp type bằng Add thì sẽ kiểm tra thêm xem sản phẩm đã tồn tại hay chưa bằng hàm exitsProduct(code) nếu thỏa mãn thì thêm product vào csdl bằng hàm addProduct(product), trường hợp Update thì cập nhật vào csdl dùng hàm updateProduct(), sau đó điều hướng về product.html bằng 'redirect:/view'
- @GetMapping("/delete") xử lý yêu cầu delete product từ trang product.html, tại đây sẽ lấy product bằng getProduct(code) sau đó gắn vào model chuyển trả về cho trang delete.html
- @PostMapping("/DeleteProduct") tại đây xử lý xóa product, khi người dùng click yes thì thực hiện hàm deleteProduct(code) để xóa product khỏi cơ sở dữ liệu và sẽ điều hướng quay lại trang product.html bằng 'redirect/view'

Nguyễn Bá Tâm_B19DCCN569 và Vũ Đức Mạnh_B19DCCN425 - thêm, cập nhật và xóa sản phẩm
Lê Huy Duy_B19DCCN138 : lấy danh sách sản phẩm hiển thị view, làm giao diện
