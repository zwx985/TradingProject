package com.laoz.trading.project.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Unified API Response Wrapper
 * 统一 API 响应包装类
 *
 * @param <T> response data type 响应数据类型
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

    /**
     * Whether the request was successful
     * 请求是否成功
     */
    private boolean success;

    /**
     * Response data payload
     * 响应数据载荷
     */
    private T data;

    /**
     * Response message
     * 响应消息
     */
    private String message;

    /**
     * Response status code
     * 响应状态码
     */
    private String code;

    /**
     * Create a successful response with data
     * 创建带数据的成功响应
     */
    public static <T> Response<T> success(T data) {
        return Response.<T>builder()
                .success(true)
                .data(data)
                .message("success")
                .code("200")
                .build();
    }

    /**
     * Create a successful response without data
     * 创建无数据的成功响应
     */
    public static Response<Void> success() {
        return Response.<Void>builder()
                .success(true)
                .message("success")
                .code("200")
                .build();
    }

    /**
     * Create an error response with default code "500"
     * 创建默认错误码 "500" 的错误响应
     */
    public static <T> Response<T> error(String message) {
        return Response.<T>builder()
                .success(false)
                .message(message)
                .code("500")
                .build();
    }

    /**
     * Create an error response with custom code
     * 创建带自定义错误码的错误响应
     */
    public static <T> Response<T> error(String code, String message) {
        return Response.<T>builder()
                .success(false)
                .message(message)
                .code(code)
                .build();
    }

}
