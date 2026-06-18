package frontend_traffic.helpers.errors;

import org.springframework.http.HttpStatus;

// Hàm xử lý báo lỗi 
public class error_handle {
    // Khai báo biến
    private String message;
    private HttpStatus status;

    // Khởi tạo construction
    public error_handle(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    // region 400: Bad Request
    /* note: Máy chủ không thể hiểu yêu cầu do cú pháp không hợp lệ. */
    public static error_handle badRequest(String message) {
        return new error_handle(
                HttpStatus.BAD_REQUEST,
                message);
    }
    // endregion

    // region 401: Unauthorized
    /*
     * note: Cho dù quy chuẩn HTTP chỉ định “unauthorized” (không có thẩm quyền),
     * nhưng nó có nghĩa phản hồi nghĩa là, client phải các tự xác thực chính mình
     * để nhận được phản hồi đã yêu cầu.
     */
    public static error_handle unauthorized(String message) {
        return new error_handle(
                HttpStatus.UNAUTHORIZED,
                message);
    }
    // endregion

    // region 403: Forbidden
    /*
     * note: Client không có quyền truy cập vào phần nội dung, nghĩa là nó không
     * được phép, vì vậy máy chủ từ chối cung cấp tài nguyên được yêu cầu. Không
     * giống như 401, danh tính của client đã được máy chủ nhận biết.
     */
    public static error_handle forbidden(String message) {
        return new error_handle(HttpStatus.FORBIDDEN, message);
    }
    // endregion

    // region 404: Not Found
    /* note: trang này ko tồn tại */
    public static error_handle notFound(String message) {
        return new error_handle(
                HttpStatus.NOT_FOUND,
                message);
    }
    // endregion

    // region 409: Conflict
    /*
     * note: Phản hồi này được gửi khi 1 yêu cầu xung đột với trạng thái hiện tại
     * của máy chủ.
     */
    public static error_handle conflict(String message) {
        return new error_handle(
                HttpStatus.CONFLICT,
                message);
    }
    // endregion

    // region 500: Internal Server Error
    /*
     * note: Một thông báo chung, được đưa ra khi máy chủ gặp phải một trường hợp
     * bất ngờ, message cụ thể không phù hợp.
     */
    public static error_handle internalServerError(String message) {
        return new error_handle(
                HttpStatus.INTERNAL_SERVER_ERROR,
                message);
    }
    // endregion

    // region 502: Bad Gateway
    /*
     * note: Máy chủ đã hoạt động như một gateway hoặc proxy và nhận được một phản
     * hồi không hợp lệ từ máy chủ nguồn.
     */
    public static error_handle badGateway(String message) {
        return new error_handle(
                HttpStatus.BAD_GATEWAY,
                message);
    }
    // endregion

    public HttpStatus status() {
        return status;
    }

    public String message() {
        return message;
    }

}
