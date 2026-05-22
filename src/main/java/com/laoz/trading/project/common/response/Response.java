package com.laoz.trading.project.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

    private boolean success;
    private T data;
    private String message;
    private String code;

    public static <T> Response<T> success(T data) {
        return Response.<T>builder()
                .success(true)
                .data(data)
                .message("success")
                .code("200")
                .build();
    }

    public static Response<Void> success() {
        return Response.<Void>builder()
                .success(true)
                .message("success")
                .code("200")
                .build();
    }

    public static <T> Response<T> error(String message) {
        return Response.<T>builder()
                .success(false)
                .message(message)
                .code("500")
                .build();
    }

    public static <T> Response<T> error(String code, String message) {
        return Response.<T>builder()
                .success(false)
                .message(message)
                .code(code)
                .build();
    }

}
