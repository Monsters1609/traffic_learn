package frontend_traffic.helpers.errors;

import org.springframework.http.HttpStatus;

// hàm xử lý khi dữ liệu trả về thành công 
public class success_handle<T> {
    // khai báo biến
    private HttpStatus status;
    private String message;
    private T data;

    // khởi tạo construction
    public success_handle(HttpStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public success_handle(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    // region 200: OK
    /* note: Yêu cầu đã thành công. */
    public success_handle<T> ok(HttpStatus status, String message, T data) {
        return new success_handle<>(HttpStatus.OK, message, data);
    }
    // endregion

    // region 201: Created
    /*
     * note: Yêu cầu đã thành công và kết quả là một tài nguyên mới đã được tạo. Đây
     * thường là phản hồi được gửi sau các yêu cầu POST hoặc một số yêu cầu PUT.
     */
    public success_handle<T> created(HttpStatus status, String message, T data) {
        return new success_handle<>(HttpStatus.CREATED, message, data);
    }
    // endregion

    // region 202: Accepted
    /*
     * note: Yêu cầu đã được nhận nhưng chưa được thực hiện. Yêu cầu này là
     * non-committal, vì không có cách nào trong HTTP để gửi sau đó một phản hồi
     * không đồng bộ cho biết kết quả của yêu cầu. Nó dành cho các trường hợp trong
     * đó 1 quá trình / máy chủ khác xử lý yêu cầu hoặc để xử lý hàng loạt.
     */
    public success_handle<T> accepted(HttpStatus status, String message, T data) {
        return new success_handle<>(HttpStatus.ACCEPTED, message, data);
    }
    // endregion

    // region 204: No Content
    /*
     * note: Không có nội dung để gửi cho yêu cầu này, nhưng các header có thể hữu
     * dụng. User-agent có thể cập nhật các header đã lưu trong bộ nhớ cache cho tài
     * nguyên này bằng các header mới.
     */

    public success_handle<T> noContent(HttpStatus status, String message) {
        return new success_handle<>(HttpStatus.NO_CONTENT, message);
    }
    // endregion

    // region 206: Partial Content
    /*
     * note: Code phản hồi này được dùng khi Range header được gửi từ client để yêu
     * cầu chỉ 1 phần của nguồn tài nguyên.
     */
    public success_handle<T> partialContent(HttpStatus status, String message, T data) {
        return new success_handle<>(HttpStatus.PARTIAL_CONTENT, message, data);
    }
    // endregion

    public HttpStatus status() {
        return status;
    }

    public String message() {
        return message;
    }

    public T data() {
        return data;
    }
}
