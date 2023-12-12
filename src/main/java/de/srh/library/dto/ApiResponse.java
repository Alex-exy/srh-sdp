package de.srh.library.dto;

public class ApiResponse<T> {
    private int code;
    private String message;

    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    public T getData() {
        return data;
    }
    private T data;
    public ApiResponse(ApiResponseCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }
    public ApiResponse(ApiResponseCode code, T data) {
        this(code);
        this.data = data;
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(ApiResponseCode.SUCCESS);
    }
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(ApiResponseCode.SUCCESS, data);
    }
    public static <T> ApiResponse<T> error(ApiResponseCode code) {
        return new ApiResponse<>(code);
    }
}
