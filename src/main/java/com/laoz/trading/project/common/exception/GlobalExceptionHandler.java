package com.laoz.trading.project.common.exception;

import com.laoz.trading.project.common.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global Exception Handler
 * Catches and processes all unhandled exceptions uniformly
 * 全局异常处理器 - 统一捕获并处理所有未处理的异常
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle generic exceptions
     * 处理通用异常
     */
    @ExceptionHandler(Exception.class)
    public Response<Void> handleException(Exception e) {
        log.error("System exception: {}", e.getMessage(), e);
        return Response.error(e.getMessage());
    }

}
